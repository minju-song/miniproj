package co.yeadam.project.orderFood.mapper;

import java.util.List;


import co.yeadam.project.orderFood.service.OrderFoodVO;

public interface OrderFoodMapper {
	OrderFoodVO orderFoodSelect(OrderFoodVO vo);
	int orderFoodInsert(OrderFoodVO vo);
}
