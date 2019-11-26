INSERT INTO movie (title, directed_by, duration_minutes, trailer_url, background_img_url, cover_img_url)
VALUES ('Lobster', 'Yorgos Lanthimos', 116,
        'https://www.youtube.com/watch?v=vU29VfayDMw',
        'https://s3.amazonaws.com/bncore/wp-content/uploads/2016/06/288350.jpg',
        'https://bookshelf.ca/i/size/o/media--0665557f-463f-e93c-f1ed-ee05bae19537/w/600'),

       ('Eternal Sunshine of the Spotless Mind', 'Michel Gondry', 108,
        'https://www.youtube.com/watch?v=07-QBnEkgXU',
        'http://hd-kino.net/uploads/posts/2015-11/1447508640_5.jpg',
        'https://upload.wikimedia.org/wikipedia/ru/thumb/a/af/Eternal_Sunshine_of_the_Spotless_Mind.jpg/269px-Eternal_Sunshine_of_the_Spotless_Mind.jpg');

INSERT INTO movie_session (movie_id, hall_id, start_at, price)
VALUES (1, 1, '2019-11-26 10:30:00', 60),
       (1, 1, '2019-11-26 13:00:00', 80),
       (1, 2, '2019-11-26 16:30:00', 80),
       (2, 1, '2019-11-26 15:00:00', 80),
       (2, 1, '2019-11-26 19:00:00', 120),

       (1, 1, '2019-11-27 10:30:00', 60),
       (1, 1, '2019-11-27 13:00:00', 80),
       (2, 1, '2019-11-27 15:00:00', 80),
       (2, 1, '2019-11-27 19:00:00', 120);



