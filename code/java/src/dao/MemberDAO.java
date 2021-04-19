package dao;

import vo.MemberVO;

public class MemberDAO extends DAO {

	// 회원가입
	public boolean JoinResult(MemberVO member) {
		boolean result = false;
		
		try {
			String sql = "insert into member values(?,?)";
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 로그인
	
	
	
}
