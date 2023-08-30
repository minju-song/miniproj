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
	
	public void run() {
		System.out.println("1.후기조회   2.후기등록");
		System.out.print(">> ");
		int menu = sc.nextInt();
		switch(menu) {
		case 1:
			searchReview();
			break;
		case 2:
			addReview();
			break;
		}

	}

	public void searchReview() {
		System.out.println();
		ReviewVO review = new ReviewVO();
		List<FoodVO> foods = new ArrayList<>();
		System.out.println("후기를 조회할 메뉴의 번호를 입력해주세요.");
		System.out.println();
		foods = daoF.foodSelectList();
		
		for(FoodVO f : foods) {
			System.out.println(f.getFoodId()+"번 - "+"이름:"+f.getFoodName()+" \t 가격:"+f.getFoodPrice());
		}
		
		System.out.print(">> ");		
		int foodId = sc.nextInt();

		review.setFoodId(foodId);
		List<ReviewVO> reviews = daoR.reviewSelectList(review);
		System.out.println();
		int idx = 1;
		for(ReviewVO r : reviews) {
			System.out.println("● 후기"+idx+" : "+r.getReviewContent());
			idx++;
		}
	}
	
	private void addReview() {
		System.out.println();
		ReviewVO review = new ReviewVO();
		List<FoodVO> foods = new ArrayList<>();
		System.out.println("후기를 등록할 메뉴의 번호를 입력해주세요.");
		System.out.println();
		foods = daoF.foodSelectList();
		
		for(FoodVO f : foods) {
			System.out.println(f.getFoodId()+"번 - "+"이름:"+f.getFoodName()+" \t 가격:"+f.getFoodPrice());
		}
		
		System.out.print(">> ");		
		int foodId = sc.nextInt();
		sc.nextLine();
		
		review.setFoodId(foodId);
		
		System.out.println("후기를 입력해주세요.");
		System.out.print(">> ");
		String content = sc.nextLine();
		review.setReviewContent(content);
		int n = daoR.insertReview(review);
		if(n == 1) {			
			System.out.println(foodId+"번 음식에 후기가 등록되었습니다.");
		}
		else System.out.println("등록실패");
	}
}
