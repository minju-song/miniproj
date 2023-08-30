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
			System.out.println("===========================================================================================");
			System.out.println("1.전체메뉴조회   2.메뉴상세조회   3.메뉴등록   4.메뉴수정   5.메뉴삭제   6.메뉴 순위   7.메뉴 후기   8.뒤로가기");
			System.out.println("===========================================================================================");
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
				foodSell();
				break;
			case 7:
				rm.searchReview();
				break;
			case 8:
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
//		System.out.println();
		
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
	
	public void foodSell() {
		System.out.println();
		System.out.println("[판매 순위]");
		int idx = 1;
		
		
		List<Map<String, Object>> food = new ArrayList<Map<String, Object>>();
		food = dao.foodSell();
		
		
		
		for(Map<String, Object> f : food) {
			System.out.println(idx+"위) "+f.get("FOOD_NAME")+"   "+f.get("SELL")+"건");
			idx++;
		}
//		
//		for(int i=0; i<foods.size(); i++) {
//			int r = dao.foodSell(foods.get(i));
//			food2.put(foods.get(i), r);
//		}
//		
//		Set<FoodVO> keySet = food2.keySet();
//		Iterator<FoodVO> keyIterator = keySet.iterator();
//		while(keyIterator.hasNext()) {
//			FoodVO key = keyIterator.next();
//			Integer value = food2.get(key);
//			System.out.println(idx+"위) "+key+"\t "+value+"건");
//		}

	}
	

}
