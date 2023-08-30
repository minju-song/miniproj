package co.yeadam.project.menu;

import java.util.ArrayList;
import co.yeadam.project.common.SHA256;
import java.util.List;
import java.util.Scanner;

import co.yeadam.project.employee.service.EmployeeService;
import co.yeadam.project.employee.service.EmployeeVO;
import co.yeadam.project.employee.serviceImpl.EmployeeServiceImpl;

public class LoginMenu {
	SHA256 sha256 = new SHA256();
	static Scanner sc = new Scanner(System.in);
	EmployeeService dao = new EmployeeServiceImpl();
	FoodMenu fm = new FoodMenu();
	OrderMenu om = new OrderMenu();
	MainMenu mm = new MainMenu();
	EmployeeVO emp = new EmployeeVO();
	
	//시작메뉴
	public void run() {
		List<EmployeeVO> e = new ArrayList<>();
		e = dao.employeeSelectList();
		
		//가입되어있는 직원이 없다면 회원가입으로 이동 (첫 가입은 무조건 사장)
		if(e.size()==0) singIn();
		
		
		boolean ck = true;
		while(ck) {
			System.out.println("=============*======*=============");
			System.out.println("  로그인 (손님이면 ID에 손님이라고 입력)  ");
			System.out.println("=============*======*=============");
			//로그인하여 emp불러옴
			emp = checkLogin();
			if(emp != null) {
				ck = false;
			}
		}
		//emp의 직급이 king이면 사장메뉴, 그 외엔 직원메뉴, 고객이라면 고객메뉴
		if(emp.getEmpLevel().equals("king")) mm.kingMenu(emp);
		else if(emp.getEmpLevel().equals("cli")) mm.clientMenu(emp);
		else mm.empMenu(emp);

	}
	
	//회원가입
	private void singIn() {
		System.out.println("회원가입을 진행해주세요.");
		System.out.print("아이디 입력>> ");
		String id = sc.next();
		System.out.print("비밀번호 입력>> ");
		String pw = sc.next();
		pw = sha256.encrypt(pw);
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
		EmployeeVO emp = new EmployeeVO();
		
		
		System.out.print("ID>> ");
		String id = sc.next();
		
		//손님
		if (id.equals("손님")) {
//			System.out.println("고객님 안녕하세요");
			emp.setEmpLevel("cli");
			return emp;
		}
		
		//회원
		System.out.print("PW>> ");
		String pw = sc.next();
		pw = sha256.encrypt(pw);
		emp.setEmpId(id);
		emp.setEmpPw(pw);
		
		//해당하는 id와 pw를 가진 employee불러옴
		emp = dao.employeeSelect(emp);
		if(emp == null) {
			System.out.println("해당 계정이 없습니다.");
			return null;
		}
		else {
			if (emp.getEmpPw().equals(pw)) {
				System.out.println(emp.getEmpName()+"님 환영합니다.");
				return emp;
			}
			else {
				System.out.println("로그인 실패");
				return null;
			}
		}
		//없으면 로그인실패
		
	}
}
