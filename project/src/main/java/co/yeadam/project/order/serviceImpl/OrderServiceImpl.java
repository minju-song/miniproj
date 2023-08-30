package co.yeadam.project.order.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yeadam.project.common.DataSource;
import co.yeadam.project.order.mapper.OrderMapper;
import co.yeadam.project.order.service.OrderService;
import co.yeadam.project.order.service.OrderVO;

public class OrderServiceImpl implements OrderService{
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private OrderMapper map = sqlSession.getMapper(OrderMapper.class);
	
	@Override
	public List<OrderVO> orderSelectList() {
		return map.orderSelectList();
	}

	@Override
	public OrderVO orderSelect(OrderVO vo) {
		return map.orderSelect(vo);
	}

	@Override
	public int orderInsert(OrderVO vo) {
		return map.orderInsert(vo);
	}

	@Override
	public int orderDelete(OrderVO vo) {
		return map.orderDelete(vo);
	}

	@Override
	public int orderIdSelect() {
		return map.orderIdSelect();
	}

	@Override
	public int orderSetPrice(OrderVO vo) {
		return map.orderSetPrice(vo);
	}

	@Override
	public int orderUpdateStatus(OrderVO vo) {
		return map.orderUpdateStatus(vo);
	}

	@Override
	public int orderSellSelect() {
		return map.orderSellSelect();
	}
	
}
