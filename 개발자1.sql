create table food (
 food_id number primary key,
 food_name varchar2(20) not null,
 food_price number not null,
 food_script varchar2(50));
 
  create table employee (
 emp_id varchar2(20) primary key,
 emp_pw varchar2(20) not null,
 emp_name varchar2(20) not null);
 
  INSERT INTO FOOD VALUES ((select MAX(food_id)+1 from food), '탕후루', 3000, '딸기');
 
  insert into food
 values (1, '라면', 3500, '안성탕면');
 
  insert into employee 
 values ('minju', 'minju', '송민주');
 
 select * from food;
 commit;
 
 DELETE FROM food WHERE food_name = '와플';
 
 update food set food_sell = 0 where food_id = 3;
 
 ALTER TABLE food ADD(food_sell NUMBER); 
 
 update food set food_price = 5000, food_script = '신라면' where food_name = '라면';
 
 select * from employee;
 
 create table orders (
 order_id number primary key,
 order_table number not null,
 order_people number not null,
 order_price number,
 FOREIGN KEY (order_food) REFERENCES FOOD (FOOD_ID),
 FOREIGN KEY (order_emp) REFERENCES employee (emp_id)
 );