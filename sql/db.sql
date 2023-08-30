ALTER TABLE food ADD food_sell number;
ALTER TABLE orders ADD status varchar2(10);
ALTER TABLE orders RENAME COLUMN status TO order_status;
ALTER TABLE employee ADD emp_phone varchar2(20);
ALTER TABLE employee ADD emp_level varchar2(20);
ALTER TABLE employee ADD hire_date date default sysdate;
ALTER TABLE employee DROP COLUMN emp_order;
ALTER TABLE food DROP COLUMN food_sell;

update employee set emp_phone = '01082251849' where emp_name = '송민주';

select * from food;
select * from orders;
select * from employee order by hire_date;
select * from order_food;

//완료처리한 주문갯수
select count(*) from employee e JOIN orders o on e.emp_id = o.emp_id where order_status = 'comp' and o.emp_id='minju';
    
delete from orders where order_id = 2;

commit;

//데이터삭제
delete from orders where order_id = 1;
delete from food;
delete from orders;
delete from employee;

insert into employee values ('minju', 'minju', '송민주', '01082251849', 'king', sysdate);
//case문
SELECT CASE WHEN MAX(FOOD_ID) IS NULL THEN 1 ELSE MAX(FOOD_ID)+1 END AS FOOD_ID FROM FOOD;
//진행중인 주문조회
SELECT * FROM ORDERS where order_status = 'ing';
SELECT CASE WHEN MAX(ORDER_ID) IS NULL THEN 1 ELSE MAX(ORDER_ID)+1 END AS ORDER_ID FROM ORDERS;

//외래키 추가
alter table orders add FOREIGN KEY (emp_id) references employee(emp_id) on delete set null;

//테이블생성
create table orders (
order_id number primary key,
order_table number,
order_people number,
order_price number,
emp_id varchar2(20),
FOREIGN KEY (emp_id) references employee(emp_id));

insert into food values (1, '라면', 4000, '안성탕면', 0);

insert into orders (order_id, order_table, order_people, emp_id) values (1, 1, 2, 'minju');
drop table order_food;
delete from employee where emp_id = 'emp' and emp_name = '직원1';



create table order_food (
order_id number,
food_id number,
primary key (order_id, food_id),
foreign key (order_id) references orders (order_id) on delete set null,
foreign key (food_id) references food (food_id) on delete set null);




insert into order_food values (1,1);
SELECT MAX(ORDER_ID)+1 FROM ORDERS;

INSERT INTO ORDERS (ORDER_ID, ORDER_TABLE, ORDER_PEOPLE, EMP_ID, ORDER_STATUS) VALUES (#{orderId}, #{orderTable}, #{orderPeople}, #{empId}, 'ing');

update orders o
set order_price = (
    select sum(f.food_price)
    from food f
    join order_food ofd on f.food_id = ofd.food_id
    where ofd.order_id = o.order_id
)
where order_id = 1;


delete from orders where order_status = 'ing';

delete from orders where order_id = 1;

update orders
set order_price = 5000
where order_id=3;

truncate table orders;

update orders
set order_status = 'ing';
select * from orders;

select emp_name ,(SELECT COUNT(*) 
                        FROM EMPLOYEE e JOIN ORDERS o ON e.EMP_ID = o.EMP_ID 
                        WHERE ORDER_STATUS = 'comp'
                        and o.emp_id = e.emp_id) as sell
from employee;

SELECT COUNT(*) 
                        FROM EMPLOYEE e JOIN ORDERS o ON e.EMP_ID = o.EMP_ID 
                        WHERE ORDER_STATUS = 'comp';
                        
SELECT emp_id, (select COUNT(*) FROM EMPLOYEE e JOIN ORDERS o ON e.EMP_ID = o.EMP_ID WHERE ORDER_STATUS = 'comp' and o.emp_id='emp') as sell
from employee
where emp_id='emp';

select COUNT(*) FROM EMPLOYEE e JOIN ORDERS o ON e.EMP_ID = o.EMP_ID WHERE ORDER_STATUS = 'comp' and o.emp_id='emp';

COUNT(*) FROM EMPLOYEE e JOIN ORDERS o ON e.EMP_ID = o.EMP_ID WHERE ORDER_STATUS = 'comp' and o.emp_id='minju';

select sum(order_price) from orders where order_status = 'comp';
SELECT * FROM ORDERS where order_status = 'comp' order by order_id;
SELECT * FROM ORDERS WHERE ORDER_STATUS = 'ing';
