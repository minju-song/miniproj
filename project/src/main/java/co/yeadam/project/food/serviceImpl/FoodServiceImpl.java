package co.yeadam.project.food.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import co.yeadam.project.common.DataSource;
import co.yeadam.project.food.mapper.FoodMapper;
import co.yeadam.project.food.service.FoodService;
import co.yeadam.project.food.service.FoodVO;
import co.yeadam.project.orderFood.service.OrderFoodVO;

public class FoodServiceImpl implements FoodService{
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private FoodMapper map = sqlSession.getMapper(FoodMapper.class);
	
	@Override
	public List<FoodVO> foodSelectList() {
		return map.foodSelectList();
	}

	@Override
	public FoodVO foodSelect(FoodVO vo) {
		return map.foodSelect(vo);
	}

	@Override
	public int foodInsert(FoodVO vo) {
		return map.foodInsert(vo);
	}

	@Override
	public int foodDelete(FoodVO vo) {
		return map.foodDelete(vo);
	}

	@Override
	public int foodUpdate(FoodVO vo) {
		return map.foodUpdate(vo);
	}

	@Override
	public List<FoodVO> foodListbyOrder(OrderFoodVO vo) {
		return map.foodListbyOrder(vo);
	}

	@Override
	public List<Map<String, Object>> foodSell() {
		return map.foodSell();
	}





}
