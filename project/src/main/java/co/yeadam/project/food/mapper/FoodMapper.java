package co.yeadam.project.food.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.yeadam.project.food.service.FoodVO;
import co.yeadam.project.orderFood.service.OrderFoodVO;

public interface FoodMapper {
	List<FoodVO> foodSelectList();
	FoodVO foodSelect(FoodVO vo);
	int foodInsert(FoodVO vo);
	int foodDelete(FoodVO vo);
	int foodUpdate(FoodVO vo);
	List<FoodVO> foodListbyOrder(OrderFoodVO vo);
	List<Map<String, Object>> foodSell();
}
