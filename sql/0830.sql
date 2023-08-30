commit;
select * from employee;
select * from food;
select * from order_food;
select * from orders; 
select * from review;
delete employee;
create table review (
review_id number primary key,
review_date date default sysdate,
review_content varchar2(50),
food_id number,
FOREIGN KEY (food_id) references food(food_id) on delete cascade);

select * from review where food_id = 1;

select food_name,(select count(*) 
                    from order_food ofd 
                    join orders o on ofd.order_id = o.order_id
                    where f.food_id = ofd.food_id
                    and o.order_status = 'comp') as sell 
		from food f order by sell desc;