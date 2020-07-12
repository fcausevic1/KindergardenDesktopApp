BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `persons` (
    id INTEGER CONSTRAINT person_pk PRIMARY KEY AUTOINCREMENT,
    first_name TEXT,
    last_name TEXT,
    address TEXT,
    jmbg TEXT,
    place_of_birth TEXT,
    date_of_birth DATE,
    gender INTEGER
);
CREATE TABLE IF NOT EXISTS `teachers` (
    id INTEGER CONSTRAINT teacher_pk PRIMARY KEY AUTOINCREMENT,
    person_id INTEGER,
    telephone TEXT,
    email TEXT,
    FOREIGN KEY (person_id) REFERENCES persons(id)
);
CREATE TABLE IF NOT EXISTS `parents` (
    id INTEGER CONSTRAINT parent_pk PRIMARY KEY AUTOINCREMENT,
    person_id INTEGER,
    telephone TEXT,
    email TEXT,
    FOREIGN KEY (person_id) REFERENCES persons(id)
);
CREATE TABLE IF NOT EXISTS `grades` (
    id INTEGER CONSTRAINT grade_pk PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    teacher_id INTEGER,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);
CREATE TABLE IF NOT EXISTS `children` (
    id INTEGER CONSTRAINT parent_pk PRIMARY KEY AUTOINCREMENT,
    person_id INTEGER,
    first_parent_id INTEGER,
    second_parent_id INTEGER,
    grade_id INTEGER,
    FOREIGN KEY (person_id) REFERENCES persons(id),
    FOREIGN KEY (grade_id) REFERENCES  grade(id),
    FOREIGN KEY (first_parent_id) REFERENCES parents(id),
    FOREIGN KEY (second_parent_id) REFERENCES parents(id)
);
CREATE TABLE IF NOT EXISTS `activities` (
    id INTEGER CONSTRAINT activity_pk PRIMARY KEY AUTOINCREMENT,
    assignment TEXT,
    description TEXT,
    review TEXT,
    lecture_date DATE,
    teacher_id INTEGER,
    child_id INTEGER,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id),
    FOREIGN KEY (child_id) REFERENCES children(id)
);
INSERT INTO persons VALUES (1, "Mujo", "Mujic", "Zmaja od Bosne", "12345678901", "Sarajevo", 321532400000, 0);
INSERT INTO persons VALUES (2, "Fata", "Fatic", "Titova", "23123123123", "Sarajevo", 321532400000, 1);
INSERT INTO persons VALUES (3, "Haso", "Hasic", "ZAVNOBIH-a", "34534534545", "Zenica", 321532400000, 0);
INSERT INTO persons VALUES (4, "Pero", "Peric", "Sarajevska", "12345678901", "Zenica", 321532400000, 0);
INSERT INTO persons VALUES (5, "Alma", "Almic", "Rudarska", "12345678901", "Tuzla", 321532400000, 1);
INSERT INTO persons VALUES (6, "Selma", "Selmic", "Marsala Tita", "12345678901", "Tuzla", 321532400000, 1);
INSERT INTO persons VALUES (7, "Fikret", "Fikretic", "Obala Kulina bana", "12345678901", "Sarajevo", 321532400000, 0);
INSERT INTO persons VALUES (8, "Sulejman", "Sulejmanic", "Bulevar Mese Selimovica", "12345678901", "Sarajevo", 321532400000, 0);
INSERT INTO persons VALUES (9, "Ivana", "Ivanovic", "ZAVNOBIH-a", "12345678901", "Zenica", 321532400000, 1);
INSERT INTO persons VALUES (10, "Zlatko", "Hasic", "Armije BiH", "12345678901", "Zenica", 321532400000, 0);
INSERT INTO persons VALUES (11, "Vesna", "Peric", "1. tuzlanske brigade", "12345678901", "Tuzla", 321532400000, 1);
INSERT INTO persons VALUES (12, "Belma", "Fikretic", "Obala Kulina bana", "12345678901", "Sarajevo", 321532400000, 1);
INSERT INTO persons VALUES (13, "Semra", "Sulejmanic", "Bulevar Mese Selimovica", "12345678901", "Sarajevo", 321532400000, 1);
INSERT INTO persons VALUES (14, "Jasna", "Hasic", "ZAVNOBIH-a", "12345678901", "Zenica", 321532400000, 1);
INSERT INTO persons VALUES (15, "Elma", "Peric", "Sarajevska", "12345678901", "Zenica", 321532400000, 1);
INSERT INTO persons VALUES (16, "Ana", "Fikretic", "Bosne Srebrene", "12345678901", "Tuzla", 321532400000, 1);
INSERT INTO persons VALUES (17, "Haris", "Sulejmanic", "Bosne Srebrene", "12345678901", "Tuzla", 321532400000, 0);
INSERT INTO persons VALUES (18, "Bakir", "Selmic", "Bosne Srebrene", "12345678901", "Tuzla", 321532400000, 0);
INSERT INTO teachers VALUES (1, 1, "000/000-000", "mujo.mujic@gmail.com");
INSERT INTO teachers VALUES (2, 2, "111/111-111", "fata.fatic@gmail.com");
INSERT INTO teachers VALUES (5, 5, "222/222-333", "alma.almic@gmail.com");
INSERT INTO teachers VALUES (9, 9, "777/123-321", "ivana.ivanic@gmail.com");
INSERT INTO parents VALUES (3, 3, "555/555-555", "haso.hasic@gmail.com");
INSERT INTO parents VALUES (4, 4, "666/666-666", "pero.peric@gmail.com");
INSERT INTO parents VALUES (6, 6, "444/444-444", "selma.selmic@gmail.com");
INSERT INTO parents VALUES (7, 7, "777/777-777", "fikret.fikretic@gmail.com");
INSERT INTO parents VALUES (8, 8, "888/888-888", "sulejman.sulejmanic@gmail.com");
INSERT INTO parents VALUES (14, 14, "999/999-999", "jasna.hasic@gmail.com");
INSERT INTO parents VALUES (15, 15, "111/000-222", "elma.peric@gmail.com");
INSERT INTO parents VALUES (12, 12, "333/444-111", "belma.fikretic@gmail.com");
INSERT INTO parents VALUES (13, 13, "555/333-111", "semra.sulejmanic@gmail.com");
INSERT INTO grades VALUES (1, "First Grade", 1);
INSERT INTO grades VALUES (2, "Second Grade", 2);
INSERT INTO grades VALUES (3, "Third Grade", 5);
INSERT INTO children VALUES (10, 10, 3, 14, 1);
INSERT INTO children VALUES (11, 11, 4, 15, 1);
INSERT INTO children VALUES (16, 16, 7, 12, 2);
INSERT INTO children VALUES (17, 17, 8, 13, null);
INSERT INTO children VALUES (18, 18, 6, null , null);
COMMIT;