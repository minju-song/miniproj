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
	EmployeeVO emp = new EmployeeVO();
	
	//시작메뉴
	public void run() {
		
		boolean ck = true;
		while(ck) {
			System.out.println("=*======*=");
			System.out.println("  로그인");
			System.out.println("=*======*=");
			//로그인하여 emp불러옴
			emp = checkLogin();
			if(emp != null) {
				ck = false;
			}
		}
		//emp의 직급이 king이면 사장메뉴, 그 외엔 직원메뉴로 이동
		if(emp.getEmpLevel().equals("king")) em.kingMenu(emp);
		else em.empMenu(emp);

	}
	
	
	//로그인
	public EmployeeVO checkLogin() {
		System.out.print("ID>> ");
		String id = sc.next();
		System.out.print("PW>> ");
		String pw = sc.next();
		
		EmployeeVO emp = new EmployeeVO();
		emp.setEmpId(id);
		emp.setEmpPw(pw);
		
		//해당하는 id와 pw를 가진 employee불러옴
		emp = dao.employeeSelect(emp);
		//없으면 로그인실패
		if (emp == null) {
			System.out.println("로그인 실패");
			return null;
		}
		else {
			System.out.println(emp.getEmpName()+"님 환영합니다.");
			return emp;
		}
		
	}
}
