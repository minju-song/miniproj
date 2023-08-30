commit;
select * from orders order by order_id;
select * from employee;
select * from food;
select * from order_food;
select * from orders; 
select * from review;

//review 테이블
create table review (
review_id number primary key,
review_date date default sysdate,
review_content varchar2(50),
food_id number,
FOREIGN KEY (food_id) references food(food_id) on delete cascade);


delete food;
delete orders;
delete employee;

SELECT SUM(ORDER_PRICE) FROM ORDERS WHERE ORDER_STATUS = 'comp';


insert into review values ((SELECT CASE WHEN MAX(review_ID) IS NULL THEN 1 ELSE MAX(review_ID)+1 END AS review_ID FROM review), sysdate, #{reviewContent}, #{foodId})
SELECT CASE WHEN MAX(review_ID) IS NULL THEN 1 ELSE MAX(review_ID)+1 END AS review_ID FROM review;

select * from review;
drop table reply;

alter table food add reply_id number;
alter table food drop column reply_id;
select * from food;

insert into review values (1, sysdate, '안뇽', 1);

select * from review where food_id = 1;
