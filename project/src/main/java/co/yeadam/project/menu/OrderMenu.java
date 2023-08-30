package co.yeadam.project.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yeadam.project.employee.service.EmployeeVO;
import co.yeadam.project.food.service.FoodService;
import co.yeadam.project.food.service.FoodVO;
import co.yeadam.project.food.serviceImpl.FoodServiceImpl;
import co.yeadam.project.order.serviceImpl.OrderServiceImpl;
import co.yeadam.project.order.service.OrderService;
import co.yeadam.project.order.service.OrderVO;
import co.yeadam.project.orderFood.service.OrderFoodVO;
import co.yeadam.project.orderFood.serviceImpl.OrderFoodServiceImpl;
import co.yeadam.project.orderFood.service.OrderFoodService;

public class OrderMenu {
	Scanner sc = new Scanner(System.in);
	FoodService daoF = new FoodServiceImpl();
	OrderService daoO = new OrderServiceImpl();
	OrderFoodService daoOF = new OrderFoodServiceImpl();
	
	//주문관리채널 시작
	public void run(EmployeeVO emp) {
		System.out.println();
		System.out.println("------------------------------------------[주문 관리 메뉴]------------------------------------------");
		boolean run = true;
		while(run) {
			System.out.println();
			System.out.println("================================================================================================");
			System.out.println("1.주문등록   2.진행중인 주문   3.주문 상세조회   4.주문완료처리   5.주문취소처리   6.주문삭제   7.매출조회   8.뒤로가기");
			System.out.println("================================================================================================");
			System.out.print("선택>> ");
			int s = sc.nextInt();
			switch(s) {
			case 1:
				addOrder();
				break;
			case 2:
				showOrder();
				break;
			case 3:
				searchOrder();
				break;
			case 4:
				complOrder(emp);
				break;
			case 5:
				cancelOrder();
				break;
			case 6:
				deleteOrder();
				break;
			case 7:
				salesOrder();
				break;
			case 8:
				run = false;
			}
		}
	}
	
	//주문등록
	public void addOrder() {
		OrderVO order = new OrderVO();
		OrderFoodVO of = new OrderFoodVO();
		List<FoodVO> foods = new ArrayList<>();
		
		//사용자에게 입력받음
		System.out.print("테이블 번호>> ");
		int table = sc.nextInt();
		System.out.print("인원수>> ");
		int people = sc.nextInt();
		//주문담당직원ID불러와서 저장
		order.setOrderPeople(people);
		order.setOrderTable(table);
		//현재 넣어야할 주문번호 불러옴
		int id = daoO.orderIdSelect();
		order.setOrderId(id);
		//주문객체 db에 저장
		daoO.orderInsert(order);
		
		System.out.println("------------------------------------");
		//현재 주문할 수 있는 메뉴 출력
		foods = daoF.foodSelectList();
		for(FoodVO f : foods) {
			System.out.println("메뉴번호:"+f.getFoodId()+" \t이름:"+f.getFoodName()+" \t 가격:"+f.getFoodPrice());
		}
		System.out.println("------------------------------------");
		
		int sum = 0;
		//주문메뉴 사용자에게 입력받음 (인원수만큼)
		System.out.println("원하는 메뉴 번호 입력 (1인 1메뉴)");
		for(int i=0; i<people; i++) {
			System.out.print("입력>> ");
			int menu = sc.nextInt();
			of.setOrderId(id);
			of.setFoodId(menu);
			//주문번호와 메뉴번호를 orderfood테이블에 저장
			int r = daoOF.orderFoodInsert(of);
			sum += r;
		}
		
		if(sum == people) {
			System.out.println();
			System.out.println(order.getOrderId()+"번 주문 완료");
		}
		
		//주문넣은 후 마지막에 금액계산
		int p = daoO.orderSetPrice(order);
		order = daoO.orderSelect(order);
		if(p == 1) {
			System.out.println("주문 합계 금액: "+order.getOrderPrice());
		}
	
	}
	
	//현재 진행중인 주문 리스트 출력
	private void showOrder() {
		List<OrderVO> orders = new ArrayList<>();
		orders = daoO.orderSelectList();
		
		for(OrderVO o : orders) {
			System.out.println("주문번호:"+o.getOrderId()+" \t테이블번호:"+o.getOrderTable()+" \t 금액:"+o.getOrderPrice());
		}
		
	}
	
	//주문상세조회
	public void searchOrder() {
		System.out.print("조회할 주문번호 입력>> ");
		int num = sc.nextInt();
		
		OrderVO vo = new OrderVO();
		//주문메뉴출력위해 orderfood테이블필요
		OrderFoodVO vo2 = new OrderFoodVO();
		vo.setOrderId(num);
		vo2.setOrderId(num);
		vo = daoO.orderSelect(vo);
		
		if(vo == null) System.out.println("없는 주문번호입니다.");
		else {			
			System.out.println();
			System.out.println("[주문번호:"+vo.getOrderId()+"]");
			System.out.println("테이블번호:"+vo.getOrderTable()+"\t인원수:"+vo.getOrderPeople());
			
			//주문메뉴출력
			List<FoodVO> foods = new ArrayList<>();
			foods = daoF.foodListbyOrder(vo2);
			System.out.print("주문한 메뉴: ");
			for(FoodVO f : foods) {
				System.out.print(f.getFoodName()+" ");
			}
			System.out.println();
			
			//총금액 출력
			System.out.println("총 금액:"+vo.getOrderPrice());
			
			if(vo.getOrderStatus().equals("ing")) System.out.println("-----진행중-----");
			else if(vo.getOrderStatus().equals("comp")) System.out.println("-----완료-----");
			else System.out.println("-----취소-----");
			System.out.println();
		}
		
		
	}
	
	//주문완료처리
	private void complOrder(EmployeeVO emp) {
		System.out.print("완료처리할 주문번호 입력>> ");
		int num = sc.nextInt();
		OrderVO vo = new OrderVO();
		
		vo.setOrderId(num);
		vo.setEmpId(emp.getEmpId());
		//주문의 status컬럼을 ing->comp
		vo.setOrderStatus("comp");
		int r = daoO.orderUpdateStatus(vo);
		System.out.println();
		if(r == 1) System.out.println(vo.getOrderId()+"번 주문이 완료처리되었습니다.");
		else System.out.println("없는 주문번호입니다.");
	}
	
	//주문취소처리
	private void cancelOrder() {
		System.out.print("취소처리할 주문번호 입력>> ");
		int num = sc.nextInt();
		OrderVO vo = new OrderVO();
		
		vo.setOrderId(num);
		//주문의 status컬럼을 ing->canc
		vo.setOrderStatus("canc");
		int r = daoO.orderUpdateStatus(vo);
		System.out.println();
		if(r == 1) System.out.println(vo.getOrderId()+"번 주문이 취소처리되었습니다.");
		else System.out.println("없는 주문번호입니다.");
		
	}
	
	//주문삭제
	private void deleteOrder() {
		System.out.print("삭제할 주문번호 입력>> ");
		int num = sc.nextInt();
		OrderVO o = new OrderVO();
//		OrderFoodVO of = new OrderFoodVO();
		
		o.setOrderId(num);
//		of.setOrderId(num);
//		int b = daoOF.orderFoodDelete(of);
		int a = daoO.orderDelete(o);
		
		if(a == 1 ) System.out.println("주문이 영구삭제되었습니다.");
		else System.out.println("없는 주문입니다.");
		
	}

	
	//현재판매금액 조회
	private void salesOrder() {
		//status가 comp인 주문만 계산
		int sell = daoO.orderSellSelect();
		System.out.println();
		System.out.println("총 매출액:"+sell+"원");
		
		
	}




}
