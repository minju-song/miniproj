package co.yeadam.project.orderFood.service;

import java.util.List;



public interface OrderFoodService {
	List<OrderFoodVO> orderFoodSelectList();
	OrderFoodVO orderFoodSelect(OrderFoodVO vo);
	int orderFoodInsert(OrderFoodVO vo);
	int orderFoodUpdate(OrderFoodVO vo);
	int orderFoodDelete(OrderFoodVO vo);

}
