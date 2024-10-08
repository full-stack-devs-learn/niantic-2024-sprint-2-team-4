DROP DATABASE IF EXISTS trivio;

CREATE DATABASE trivio;

USE trivio;

CREATE TABLE quiz
(
    quiz_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    quiz_title VARCHAR(255),
    is_live BOOLEAN DEFAULT 0,
    description TEXT(500)
);

CREATE TABLE question
(
    question_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL REFERENCES quiz(quiz_id),
    question_number INT NOT NULL,
    question_text VARCHAR(2000) NOT NULL
);

CREATE TABLE answer
(
    answer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL REFERENCES question(question_id),
    answer_text VARCHAR(2000) NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT 0
);

INSERT INTO quiz(quiz_id, quiz_title, is_live, description)
VALUES (1, 'RetroWit', 1, 'Test your knowledge of the world''s past with this comprehensive world history quiz! From ancient civilizations to modern times, this quiz covers pivotal events, influential figures, and major milestones that have shaped our global history. Whether you''re a history buff or just looking to brush up on your historical knowledge, this quiz offers a challenging and educational experience. Perfect for history enthusiasts and learners alike!')
     , (2, 'SmartyParty', 2, 'Challenge yourself with this engaging academic trivia quiz! Designed for those who love to test their knowledge across various subjects, this quiz covers a wide range of academic topics, including science, literature, mathematics, and more. Whether you''re a student, a teacher, or just a trivia aficionado, this quiz offers a fun and stimulating way to test your intellect and learn new facts. Ideal for quiz enthusiasts and trivia lovers of all ages!')
     , (3, 'Poké-Quiz GO', 1, 'Think you know everything about Pokémon Go? Test your knowledge of Pokémon Go with this fun quiz about the game that redefined mobile gaming and got us all moving! From the early days of catching your first Pikachu to the latest exciting updates, see how well you know the game that brought the world together! Do you have what it takes to be a true Pokémon Master?')
	 , (4, 'Lyriquizz', 1, 'Test your lyrical knowledge with Lyriquizz! From classic hits to today’s chart-toppers, see if you can match the lyrics to the right songs and artists. Perfect for music lovers of all genres!')
     , (5, 'Reel iQ', 1, 'How well do you know your movies? Prove your film expertise with Reel iQ! From Hollywood classics to recent blockbusters, this quiz challenges your knowledge of plots, quotes, and stars. Are you ready for the spotlight?')
     , (6, 'TV Showdown', 1, 'Are you a true TV buff? Dive into TV Showdown and put your binge-watching skills to the test! From iconic moments to character trivia, see if you have what it takes to be crowned the ultimate couch potato!')
     , (7, 'Swiftology', 1, 'Are you ready for it? Swiftology is the ultimate challenge for fans of Taylor Swift. From her earliest hits to the latest releases, journey through all of Taylors unforgettable eras and prove you"re the ultimate Swiftie?')
     , (8, 'Marvelous', 1, 'Step into the Marvel universe with this quiz that tests your knowledge of heroes, villains, and epic battles! From the Avengers to the Guardians, prove you"re the ultimate fan by acing this Marvel trivia!')
;

INSERT INTO question(question_id, quiz_id, question_number, question_text)
VALUES (1, 1, 1, 'Who was the first President of the United States?')
     , (2, 1, 2, 'What year did the Titanic sink?')
     , (3, 1, 3, 'Which empire was famously led by Genghis Khan?')
     , (4, 1, 4, 'The fall of the Berlin Wall occurred in which year?')
     , (5, 1, 5, 'Who was the British Prime Minister during World War II?')
     , (6, 2, 1, 'What is the chemical symbol for gold?')
     , (7, 2, 2, 'In which year did the United States declare its independence?')
     , (8, 2, 3, 'Who wrote the play Romeo and Juliet?')
     , (9, 2, 4, 'What is the formula for the area of a circle?')
     , (10, 2, 5, 'What is the powerhouse of the cell?')
     , (11, 3, 1, 'What year was Pokémon Go officially released?')
     , (12, 3, 2, 'What are the three teams players can choose from in Pokémon Go?')
     , (13, 3, 3, 'What is the primary purpose of PokéStops in the game?')
     , (14, 3, 4, 'Which Pokémon was the first to be featured in a Community Day event in Pokémon Go?')
     , (15, 3, 5, 'Which legendary bird Pokémon was the first to be made available for capture in Pokémon Go?')
     , (16, 3, 6, 'What is the maximum level a player can currently reach in Pokémon Go?')
     , (17, 3, 7, 'Which in-game item doubles the number of Pokémon candies you receive from catching Pokémon?')
     , (18, 3, 8, 'What is the term used for the large, cooperative battles against powerful Pokémon found at gyms?')
     , (19, 3, 9, 'What special feature was introduced in Pokémon Go during the "GO Beyond" update in late 2020?')
     , (20, 3, 10, 'Which region’s Pokémon were released first in Pokémon Go?')
     , (21, 3, 11, 'What is the maximum number of Pokémon a player can have in their storage after all possible storage upgrades?')
     , (22, 3, 12, 'Which Pokémon Go event introduced the "Shadow Pokémon" and the concept of "Purification"?')
     , (23, 3, 13, 'What was the first EX Raid Boss available in Pokémon Go?')
     , (24, 3, 14, 'How many different types of Lures are currently available in Pokémon Go, not including special event lures?')
     , (25, 3, 15, 'Which Pokémon was the first to have a special regional variant form released in Pokémon Go?')
     , (26, 4, 1, 'In Taylor Swift''s "Blank Space", what does she write in the blank space?')
     , (27, 4, 2, 'In Chappell Roan''s "Pink Pony Club," what city does she want to dance in?')
     , (28, 4, 3, 'Which Ariana Grande song mentions all of her exes by name?')
     , (29, 4, 4, 'In "Levitating" by Dua Lipa, what is both in the sky and in her eyes?')
     , (30, 4, 5, 'Which song by Lady Gaga has the lyric "I''m your biggest fan, I''ll follow you until you love me"?')
     , (31, 5, 1, 'In "The Godfather," who is the first character to say, "I''ll make him an offer he can''t refuse"?')
     , (32, 5, 2, 'What is the name of the computer that controls the spaceship in "2001: A Space Odyssey"?')
     , (33, 5, 3, 'Which famous line from "Casablanca" is often misquoted as "Play it again, Sam"?')
     , (34, 5, 4, 'In "The Wizard of Oz," what is the name of Dorothy''s dog?')
     , (35, 5, 5, 'What is the name of the shark-hunting boat in "Jaws"?')
     , (36, 6, 1, 'In "Seinfeld," what is the name of the coffee shop where the main characters frequently hang out?')
     , (37, 6, 2, 'In "It''s Always Sunny in Philadelphia," what is the name of the bar owned by the main characters?')
     , (38, 6, 3, 'In "Friends," what is the name of Ross and Monica''s dog that was supposedly sent to "the farm"?')
     , (39, 6, 4, 'What was the name of the fictional radio station in "Frasier"?')
     , (40, 6, 5, 'In "The Fresh Prince of Bel-Air," what was the nickname of Will’s best friend from Philadelphia?')
     , (41, 7, 1, 'Which Taylor Swift album was the first to win the Grammy for Album of the Year?')
     , (42, 7, 2, 'In which music video does Taylor Swift dress up as a nerdy version of herself for a school talent show?')
     , (43, 7, 3, 'What is the title of Taylor Swift’s documentary released on Netflix in 2020?')
     , (44, 7, 4, 'Which Taylor Swift song features the lyric "You made a rebel of a careless man’s careful daughter"?')
     , (45, 7, 5, 'What is the name of Taylor Swift’s debut single released in 2006?')
     , (46, 8, 1, 'In "Avengers: Endgame," which character wields both Mjolnir and Stormbreaker during the final battle?')
     , (47, 8, 2, 'What is the real name of the superhero known as Black Panther?')
     , (48, 8, 3, 'Which Marvel Cinematic Universe movie first introduced the character of Wanda Maximoff?')
     , (49, 8, 4, 'In "Guardians of the Galaxy," what is the name of Peter Quill’s ship?')
     , (50, 8, 5, 'Which Infinity Stone is hidden on Vormir and requires a sacrifice to obtain?')
;

INSERT INTO answer(question_id, answer_text, is_correct)
VALUES (1, 'Thomas Jefferson', 0)
     , (1, 'George Washington', 1)
     , (1, 'Abraham Lincoln', 0)
     , (1, 'John Adams', 0)

     , (2, '1912', 1)
     , (2, '1905', 0)
     , (2, '1923', 0)
     , (2, '1915', 0)

     , (3, 'Ottoman Empire', 0)
     , (3, 'Roman Empire', 0)
     , (3, 'Mongol Empire', 1)
     , (3, 'Persian Empire', 0)

     , (4, '1979', 0)
     , (4, '1985', 0)
     , (4, '1989', 1)
     , (4, '1991', 0)

     , (5, 'Neville Chamberlain', 0)
     , (5, 'Winston Churchill', 1)
     , (5, 'Margaret Thatcher', 0)
     , (5, 'Harold Wilson', 0)

     , (6, 'Au', 1)
     , (6, 'Ag', 0)
     , (6, 'Fe', 0)
     , (6, 'Pb', 0)

     , (7, '1776', 1)
     , (7, '1789', 0)
     , (7, '1801', 0)
     , (7, '1812', 0)

     , (8, 'Charles Dickens', 0)
     , (8, 'William Shakespeare', 1)
     , (8, 'Mark Twain', 0)
     , (8, 'Jane Austen', 0)

     , (9, '2πr', 0)
     , (9, 'πd', 0)
     , (9, 'πr²', 1)
     , (9, '2πd', 0)

     , (10, 'Nucleus', 0)
     , (10, 'Ribosome', 0)
     , (10, 'Endoplasmic reticulum', 0)
     , (10, 'Mitochondria', 1)
     
     , (11, '2015', 0)
     , (11, '2016', 1)
     , (11, '2014', 0)
     , (11, '2017', 0)
     
     , (12, 'Value, Spirit, Instinct', 0)
     , (12, 'Valor, Mystic, Intuition', 0)
     , (12, 'Valor, Mystic, Instinct', 1)
     , (12, 'Honor, Spirit, Intuition', 0)
     
     , (13, 'To catch Pokémon', 0)
     , (13, 'To heal Pokémon', 0)
     , (13, 'To spin and collect items', 1)
     , (13, 'To battle other trainers', 0)

     , (14, 'Pikachu', 1)
     , (14, 'Dratini', 0)
     , (14, 'Bulbasaur', 0)
     , (14, 'Mareep', 0)

     , (15, 'Articuno', 1)
     , (15, 'Zapdos', 0)
     , (15, 'Moltres', 0)
     , (15, 'Lugia', 0)

     , (16, '40', 0)
     , (16, '45', 0)
     , (16, '50', 1)
     , (16, '55', 0)

     , (17, 'Star Piece', 0)
     , (17, 'Lucky Egg', 0)
     , (17, 'Rare Candy', 0)
     , (17, 'Pinap Berry', 1)

     , (18, 'Gym Battle', 0)
     , (18, 'Raid Battle', 1)
     , (18, 'Mega Battle', 0)
     , (18, 'Challenge Battle', 0)

     , (19, 'Trainer battles', 0)
     , (19, 'Mega Evolutions', 0)
     , (19, 'Seasons and level cap increase', 1)
     , (19, 'AR mapping tasks', 0)

     , (20, 'Johto', 0)
     , (20, 'Sinnoh', 0)
     , (20, 'Kanto', 1)
     , (20, 'Hoenn', 0)

     , (21, '3,000', 0)
     , (21, '4,000', 0)
     , (21, '5,000', 1)
     , (21, '6,000', 0)

     , (22, 'Pokémon Go Fest 2018', 0)
     , (22, 'Team GO Rocket Invasion', 1)
     , (22, 'Ultra Bonus Event 2019', 0)
     , (22, 'Halloween Event 2017', 0)

     , (23, 'Mewtwo', 1)
     , (23, 'Deoxys', 0)
     , (23, 'Regigigas', 0)
     , (23, 'Darkrai', 0)

     , (24, '2', 0)
     , (24, '3', 0)
	 , (24, '4', 1)
     , (24, '5', 0)

     , (25, 'Alolan Raichu', 0)
     , (25, 'Galarian Ponyta', 0)
     , (25, 'Alolan Exeggutor', 1)
     , (25, 'Galarian Weezing', 0)

     , (26, 'Her lover’s name', 1)
     , (26, 'Her ex’s name', 0)
     , (26, 'Next', 0)
     , (26, 'A goodbye note', 0)

     , (27, 'Los Angeles', 1)
     , (27, 'New York City', 0)
     , (27, 'San Francisco', 0)
     , (27, 'Las Vegas', 0)

     , (28, '7 rings', 0)
     , (28, 'thank u, next', 1)
     , (28, 'Into You', 0)
     , (28, 'Dangerous Woman', 0)

     , (29, 'Sunlight', 0)
     , (29, 'Glitter', 1)
     , (29, 'The moonlight', 0)
     , (29, 'The Milky Way', 0)

     , (30, 'Born This Way', 0)
     , (30, 'Poker Face', 0)
     , (30, 'Paparazzi', 1)
     , (30, 'Bad Romance', 0)

     , (31, 'Vito Corleone', 1)
     , (31, 'Michael Corleone', 0)
     , (31, 'Sonny Corleone', 0)
     , (31, 'Tom Hagen', 0)

     , (32, 'HAL 9000', 1)
     , (32, 'WOPR', 0)
     , (32, 'R2-D2', 0)
     , (32, 'Mother', 0)

     , (33, 'Here’s looking at you, kid', 0)
     , (33, 'We’ll always have Paris', 0)
     , (33, 'Play it, Sam. Play "As Time Goes By"', 1)
     , (33, 'Of all the gin joints, in all the towns, in all the world', 0)

     , (34, 'Toto', 1)
     , (34, 'Scooby', 0)
     , (34, 'Buddy', 0)
     , (34, 'Lassie', 0)

     , (35, 'Orca', 1)
     , (35, 'Minnow', 0)
     , (35, 'Shark Hunter', 0)
     , (35, 'Poseidon', 0)

     , (36, 'Monk''s Café', 1)
     , (36, 'Central Perk', 0)
     , (36, 'Cafe Nervosa', 0)
     , (36, 'The Regal Beagle', 0)

     , (37, 'Paddy''s Pub', 1)
     , (37, 'Cheers', 0)
     , (37, 'Moe''s Tavern', 0)
     , (37, 'The Drunken Clam', 0)

     , (38, 'Chi-Chi', 1)
     , (38, 'Marcel', 0)
     , (38, 'Hugsy', 0)
     , (38, 'Clunkers', 0)

     , (39, 'KACL 780 AM', 1)
     , (39, 'WNYX 870 AM', 0)
     , (39, 'WKRP 780 AM', 0)
     , (39, 'KQED 870 AM', 0)

     , (40, 'Jess', 0)
     , (40, 'Carlton', 0)
     , (40, 'Jazz', 1)
     , (40, 'Marlon', 0)

     , (41, 'Fearless', 1)
     , (41, '1989', 0)
     , (41, 'Speak Now', 0)
     , (41, 'Red', 0)

     , (42, 'You Belong with Me', 1)
     , (42, 'Love Story', 0)
     , (42, 'Blank Space', 0)
     , (42, 'Look What You Made Me Do', 0)

     , (43, 'Miss Americana', 1)
     , (43, 'Reputation Stadium Tour', 0)
     , (43, 'The Long Pond Studio Sessions', 0)
     , (43, 'This Is Me Trying', 0)

     , (44, 'Mine', 1)
     , (44, 'All Too Well', 0)
     , (44, 'Begin Again', 0)
     , (44, 'Style', 0)

     , (45, 'Tim McGraw', 1)
     , (45, 'Teardrops on My Guitar', 0)
     , (45, 'Our Song', 0)
     , (45, 'Picture to Burn', 0)

     , (46, 'Thor', 1)
     , (46, 'Captain America', 0)
     , (46, 'Iron Man', 0)
     , (46, 'Hulk', 0)

     , (47, 'T''Challa', 1)
     , (47, 'M''Baku', 0)
     , (47, 'N''Jadaka', 0)
     , (47, 'Shuri', 0)

     , (48, 'Avengers: Age of Ultron', 1)
     , (48, 'Captain America: Civil War', 0)
     , (48, 'Doctor Strange', 0)
     , (48, 'Thor: Ragnarok', 0)

     , (49, 'Milano', 1)
     , (49, 'Benatar', 0)
     , (49, 'Astrella', 0)
     , (49, 'Star-Lord', 0)

     , (50, 'Soul Stone', 1)
     , (50, 'Reality Stone', 0)
     , (50, 'Mind Stone', 0)
     , (50, 'Power Stone', 0)
;