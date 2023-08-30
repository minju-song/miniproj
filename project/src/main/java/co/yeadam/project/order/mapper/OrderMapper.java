package co.yeadam.project.order.mapper;

import java.util.List;

import co.yeadam.project.order.service.OrderVO;

public interface OrderMapper {
	List<OrderVO> orderSelectList();
	OrderVO orderSelect(OrderVO vo);
	int orderInsert(OrderVO vo);
	int orderDelete(OrderVO vo);
	int orderIdSelect();
	int orderSetPrice(OrderVO vo);
	int orderUpdateStatus(OrderVO vo);
	int orderSellSelect();
}
