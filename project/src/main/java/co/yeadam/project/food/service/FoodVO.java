package co.yeadam.project.food.service;

import lombok.Data;

@Data
public class FoodVO {
	private String foodName;
	private int foodPrice;
	private String foodScript;
	
	public void toString(FoodVO food) {
		System.out.println("메뉴 이름:"+food.foodName+"   가격:"+food.foodPrice+"원   설명:"+food.foodScript);
	}
}
