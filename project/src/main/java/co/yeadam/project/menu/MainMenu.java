package co.yeadam.project.menu;

import java.util.ArrayList;
import java.util.List;
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
		List<EmployeeVO> e = new ArrayList<>();
		e = dao.employeeSelectList();
		if(e.size()==0) singIn();
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
	
	//회원가입
	private void singIn() {
		System.out.println("회원가입을 진행해주세요.");
		System.out.print("아이디 입력>> ");
		String id = sc.next();
		System.out.print("비밀번호 입력>> ");
		String pw = sc.next();
		System.out.print("이름 입력>> ");
		String name = sc.next();
		System.out.print("연락처 입력>> ");
		String phone = sc.next();
		EmployeeVO e = new EmployeeVO(id,pw,name,phone);
		int r = dao.employeeInsertKing(e);		
		System.out.println();
		if (r == 1) System.out.println(e.getEmpName()+"님이 사장으로 등록되었습니다.");
		else System.out.println("등록에 실패하였습니다.");
		
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
