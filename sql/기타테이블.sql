drop table employee;
drop table food;

commit;

create table employee (
emp_id varchar2(20) primary key,
emp_pw varchar2(20) not null,
emp_name varchar2(20) not null,
emp_phone varchar2(20),
emp_level varchar2(20),
hire_date date default sysdate);

create table orders (
order_id number primary key,
order_table number,
order_people number,
order_price number,
order_status varchar2(10),
emp_id varchar2(20),
FOREIGN KEY (emp_id) references employee(emp_id) on delete set null);

create table food(
food_id number primary key,
food_name varchar2(20) not null,
food_price number not null,
food_script varchar2(50));

select * from food;
select * from orders;
select * from order_food;
commit;



create table order_food (
order_id number,
food_id number,
primary key (order_id, food_id),
foreign key (order_id) references orders (order_id) on delete cascade,
foreign key (food_id) references food (food_id) on delete cascade);
