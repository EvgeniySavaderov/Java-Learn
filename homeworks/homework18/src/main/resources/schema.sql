create table if not exists customers(
id bigserial primary key,
fio varchar
);

create table if not exists orders(
id bigserial primary key,
id_customer bigserial references customers,
date_order date,
amount integer,
discount integer
);
