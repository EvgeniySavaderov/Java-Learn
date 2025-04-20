create table if not exists customers(
id bigserial primary key,
fio varchar
);

create table if not exists products(
id bigserial primary key,
description varchar,
cost integer,
amount integer
);

create table if not exists orders(
id_product bigserial references products,
id_customer bigserial references customers,
date_order date,
amount integer
);

comment on table customers is 'Список покупателей';
comment on table products is 'Список продуктов';
comment on table orders is 'Список заказов';

insert into customers (fio) values
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

insert into products(description, cost, amount) values
('Бумага офисная', 349, 1000),
('Витамин B', 258, 12),
('Телевизор Samsung', 27719, 4),
('Гель для стирки Ariel', 1198, 42),
('Пылесос Philips', 12096, 1),
('Часы наручные CIGA Design', 69177, 3),
('Кухонный стол', 27289, 15),
('Видеокарта Asus RTX 3060', 29699, 2),
('Бейсболка', 857, 28),
('Кроссовки Adidas', 3093, 10);

insert into orders values
(3, 1, '2025-03-12', 1),
(1, 2, '2025-03-09', 20),
(4, 3, '2025-03-10', 2),
(2, 4, '2025-03-20', 1),
(5, 5, '2025-03-15', 1),
(7, 6, '2025-02-28', 3),
(6, 7, '2025-03-05', 1),
(8, 9, '2025-03-10', 1),
(9, 8, '2025-02-27', 1),
(10, 10, '2025-03-15', 1);