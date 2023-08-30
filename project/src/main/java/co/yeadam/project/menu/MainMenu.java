package co.yeadam.project.menu;

import java.util.Scanner;

import co.yeadam.project.employee.service.EmployeeService;
import co.yeadam.project.employee.service.EmployeeVO;
import co.yeadam.project.employee.serviceImpl.EmployeeServiceImpl;

public class MainMenu {
	static Scanner sc = new Scanner(System.in);
	EmployeeService dao = new EmployeeServiceImpl();
	FoodMenu fm = new FoodMenu();
	OrderMenu om = new OrderMenu();
	EmployeeMenu em = new EmployeeMenu();
	ReviewMenu rm = new ReviewMenu();
	EmployeeVO emp = new EmployeeVO();
	
	
	//직원메뉴
	public void empMenu(EmployeeVO emp) {
		System.out.println();
		System.out.println("-----------------[메인 메뉴]-----------------");
		boolean run = true;
		while(run) {
			System.out.println("===========================================");
			System.out.println("1.음식메뉴 관리   2.주문관리   3.마이페이지   4.종료");
			System.out.println("===========================================");
			System.out.print("선택>> ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				//음식메뉴로 이동
				fm.run();
				break;
			case 2:
				//주문메뉴로 이동
				om.run(emp);
				break;
			case 3:
				//마이페이지로 이동
				em.empRun(emp);
				break;
			case 4:
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}
	
	//사장메뉴
	public void kingMenu(EmployeeVO emp) {
		System.out.println();
		System.out.println("----------------[메인 메뉴]----------------");
		boolean run = true;
		while(run) {
			System.out.println("========================================");
			System.out.println("1.음식메뉴 관리   2.주문관리   3.직원관리  4.종료");
			System.out.println("========================================");
			System.out.print("선택>> ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				//음식메뉴로 이동
				fm.run();
				break;
			case 2:
				//주문메뉴로 이동
				om.run(emp);
				break;
			case 3:
				//직원관리로 이동
				em.kingRun(emp);
				break;
			case 4:
				run = false;
				break;
			}
		}
		System.out.println("프로그램 종료");
	}
	
	//고객메뉴 
	public void clientMenu(EmployeeVO emp) {
		System.out.println();
		System.out.println("----------------[고객 메뉴]----------------");
		boolean run = true;
		while(run) {
			System.out.println("=====================================");
			System.out.println("1.주문등록   2.주문정보   3.메뉴후기  4.종료");
			System.out.println("=====================================");
			System.out.print("선택>> ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				//주문메뉴 - 메뉴등록 이동
				om.addOrder();
				break;
			case 2:
				//주문메뉴 - 메뉴조회 이동
				om.searchOrder();
				break;
			case 3:
				//후기메뉴로 이동
				rm.run();
				break;
			case 4:
				run = false;
				break;
			}
		}
		System.out.println("프로그램 종료");
	}


	

}
