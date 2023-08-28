package co.yeadam.project.food.service;

import java.util.List;

public interface FoodService {
	List<FoodVO> foodSelectList();
	FoodVO foodSelect(FoodVO vo);
	int foodInsert(FoodVO vo);
	int foodDelete(FoodVO vo);
	int foodUpdate(FoodVO vo);

}
