package co.yeadam.project.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yeadam.project.employee.service.EmployeeService;
import co.yeadam.project.employee.service.EmployeeVO;
import co.yeadam.project.employee.serviceImpl.EmployeeServiceImpl;

public class EmployeeMenu {
	static Scanner sc = new Scanner(System.in);
	EmployeeService dao = new EmployeeServiceImpl();
	FoodMenu fm = new FoodMenu();
	OrderMenu om = new OrderMenu();
	EmployeeVO emp = new EmployeeVO();
	
	
	//직원메뉴
	public void empMenu(EmployeeVO emp) {
		boolean run = true;
		while(run) {			
			System.out.println("=======================================================");
			System.out.println("1.음식메뉴 관리   2.주문관리   3.정보수정   4.내정보 조회   5.종료");
			System.out.println("=======================================================");
			System.out.print("선택>> ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				fm.run();
				break;
			case 2:
				om.run(emp);
				break;
			case 3:
				updateInfo(emp);
				break;
			case 4:
				searchInfo(emp);
				break;
			case 5:
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}
	
	//사장메뉴
	public void kingMenu(EmployeeVO emp) {
		boolean run = true;
		while(run) {			
			System.out.println("==========================================================================");
			System.out.println("1.음식메뉴 관리   2.주문관리   3.직원추가   4.직원삭제   5.직원목록   6.직원순위   7.종료");
			System.out.println("==========================================================================");
			System.out.print("선택>> ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				fm.run();
				break;
			case 2:
				om.run(emp);
				break;
			case 3:
				addEmp();
				break;
			case 4:
				deleteEmp();
				break;
			case 5:
				listEmp();
				break;
			case 6:
				medalEmp();
				break;
			case 7:
				run = false;
				break;
			}
		}
		System.out.println("프로그램 종료");
	}
	
	//직원메뉴 - 본인정보 수정
	private void updateInfo(EmployeeVO e) {
		EmployeeVO emp = new EmployeeVO();
		emp.setEmpId(e.getEmpId());
		System.out.println("정보수정 페이지입니다.");
		System.out.println("수정할 정보 선택   1.이름   2.전화번호   3.직급");
		int select = sc.nextInt();
		switch(select) {
		case 1:
			System.out.print("새로운 이름 입력>> ");
			String name = sc.next();
			emp.setEmpName(name);
			break;
		case 2: 
			System.out.print("새로운 전화번호 입력>> ");
			String phone = sc.next();
			emp.setEmpPhone(phone);
			break;
		case 3: 
			System.out.print("새로운 직급 입력>> ");
			String level = sc.next();
			emp.setEmpLevel(level);
			break;
		}
		int r = dao.employeeUpdate(emp);
		if(r==1) {
			System.out.println();
			System.out.println("수정완료");			
		}
	}
	
	
	//직원메뉴 - 본인정보 조회
	private void searchInfo(EmployeeVO e) {
		EmployeeVO emp = new EmployeeVO();
		emp = dao.employeeSelect(e);
		System.out.println("이름:"+emp.getEmpName());
		System.out.println("아이디:"+emp.getEmpId()+" \t직급:"+emp.getEmpLevel());
		System.out.println("연락처"+emp.getEmpPhone()+" \t입사일:"+emp.getHireDate());
		System.out.println();
	}
	
	//사장메뉴 - 직원추가
	private void addEmp() {
		System.out.print("아이디 입력>> ");
		String id = sc.next();
		System.out.print("비밀번호 입력>> ");
		String pw = sc.next();
		System.out.print("이름 입력>> ");
		String name = sc.next();
		System.out.print("연락처 입력>> ");
		String phone = sc.next();
		EmployeeVO e = new EmployeeVO(id,pw,name,phone);
		int r = dao.employeeInsert(e);		
		System.out.println();
		if (r == 1) System.out.println(e.getEmpName()+"님이 등록되었습니다.");
		else System.out.println("등록에 실패하였습니다.");

	}

	//사장메뉴 - 직원삭제
	private void deleteEmp() {
		EmployeeVO e = new EmployeeVO();
		System.out.print("삭제할 직원 이름>> ");
		String name = sc.next();
		System.out.print("삭제할 직원 아이디>> ");
		String id = sc.next();
		e.setEmpId(id);
		e.setEmpName(name);
		int r = dao.employeDelete(e);
		System.out.println();
		if(r == 1) System.out.println(e.getEmpName()+"님이 삭제되었습니다.");
		else System.out.println("삭제에 실패하였습니다.");
	}

	//사장메뉴 - 직원목록
	private void listEmp() {
		List<EmployeeVO> emp = new ArrayList<>();
		emp = dao.employeeSelectList();
		for(EmployeeVO e : emp) {
			System.out.print("이름: "+e.getEmpName()+"\t입사일:"+e.getHireDate()+" \t주문처리:");
			System.out.println(dao.employeeSell(e)+"건");
			System.out.println("아이디:"+e.getEmpId()+" \t연락처:"+e.getEmpPhone());
			System.out.println();
		}
		
	}
	
	private void medalEmp() {
		
	}


}
