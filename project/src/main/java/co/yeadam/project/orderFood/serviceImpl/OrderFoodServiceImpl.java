package co.yeadam.project.orderFood.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yeadam.project.common.DataSource;
import co.yeadam.project.order.service.OrderVO;
import co.yeadam.project.orderFood.mapper.OrderFoodMapper;
import co.yeadam.project.orderFood.service.OrderFoodService;
import co.yeadam.project.orderFood.service.OrderFoodVO;

public class OrderFoodServiceImpl implements OrderFoodService {
	
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private OrderFoodMapper map = sqlSession.getMapper(OrderFoodMapper.class);

	@Override
	public OrderFoodVO orderFoodSelect(OrderFoodVO vo) {
		return map.orderFoodSelect(vo);
	}

	@Override
	public int orderFoodInsert(OrderFoodVO vo) {
		return map.orderFoodInsert(vo);
	}

}
