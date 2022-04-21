package kr.study.dev_mook.dao;

import java.util.ArrayList;

import kr.study.dev_mook.dto.ContentDto;

public interface IDao {
	
	public ArrayList<ContentDto> listDao();
	public void writeDao(Long mId, String mWriter, String mContent);
	public ContentDto viewDao(String strID);
	public void deleteDao(String bId);
	public Long latestMid();
	
}
