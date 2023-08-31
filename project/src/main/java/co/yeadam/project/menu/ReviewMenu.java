package co.yeadam.project.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yeadam.project.food.service.FoodService;
import co.yeadam.project.food.service.FoodVO;
import co.yeadam.project.food.serviceImpl.FoodServiceImpl;
import co.yeadam.project.review.service.ReviewVO;
import co.yeadam.project.review.serviceImpl.ReviewServiceImpl;
import co.yeadam.project.review.service.ReviewService;

public class ReviewMenu {
	Scanner sc = new Scanner(System.in);
	FoodService daoF = new FoodServiceImpl();
	ReviewService daoR = new ReviewServiceImpl();
	
	//고객에게만 보이는 리뷰실행화면
	public void run() {
		System.out.println();
		System.out.println("1.후기조회   2.후기등록");
		System.out.print(">> ");
		int menu = sc.nextInt();
		switch(menu) {
		case 1:
			//후기조회
			searchReview();
			break;
		case 2:
			//후기등록
			addReview();
			break;
		}

	}

	//1. 후기조회
	//고객 후기메뉴와 직원들의 음식메뉴관리채널에서 호출
	public void searchReview() {
		System.out.println();
		ReviewVO review = new ReviewVO();
		FoodVO food = new FoodVO();
		
		//현재 메뉴들 모두 출력
		List<FoodVO> foods = new ArrayList<>();
		System.out.println("후기를 조회할 메뉴의 이름을 입력해주세요.");
		System.out.println();
		
		foods = daoF.foodSelectList();
		
		for(FoodVO f : foods) {
			System.out.println(f.getFoodId()+"번 - "+"이름:"+f.getFoodName()+" \t 가격:"+f.getFoodPrice());
		}
		
		//메뉴 먼저 출력 후 어떤 메뉴 후기 출력할 지 입력받음
		System.out.print(">> ");
		String name = sc.next();
		
		try {			
			food.setFoodName(name);
			
			food = daoF.foodSelect(food);
			review.setFoodId(food.getFoodId());
			
			//사용자가 입력한 foodId를 가진 review 모두 출력
			List<ReviewVO> reviews = daoR.reviewSelectList(review);
			System.out.println();
			int idx = 1;
			for(ReviewVO r : reviews) {
				System.out.println("● 후기"+idx+" : "+r.getReviewContent());
				idx++;
			}
		}catch (NullPointerException e) {
			System.out.println("없는 메뉴입니다.");
		}
	}
	
	//2. 후기등록
	private void addReview() {
		System.out.println();
		ReviewVO review = new ReviewVO();
		FoodVO food = new FoodVO();
		List<FoodVO> foods = new ArrayList<>();
		//메뉴 먼저 출력
		System.out.println("후기를 등록할 메뉴의 이름 입력해주세요.");
		System.out.println();
		foods = daoF.foodSelectList();
		
		for(FoodVO f : foods) {
			System.out.println(f.getFoodId()+"번 - "+"이름:"+f.getFoodName()+" \t 가격:"+f.getFoodPrice());
		}
		
		//등록할 foodId 입력받음
		System.out.print(">> ");		
		String name = sc.next();
		sc.nextLine();
		
		food.setFoodName(name);
		food = daoF.foodSelect(food);
		if(food == null) {
			System.out.println("없는 메뉴입니다.");
		}
		else {		
			review.setFoodId(food.getFoodId());
			System.out.println();
			System.out.println("후기를 입력해주세요.");
			System.out.print(">> ");
			String content = sc.nextLine();
			review.setReviewContent(content);
			int n = daoR.insertReview(review);
			System.out.println();
			if(n == 1) {			
				System.out.println(food.getFoodId()+"번 음식에 후기가 등록되었습니다.");
			}
			else System.out.println("등록실패");
		}
		
	}
}
