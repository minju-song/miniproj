select * from food;
select * from employee;
select * from orders;
select * from order_food;
create table orders (
order_id number primary key,
order_table number,
order_people number,
order_price number,
order_emp varchar2(20));

create table order_food (
order_id number,
food_id number,
primary key (order_id, food_id),
foreign key (order_id) references orders (order_id),
foreign key (food_id) references food (food_id));

insert into orders (order_id, order_table, order_people,order_emp)
values (1, 1, 2, (select emp_id from employee where emp_name = 'º€πŒ¡÷'));

insert into order_food (order_id, food_id)
values (1,1);

insert into order_food (order_id, food_id)
values (1,2);

select * from order_food where order_id = 1;
commit;
update orders o
set order_price = (
    select sum(f.food_price)
    from food f
    join order_food ofd on f.food_id = ofd.food_id
    where ofd.order_id = o.order_id
)
where order_id = 1;