package co.yeadam.project.review.service;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	private int reviewId;
	private Date reviewDate;
	private String reviewContent;
	private int foodId;
}
