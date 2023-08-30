package co.yeadam.project.food.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.yeadam.project.orderFood.service.OrderFoodVO;

public interface FoodService {
	List<FoodVO> foodSelectList();
	FoodVO foodSelect(FoodVO vo);
	int foodInsert(FoodVO vo);
	int foodDelete(FoodVO vo);
	int foodUpdate(FoodVO vo);
	List<FoodVO> foodListbyOrder(OrderFoodVO vo);
	List<Map<String, Object>> foodSell();
}
