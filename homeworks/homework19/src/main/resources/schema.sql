create table if not exists users(
id bigserial primary key,
fio varchar
);

insert into users (fio) values
('Евгений Владимиров'),
('Дмитрий Троицкий'),
('Арина Чернова'),
('Кирилл Аксенов'),
('Макар Исаев'),
('Виктория Новикова'),
('Ярослав Захаров'),
('Ярослав Зотов'),
('Дарья Сычева'),
('Софья Егорова');

create table if not exists orders(
id bigserial primary key,
id_customer bigserial references users,
date_order date,
amount integer,
discount integer
);

insert into orders(id_customer, date_order, amount, discount) values
(3, '2025-03-12', 1, 0),
(1, '2025-03-09', 20, 10),
(4, '2025-03-10', 2, 0),
(2, '2025-03-20', 1, 0),
(5, '2025-03-15', 1, 20),
(7, '2025-02-28', 3, 5),
(6, '2025-03-05', 1, 0),
(8, '2025-03-10', 1, 50),
(9, '2025-02-27', 1, 0),
(10, '2025-03-15', 1, 0);