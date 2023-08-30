package co.yeadam.project.review.mapper;

import java.util.List;

import co.yeadam.project.review.service.ReviewVO;

public interface ReviewMapper {
	List<ReviewVO> reviewSelectList(ReviewVO vo);
	int insertReview(ReviewVO vo);
	ReviewVO selectReview(ReviewVO vo);
}
