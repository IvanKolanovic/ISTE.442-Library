package com.ikolanovic.restapi.services;

import com.ikolanovic.restapi.exceptions.RequestFailedException;
import com.ikolanovic.restapi.exceptions.ResourceNotFoundException;
import com.ikolanovic.restapi.models.User;
import com.ikolanovic.restapi.repos.BookRepository;
import com.ikolanovic.restapi.repos.UserRepository;
import com.ikolanovic.restapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(User user) {

        if (isEmailInUse(user.getEmail())) {
            throw new RequestFailedException(CONFLICT, "User email:" + user.getEmail() + " is already taken!");
        }

        user.setRoles("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id:" + id + " not found."));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id:" + userId + " not found."));

        user.getBorrowedBooks().forEach(book -> book.setAvailable(true));
        bookRepository.saveAllAndFlush(user.getBorrowedBooks());

        userRepository.delete(user);
        return true;
    }

    @Override
    @Transactional
    public User updateUser(Long id, User input) {
        User updatedUser =
                userRepository
                        .findById(id)
                        .map(
                                user -> {
                                    if (!user.getEmail().equals(input.getEmail())) {
                                        if (isEmailInUse(input.getEmail())) {
                                            throw new RequestFailedException(
                                                    CONFLICT, "User email:" + input.getEmail() + " is already taken!");
                                        }
                                        user.setEmail(input.getEmail());
                                    }

                                    if (Optional.ofNullable(input.getFirstName()).isPresent())
                                        user.setFirstName(input.getFirstName());

                                    if (Optional.ofNullable(input.getLastName()).isPresent())
                                        user.setLastName(input.getLastName());

                                    return user;
                                })
                        .orElseThrow(resourceNotFound("User with id " + id + " does not exist."));

        return userRepository.saveAndFlush(updatedUser);
    }

    @Override
    public boolean isEmailInUse(String email) {
        Optional<User> dbUser = userRepository.findByEmail(email);
        return dbUser.isPresent();
    }
}
