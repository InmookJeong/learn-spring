package kr.study.dev_mook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import kr.study.dev_mook.dto.BDto;
import kr.study.dev_mook.util.Constants;

public class BDao {
	
	JdbcTemplate jdbcTemplate = null;
	
	public BDao() {
//		XML에서 모두 처리해놨기 때문에 필요 없음
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/knou");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
		
		jdbcTemplate = Constants.jdbcTemplate;
	}
	
	// MVC Board보다 개선된 list method.
	public ArrayList<BDto> list() {
		
		String query = "";
		query += " SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent ";
		query += " FROM mvc_board ";
		query += " ORDER BY bGroup DESC, bStep ASC ";
		
		// 두 번째 파라미터 : 데이터를 가져올 커맨드 객체를 명시
		ArrayList<BDto> dtos = (ArrayList<BDto>) jdbcTemplate.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
		return dtos;
	}
	
	public int countList() {
		String query = " SELECT COUNT(*) FROM mvc_board ";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	public int getLatestBid() {
		int latestBid = 0;
		
		if(countList() > 0) {
			String query = "";
			query += " SELECT bId ";
			query += " FROM mvc_board ";
			query += " ORDER BY bId DESC ";
			query += " LIMIT 1 ";
			
			latestBid = jdbcTemplate.queryForObject(query, Integer.class);
		}
		
		return latestBid;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			conn = dataSource.getConnection();
//			
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) latestBid = rs.getInt("bId");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return latestBid;
	}
	
	public void write(final String bName, final String bTitle, final String bContent) {
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "";
				query += " INSERT INTO mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) ";
				query += " VALUES (?, ?, ?, ?, 0, ?, 0, 0) ";
				
				int newBid = getLatestBid() + 1;
				
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, newBid);
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setInt(5, newBid);
				
				return pstmt;
			}
		});
	}
	
	public BDto contentView(int bId) {
		
		_upHit(bId);
		
		String query = "";
		query += " SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent ";
		query += " FROM mvc_board ";
		query += " WHERE bId = " + bId + " ";
		
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void modify(final int bId, final String bName, final String bTitle, final String bContent) {
		String query = "";
		query += " UPDATE mvc_board ";
		query += " SET bName = ?, ";
		query += " bTitle = ?, ";
		query += " bContent = ? ";
		query += " WHERE bId = ? ";
		
		jdbcTemplate.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, bId);
				
			}
		});
	}
	
	public void delete(final int bId) {
		String query = "";
		query += " DELETE FROM mvc_board ";
		query += " WHERE bId = ? ";
		
		jdbcTemplate.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bId);
			}
		});
	}
	
	public void reply(final String bName, final String bTitle, final String bContent, final int bGroup, final int bStep, final int bIndent) {
		_replyShape(bGroup, bStep);
		
		String query = "";
		query += " INSERT INTO mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) ";
		query += " VALUES (?, ?, ?, ?, 0, ?, ?, ?) ";
		
		jdbcTemplate.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int newBid = getLatestBid() + 1;
				
				ps.setInt(1, newBid);
				ps.setString(2, bName);
				ps.setString(3, bTitle);
				ps.setString(4, bContent);
				ps.setInt(5, bGroup);
				ps.setInt(6, bStep+1);
				ps.setInt(7, bIndent+1);
			}
		});
	}
	
	public BDto replyView(int bId) {
		
		String query = "";
		query += " SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent ";
		query += " FROM mvc_board ";
		query += " WHERE bId = " + bId + " ";
		
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	private void _replyShape(final int group, final int step) {
		
		String query = "";
		query += " UPDATE mvc_board ";
		query += " SET bStep = bStep + 1 ";
		query += " WHERE bGroup = ? AND bStep > ? ";
		
		jdbcTemplate.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, group);
				ps.setInt(2, step);
			}
		});
	}
	
	// setValues 메소드에서 bId가 변경될 수 있다고 판단하면 에러가 발생하기 때문에
	// Argument로 받는 bId에 final 추가
	private void _upHit(final int bId) {
		
		String query = "";
		query += " UPDATE mvc_board ";
		query += " SET bHit = bHit + 1 ";
		query += " WHERE bId = ? ";
		
		jdbcTemplate.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bId);
			}
		});
	}
}
