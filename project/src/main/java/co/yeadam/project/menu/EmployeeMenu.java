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
	
	public void empRun(EmployeeVO emp) {
		System.out.println();
		System.out.println("---------[마이페이지 메뉴]---------");
		boolean run = true;
		while(run) {		
			System.out.println("================================");
			System.out.println("1.내정보 조회   2.정보수정   3.뒤로가기");
			System.out.println("================================");
			System.out.print("선택>> ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				searchInfo(emp);
				break;
			case 2:
				updateInfo(emp);
				break;
			case 3:
				run = false;

			}
		}
	}
	
	
	private void searchInfo(EmployeeVO e) {
		EmployeeVO emp = new EmployeeVO();
		emp = dao.employeeSelect(e);
		System.out.println("이름:"+emp.getEmpName());
		System.out.println("아이디:"+emp.getEmpId()+" \t직급:"+emp.getEmpLevel());
		System.out.println("연락처"+emp.getEmpPhone()+" \t입사일:"+emp.getHireDate());
		System.out.println();
	}
	
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
	
	public void kingRun(EmployeeVO emp) {
		System.out.println();
		System.out.println("-------------------[직원 관리 메뉴]-------------------");
		boolean run = true;
		while(run) {	
			System.out.println("===================================================");
			System.out.println("1.직원추가   2.직원삭제   3.직원목록   4.직원정보   5.뒤로가기");
			System.out.println("===================================================");
			System.out.print("선택>> ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				addEmp();
				break;
			case 2:
				deleteEmp();
				break;
			case 3:
				listEmp();
				break;
			case 4:
				searchEmp();
				break;
			case 5:
				run = false;
				break;
			}
		}
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
		System.out.println();
		for(EmployeeVO e : emp) {
			System.out.print("이름: "+e.getEmpName()+" \t연락처:"+e.getEmpPhone()+" \t주문처리:");
			System.out.println(dao.employeeSell(e)+"건");
			System.out.println();
		}
		
	}
	
	private void searchEmp() {
		EmployeeVO e = new EmployeeVO();
		System.out.print("조회할 직원 이름>> ");
		String name = sc.next();
		e.setEmpName(name);
		e = dao.employeeSelectName(e);
		System.out.println();
		System.out.println("이름 : "+e.getEmpName());
		System.out.println("아이디 : "+e.getEmpId());
		System.out.println("연락처 : "+e.getEmpPhone());
		System.out.println("입사일 : "+e.getHireDate());
		System.out.print("직급 : ");
		if(e.getEmpLevel().equals("king")) System.out.println("사장");
		else System.out.println("직원");
		System.out.println("처리한 주문 : "+dao.employeeSell(e)+"건");
		System.out.println();
	}
}
