USE movie_theatre_spring;

INSERT INTO user (username, password, role, email)
VALUES ('admin', 'admin', 'ROLE_ADMIN', 'admin@gmail.com'),
       ('reni', 'reni', 'ROLE_USER', 'reni@gmail.com'),
       ('test', 'test', 'ROLE_USER', 'test@gmail.com');

INSERT INTO hall (name)
VALUES (1);

INSERT INTO seat (seat_row, place, hall_id)
VALUES (1, 1, 1),
       (1, 2, 1),
       (1, 3, 1),
       (1, 4, 1),
       (1, 5, 1),
       (1, 6, 1),
       (1, 7, 1),
       (1, 8, 1),
       (1, 9, 1),
       (2, 1, 1),
       (2, 2, 1),
       (2, 3, 1),
       (2, 4, 1),
       (2, 5, 1),
       (2, 6, 1),
       (2, 7, 1),
       (2, 8, 1),
       (2, 9, 1),
       (3, 1, 1),
       (3, 2, 1),
       (3, 3, 1),
       (3, 4, 1),
       (3, 5, 1),
       (3, 6, 1),
       (3, 7, 1),
       (3, 8, 1),
       (3, 9, 1),
       (4, 1, 1),
       (4, 2, 1),
       (4, 3, 1),
       (4, 4, 1),
       (4, 5, 1),
       (4, 6, 1),
       (4, 7, 1),
       (4, 8, 1),
       (4, 9, 1),
       (5, 1, 1),
       (5, 2, 1),
       (5, 3, 1),
       (5, 4, 1),
       (5, 5, 1),
       (5, 6, 1),
       (5, 7, 1),
       (5, 8, 1),
       (5, 9, 1);

INSERT INTO movie (title, directed_by, duration_minutes, trailer_url, background_img_url, cover_img_url)
VALUES ('Lobster', 'Yorgos Lanthimos', 116,
        'https://www.youtube.com/watch?v=vU29VfayDMw',
        'https://s3.amazonaws.com/bncore/wp-content/uploads/2016/06/288350.jpg',
        'https://bookshelf.ca/i/size/o/media--0665557f-463f-e93c-f1ed-ee05bae19537/w/600'),

       ('The Hobbit', 'Peter Jackson', 462,
        'https://www.youtube.com/watch?v=JTSoD4BBCJc',
        'https://www.xtrafondos.com/wallpapers/resized/bilbo-baggins-en-el-hobbit-3-99.jpg?s=large',
        'https://is1-ssl.mzstatic.com/image/thumb/Video69/v4/f4/28/dc/f428dca5-5b1d-172e-71f3-e129dd679ac5/pr_source.lsr/268x0w.png'),

       ('Eternal Sunshine of the Spotless Mind', 'Michel Gondry', 108,
        'https://www.youtube.com/watch?v=07-QBnEkgXU',
        'http://hd-kino.net/uploads/posts/2015-11/1447508640_5.jpg',
        'https://upload.wikimedia.org/wikipedia/ru/thumb/a/af/Eternal_Sunshine_of_the_Spotless_Mind.jpg/269px-Eternal_Sunshine_of_the_Spotless_Mind.jpg');

INSERT INTO movie_session (movie_id, hall_id, start_at, price)
VALUES (1, 1, '2019-12-07 10:30:00', 60),
       (1, 1, '2019-12-07 13:00:00', 80),
       (1, 1, '2019-12-07 16:30:00', 80),
       (2, 1, '2019-12-07 15:00:00', 80),
       (2, 1, '2019-12-07 19:00:00', 120),

       (1, 1, '2019-12-08 10:30:00', 60),
       (1, 1, '2019-12-08 13:00:00', 80),
       (2, 1, '2019-12-08 15:00:00', 80),
       (2, 1, '2019-12-08 19:00:00', 120);



