package co.yeadam.project.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import co.yeadam.project.employee.service.EmployeeVO;
import co.yeadam.project.food.service.*;
import co.yeadam.project.food.serviceImpl.FoodServiceImpl;

public class FoodMenu {
	Scanner sc = new Scanner(System.in);
	FoodService dao = new FoodServiceImpl();
	ReviewMenu rm = new ReviewMenu();
	
	//음식메뉴관리의 시작
	public void run() {
		System.out.println();
		System.out.println("---------------------------------[음식 관리 메뉴]---------------------------------");
		boolean run = true;
		while(run) {
			System.out.println();
			System.out.println("===================================================================================================");
			System.out.println("1.전체메뉴조회   2.메뉴상세조회   3.메뉴등록   4.메뉴수정   5.메뉴삭제   6.메뉴 순위   7.메뉴 후기   8.뒤로가기");
			System.out.println("===================================================================================================");
			System.out.print("선택>> ");
			int s = sc.nextInt();
			switch(s) {
			case 1:
				//전체메뉴조회
				System.out.println();
				System.out.println("|전체메뉴조회|");
				showList();
				break;
			case 2:
				//메뉴상세조회
				showFood();
				break;
			case 3:
				//메뉴등록
				addFood();
				break;
			case 4:
				//메뉴수정
				modifyFood();
				break;
			case 5:
				//메뉴삭제
				deleteFood();
				break;
			case 6:
				//메뉴순위
				foodSell();
				break;
			case 7:
				//메뉴후기조회
				rm.searchReview();
				break;
			case 8:
				run=false;
				break;
			}
		}
	}
	
	
	//1. 메뉴전체조회
	public void showList() {
		
		List<FoodVO> foods = new ArrayList<>();
		
		foods = dao.foodSelectList();
		
		System.out.println();
		for(FoodVO f : foods) {
			System.out.println("이름:"+f.getFoodName()+" \t 가격:"+f.getFoodPrice());
		}

	}
	
	//2. 메뉴상세조회
	public void showFood() {
		FoodVO food = new FoodVO();
		System.out.println();
		System.out.print("메뉴이름입력>> ");
		String name = sc.next();
		food.setFoodName(name);
		
		System.out.println();
		try {			
			food = dao.foodSelect(food);
			food.toString(food);
		}catch(NullPointerException e) {
			System.out.println("없는 메뉴입니다.");
		}
		
		
	}
	
	//3. 메뉴등록
	public void addFood() {
		FoodVO food = new FoodVO();
		//사용자에게 이름,가격,설명 입력받음
		
		System.out.println();
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
		
		System.out.println();
		int r = dao.foodInsert(food);
		if (r!=1) {
			System.out.println("등록안됨");
		}
		else {
			System.out.println(name+" 이(가) 등록되었습니다.");
		}
	}
	
	//4. 메뉴수정
	public void modifyFood() {
		FoodVO food = new FoodVO();
		
		System.out.println();
		System.out.print("수정할 메뉴 이름 입력>>");
		String menu = sc.next();
		food.setFoodName(menu);
		
		if(dao.foodSelect(food) == null) {
			System.out.println("없는 메뉴입니다.");
		}
		else {			
			//가격,설명 중 입력받음
			System.out.println("1.가격수정   2.설명수정");
			System.out.print(">> ");
			int select = sc.nextInt();
			sc.nextLine();
			
			int r=0;
			//가격은 가격만, 설명은 설명만 입력받음
			switch(select) {
			case 1:
				System.out.print("새로운 가격>> ");
				int price = sc.nextInt();
				sc.nextLine();
				food.setFoodPrice(price);
				r = dao.foodUpdate(food);
				break;
			case 2:
				System.out.print("새로운 설명>> ");
				String script = sc.nextLine();
				food.setFoodScript(script);
				r = dao.foodUpdate(food);
				break;
			}
			
			System.out.println();
			if(r==1) System.out.println(menu+" 이(가) 수정되었습니다.");
			else System.out.println("수정 실패");
		}

	}
	
	//5. 메뉴삭제
	public void deleteFood() {
		FoodVO food = new FoodVO();
		System.out.println();
		System.out.print("삭제할 메뉴 이름 입력>> ");
		String name = sc.next();
		food.setFoodName(name);
		
		if(dao.foodSelect(food) == null) {
			System.out.println("없는 메뉴입니다.");
		}
		else {			
			int r = dao.foodDelete(food);
			System.out.println();
			
			if(r==1) System.out.println(name+" 이(가) 수정되었습니다.");
			else System.out.println("삭제 실패");
		}
		
		
	}
	
	//6, 메뉴순위
	public void foodSell() {
		System.out.println();
		System.out.println("[판매 순위]");
		System.out.println();
		int idx = 1;
		
		
		//List<Map<String,Object>>로 받음
		List<Map<String, Object>> food = new ArrayList<Map<String, Object>>();
		food = dao.foodSell();
		
		
		
		for(Map<String, Object> f : food) {
			System.out.println(idx+"위) "+f.get("FOOD_NAME")+"\t"+f.get("SELL")+"건");
			idx++;
		}


	}
	

}
