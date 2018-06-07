DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, calories_per_day) VALUES
  ('User', 'user@yandex.ru', '{noop}password', 2005),
  ('Admin', 'admin@gmail.com', '{noop}admin', 1900);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name) VALUES
  ('Restaurant1'),
  ('Restaurant2');

INSERT INTO dishes (name, date, price, restaurant_id) VALUES
  ('soup1', '2018-06-06', 500.99, 100002),
  ('salad1', '2018-06-06', 500.99, 100002),
  ('coffee1', '2018-06-06', 500.99, 100002),
  ('soup2', '2018-06-06', 500.99, 100003),
  ('salad2', '2018-06-06', 500.99, 100003),
  ('coffee2', '2018-06-06', 500.99, 100003),
  ('soup1', '2018-06-07', 500.99, 100002),
  ('salad1', '2018-06-07', 500.99, 100002),
  ('coffee1', '2018-06-07', 500.99, 100002),
  ('soup2', '2018-06-07', 500.99, 100003),
  ('salad2', '2018-06-07', 500.99, 100003),
  ('coffee2', '2018-06-07', 500.99, 100003);

INSERT INTO votes (date, restaurant_id, user_id) VALUES
  ('2018-06-06', 100002, 100000),
  ('2018-06-07', 100002, 100000),
  ('2018-06-06', 100002, 100001),
  ('2018-06-07', 100003, 100001);





