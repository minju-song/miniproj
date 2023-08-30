package co.yeadam.project.review.service;

import java.util.List;


public interface ReviewService {
	List<ReviewVO> reviewSelectList(ReviewVO vo);
	int insertReview(ReviewVO vo);
	ReviewVO selectReview(ReviewVO vo);
}
