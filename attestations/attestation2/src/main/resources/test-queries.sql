select * from customers
where id = 5;
select * from customers
where fio = 'Ярослав Зотов';
select * from products
where cost<1000;
select * from products
where amount>10;
select * from products
where description='Бейсболка';
select * from orders
where date_order>'2025-03-10';
delete from orders
where date_order<'2025-03-01';
select * from orders
where amount>1;
select * from orders
where amount=1;
update products
set cost = 3300, amount = 15
where description = 'Кроссовки Adidas';