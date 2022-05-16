package kr.study.dev_mook.model;

/* 
 * - 검색 조건을 위한 Model
 * - page, perPageNum을 이용하기 위해 Criteria 상속
 *   - Criteria를 상속받으면 PageMaker에서도 사용 가능
 * */
public class SearchCriteria extends Criteria {
	
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
}
