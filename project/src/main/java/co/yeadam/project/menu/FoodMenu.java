package co.yeadam.project.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yeadam.project.employee.service.EmployeeVO;
import co.yeadam.project.food.service.*;
import co.yeadam.project.food.serviceImpl.FoodServiceImpl;

public class FoodMenu {
	Scanner sc = new Scanner(System.in);
	FoodService dao = new FoodServiceImpl();
	
	//음식메뉴관리의 시작
	public void run() {
		System.out.println();
		System.out.println("음식 메뉴 관리 채널입니다.");
		boolean run = true;
		while(run) {
			System.out.println("===================================================================");
			System.out.println("1.전체메뉴조회   2.메뉴상세조회   3.메뉴등록   4.메뉴수정   5.메뉴삭제   6.뒤로가기");
			System.out.println("===================================================================");
			System.out.print("선택>> ");
			int s = sc.nextInt();
			switch(s) {
			case 1:
				System.out.println("|전체메뉴조회|");
				showList();
				break;
			case 2:
				showFood();
				break;
			case 3:
				addFood();
				break;
			case 4:
				modifyFood();
				break;
			case 5:
				deleteFood();
				break;
			case 6:
				run=false;
				break;
			}
		}
	}
	
	
	//메뉴전체조회
	public void showList() {
		
		List<FoodVO> foods = new ArrayList<>();
		
		foods = dao.foodSelectList();
		
		for(FoodVO f : foods) {
			System.out.println("이름:"+f.getFoodName()+" \t 가격:"+f.getFoodPrice());
			System.out.println();
		}

	}
	
	//메뉴상세조회
	public void showFood() {
		FoodVO food = new FoodVO();
		System.out.print("메뉴이름입력>> ");
		String name = sc.next();
		food.setFoodName(name);
		
		food = dao.foodSelect(food);
		
		food.toString(food);
		System.out.println();
		
	}
	
	//메뉴등록
	public void addFood() {
		FoodVO food = new FoodVO();
		//사용자에게 이름,가격,설명 입력받음
		System.out.print("등록할 메뉴 이름>> ");
		String name = sc.next();
		System.out.print("등록할 메뉴 가격>> ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("등록할 메뉴 설명>> ");
		String script = sc.nextLine();
		
		
		food.setFoodName(name);
		food.setFoodPrice(price);
		food.setFoodScript(script);
		
		int r = dao.foodInsert(food);
		if (r!=1) {
			System.out.println("등록안됨");
		}
		else {
			System.out.println();
			System.out.println("등록완료");
		}
	}
	
	//메뉴수정
	public void modifyFood() {
		FoodVO food = new FoodVO();
		
		System.out.print("수정할 메뉴 이름 입력>>");
		String menu = sc.next();
		food.setFoodName(menu);
		
		//가격,설명 중 입력받음
		System.out.println("1.가격수정   2.설명수정");
		System.out.print(">> ");
		int select = sc.nextInt();
		sc.nextLine();
		
		//가격은 가격만, 설명은 설명만 입력받음
		switch(select) {
		case 1:
			System.out.print("새로운 가격>> ");
			int price = sc.nextInt();
			sc.nextLine();
			food.setFoodPrice(price);
			break;
		case 2:
			System.out.print("새로운 설명>> ");
			String script = sc.nextLine();
			food.setFoodScript(script);
			break;
		}

		int r = dao.foodUpdate(food);
		System.out.println();
		if(r==1) System.out.println("수정 완료");
		else System.out.println("수정 실패");
	}
	
	//메뉴삭제
	public void deleteFood() {
		FoodVO food = new FoodVO();
		System.out.print("삭제할 메뉴 이름 입력>> ");
		String name = sc.next();
		food.setFoodName(name);
		
		int r = dao.foodDelete(food);
		System.out.println();
		
		if(r==1) System.out.println("삭제 완료");
		else System.out.println("삭제 실패");
		
	}
}
