package co.yeadam.project.order.service;

import lombok.Data;

@Data
public class OrderVO {
	private int orderId;
	private int orderTable;
	private int orderPeople;
	private int orderPrice;
	private String empId;
	private String orderStatus;
}
