package co.yeadam.project.menu;

import java.util.Scanner;

import co.yeadam.project.employee.service.EmployeeService;
import co.yeadam.project.employee.service.EmployeeVO;
import co.yeadam.project.employee.serviceImpl.EmployeeServiceImpl;

public class MainMenu {
	static Scanner sc = new Scanner(System.in);
	EmployeeService dao = new EmployeeServiceImpl();
	FoodMenu fm = new FoodMenu();
	
	public void run() {
		boolean ck = true;
		while(ck) {
			System.out.println("로그인");
			if(checkLogin() == true) {
				ck = false;
			}
		}
		
		System.out.println("1.음식메뉴 관리   2.주문관리");
		System.out.print("선택>> ");
		int menu = sc.nextInt();
		switch(menu) {
		case 1:
			fm.run();
			break;
		case 2:
			break;
		}
	}
	
	public boolean checkLogin() {
		System.out.print("ID>> ");
		String id = sc.next();
		System.out.print("PW>> ");
		String pw = sc.next();
		
		EmployeeVO emp = new EmployeeVO();
		emp.setEmpId(id);
		emp.setEmpPw(pw);
		
		emp = dao.employeeSelect(emp);
		if (emp == null) {
			System.out.println("로그인 실패");
			return false;
		}
		else {
			System.out.println(emp.getEmpName()+"님 환영합니다.");
			return true;
		}
		
	}
}
