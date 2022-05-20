package kr.study.dev_mook.persistence;

/* 포인트 적립을 위한 Interface */
public interface PointDAO {
	
	public void updatePoint(String uid, int point) throws Exception;
	
}
