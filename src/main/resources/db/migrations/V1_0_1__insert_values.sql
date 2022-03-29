-- Insert Users
INSERT INTO user (email, password, roles, first_name, last_name)
VALUES ('admin@rit.com', '$2a$10$.xaYSfiMMA1mWtZcNe9HjODlWtaOBoq6azpoEy445YFX8alxHPJJC', 'ROLE_ADMIN', 'Ivan',
        'Kolanovic');
INSERT INTO user (email, password, roles, first_name, last_name)
VALUES ('user@rit.com', '$2a$10$.xaYSfiMMA1mWtZcNe9HjODlWtaOBoq6azpoEy445YFX8alxHPJJC', 'ROLE_USER', 'Pero', 'Peric');

-- Insert Author
INSERT INTO author (name, location, bio, dob, active)
VALUES ('H.P Lovecraft', 'Providence, Rhode Island, U.S',
        'Howard Phillips "H.P." Lovecraft was a professional writer from Providence, Rhode Island. He specialized in writing so-called "weird fiction", a term used for stories which combined elements from fantasy, horror fiction, and science fiction. He is primarily remembered as the founder of the Cthulhu Mythos, a shared fictional universe involving both his own works and those of several other writers who collaborated with him. His works were influenced by his adherence to the philosophy of Cosmicism, the belief that "humans are particularly insignificant in the larger scheme of intergalactic existence". He died when only 46-years-old, but continues to have an influence on modern fiction. The term "Lovecraftian horror" is used for modern works influenced by his ideas.',
        '1890-08-20', 1);

INSERT INTO author (name, location, bio, dob, active)
VALUES ('J. R. R. Tolkien', 'Bloemfontein, Orange Free State',
        'John Ronald Reuel Tolkien CBE FRSL (3 January 1892 – 2 September 1973) was an English writer, poet, philologist, and academic, best known as the author of the high fantasy works The Hobbit and The Lord of the Rings.',
        '1892-01-03', 1);

INSERT INTO author (name, location, bio, dob, active)
VALUES ('George Orwell', 'Motihari, Bengal Presidency, British India',
        'George Orwell was a novelist, essayist and critic best known for his novels Animal Farm and Nineteen Eighty-Four. He was a man of strong opinions who addressed some of the major political movements of his times, including imperialism, fascism and communism.',
        '1903-06-25', 1);

-- Insert book
INSERT INTO book (title, genre, description, author_id, borrowed_by, publish_date, available)
VALUES ('The Call of Cthulhu', 'Horror',
        'The story''s narrator, Francis Wayland Thurston, recounts his discovery of various notes left behind by his great uncle, George Gammell Angell, a prominent professor of Semitic languages at Brown University in Providence, Rhode Island, who died during the winter of 1926, suspecting eldritch goings-on after being bumped into by a sailor.',
        1, null, '1928-02-10', 1);
INSERT INTO book (title, genre, description, author_id, borrowed_by, publish_date, available)
VALUES ('From Beyond', 'Horror',
        'The story is told from the first-person perspective of an unnamed narrator and details his experiences with a scientist named Crawford Tillinghast. Tillinghast creates an electronic device that emits a resonance wave, which stimulates an affected person''s pineal gland, thereby allowing them to perceive planes of existence outside the scope of accepted reality.',
        1, null, '1934-01-01', 1);

INSERT INTO book (title, genre, description, author_id, borrowed_by, publish_date, available)
VALUES ('The Lord of the Rings: The Fellowship of the Ring', 'High Fantasy Action Drama',
        'An ancient Ring thought lost for centuries has been found, and through a strange twist of fate has been given to a small Hobbit named Frodo. When Gandalf discovers the Ring is in fact the One Ring of the Dark Lord Sauron, Frodo must make an epic quest to the Cracks of Doom in order to destroy it. However, he does not go alone. He is joined by Gandalf, Legolas the elf, Gimli the Dwarf, Aragorn, Boromir, and his three Hobbit friends Merry, Pippin, and Samwise. Through mountains, snow, darkness, forests, rivers and plains, facing evil and danger at every corner the Fellowship of the Ring must go. Their quest to destroy the One Ring is the only hope for the end of the Dark Lords reign.',
        2, null, '1954-07-29', 1);
INSERT INTO book (title, genre, description, author_id, borrowed_by, publish_date, available)
VALUES ('The Lord of the Rings: The Two Towers', 'High Fantasy Action Drama',
        'The continuing quest of Frodo and the Fellowship to destroy the One Ring. Frodo and Sam discover they are being followed by the mysterious Gollum. Aragorn, the Elf archer Legolas, and Gimli the Dwarf encounter the besieged Rohan kingdom, whose once great King Theoden has fallen under Saruman''s deadly spell.',
        2, null, '1954-11-11', 1);

INSERT INTO book (title, genre, description, author_id, borrowed_by, publish_date, available)
VALUES ('Nineteen Eighty-Four (1984)', 'Dystopian political fiction social science fiction',
        'In 1984, civilisation has been ravaged by world war, civil conflict, and revolution. Airstrip One (formerly known as Great Britain) is a province of Oceania, one of the three totalitarian super-states that rule the world. It is ruled by "The Party" under the ideology of "Ingsoc" (a Newspeak shortening of "English Socialism") and the mysterious leader Big Brother, who has an intense cult of personality. The Party brutally purges out anyone who does not fully conform to their regime, using the Thought Police and constant surveillance through telescreens (two-way televisions), cameras, and hidden microphones. Those who fall out of favour with the Party become "unpersons", disappearing with all evidence of their existence destroyed.',
        3, null, '1949-06-08', 1);
INSERT INTO book (title, genre, description, author_id, borrowed_by, publish_date, available)
VALUES ('Animal Farm', 'Political satire',
        'The poorly-run Manor Farm near Willingdon, England, is ripened for rebellion from its animal populace by neglect at the hands of the irresponsible and alcoholic farmer, Mr. Jones. One night, the exalted boar, Old Major, holds a conference, at which he calls for the overthrow of humans and teaches the animals a revolutionary song called "Beasts of England". When Old Major dies, two young pigs, Snowball and Napoleon, assume command and stage a revolt, driving Mr. Jones off the farm and renaming the property "Animal Farm". They adopt the Seven Commandments of Animalism, the most important of which is, "All animals are equal". The decree is painted in large letters on one side of the barn. Snowball teaches the animals to read and write, while Napoleon educates young puppies on the principles of Animalism. To commemorate the start of Animal Farm, Snowball raises a green flag with a white hoof and horn. Food is plentiful, and the farm runs smoothly. The pigs elevate themselves to positions of leadership and set aside special food items, ostensibly for their personal health. Following an unsuccessful attempt by Mr. Jones and his associates to retake the farm (later dubbed the "Battle of the Cowshed"), Snowball announces his plans to modernise the farm by building a windmill. Napoleon disputes this idea, and matters come to head, which culminate in Napoleon''s dogs chasing Snowball away and Napoleon declaring himself supreme commander.',
        3, null, '1945-08-17', 1);
