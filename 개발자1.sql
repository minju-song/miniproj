select * from member;
commit;

insert into member(member_id, member_password, member_name)
values ('minju', 'minju', 'º€πŒ¡÷');

commit;

create table food (
 food_id number primary key,
 food_name varchar2(20) not null,
 food_price number not null,
 food_script varchar2(50));
 
 select * from food;
 select * from employee;
 
 SELECT * FROM EMPLOYEE WHERE EMP_ID = 'minju' AND EMP_PW = 'minju';
 
 SELECT * FROM EMPLOYEE WHERE EMP_ID = 'minju';
 
 insert into employee 
 values ('minju', 'minju', 'º€πŒ¡÷');
 
 SELECT * FROM FOOD WHERE FOOD_NAME = '∂Û∏È';
 
 INSERT INTO FOOD VALUES ((select MAX(food_id)+1 from food), '≈¡»ƒ∑Á', 3000, 'µ˛±‚');
 
 select MAX(food_id)+1 from food;
 
 insert into food
 values (1, '∂Û∏È', 3500, 'æ»º∫≈¡∏È');
 
 create table employee (
 emp_id varchar2(20) primary key,
 emp_pw varchar2(20) not null,
 emp_name varchar2(20) not null);