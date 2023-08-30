package co.yeadam.project.review.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yeadam.project.common.DataSource;
import co.yeadam.project.review.mapper.ReviewMapper;
import co.yeadam.project.review.service.ReviewService;
import co.yeadam.project.review.service.ReviewVO;

public class ReviewServiceImpl implements ReviewService{
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ReviewMapper map = sqlSession.getMapper(ReviewMapper.class);

	
	@Override
	public List<ReviewVO> reviewSelectList(ReviewVO vo) {
		return map.reviewSelectList(vo);
	}


	@Override
	public int insertReview(ReviewVO vo) {
		return map.insertReview(vo);
	}


	@Override
	public ReviewVO selectReview(ReviewVO vo) {
		return map.selectReview(vo);
	}

}
