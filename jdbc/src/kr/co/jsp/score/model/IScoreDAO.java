package kr.co.jsp.score.model;

import java.util.List;

public interface IScoreDAO {

	//점수를 등록하는 기능
	boolean insert(ScoreVO score);
	
	//점수 목록 조회
	List<ScoreVO> list();

	//이름으로 검색하는 기능
	List<ScoreVO> search(String keyword);
	
	//삭제 기능
	boolean delete(long id);
}
