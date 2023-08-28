package co.yeadam.project.food.mapper;

import java.util.List;

import co.yeadam.project.food.service.FoodVO;

public interface FoodMapper {
	List<FoodVO> foodSelectList();
	FoodVO foodSelect(FoodVO vo);
	int foodInsert(FoodVO vo);
	int foodDelete(FoodVO vo);
	int foodUpdate(FoodVO vo);
}
