commit;

delete orders;

select * from employee;
select*from food;
select * from order_food;
select * from orders;
select * from review;

SELECT COUNT(*) FROM EMPLOYEE e JOIN ORDERS o ON e.EMP_ID = o.EMP_ID WHERE ORDER_STATUS = 'comp' AND o.EMP_ID='king';

SELECT NVL(SUM(ORDER_PRICE), 0) FROM ORDERS WHERE ORDER_STATUS = 'comp';



//직원
create table employee (
emp_id varchar2(20) primary key,
emp_pw varchar2(512) not null,
emp_name varchar2(20) not null,
emp_phone varchar2(20),
emp_level varchar2(20),
hire_date date default sysdate)

//메뉴
create table food {
food_id number primary key,
food_name varchar2(20) not null,
food_price number not null,
food_script varchar2(50) )

//주문
create table orders (
order_id number primary key,
order_table number,
order_people number,
order_price number,
order_status varchar2(10),
emp_id varchar2(20),
FOREIGN KEY (emp_id) references employee(emp_id) on delete set null);

//메뉴-주문
create table order_food (
order_id number,
food_id number,
primary key (order_id, food_id),
foreign key (order_id) references orders (order_id) on delete cascade,
foreign key (food_id) references food (food_id) on delete cascade);

//리뷰
create table review (
review_id number primary key,
review_date date default sysdate,
review_content varchar2(50),
food_id number,
FOREIGN KEY (food_id) references food(food_id) on delete cascade);