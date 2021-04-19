package dao;

import vo.MemberVO;

public class MemberDAO extends DAO {
	// Constructor
	public MemberDAO() {
		System.out.println("3: MemberDAO load");
	}

	// Method
	// 회원가입
	public boolean getJoinResult(MemberVO member) {
		boolean result = false;

		try {
			String sql = "insert into member(ID, PW) values(?,?)";
			getPreparedStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());

			int val = pstmt.executeUpdate();
			if (val != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 아이디 중복확인
	public boolean getIdCheckResult(String id) {
		boolean result = false;

		try {
			String sql = "select count(*) from member where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 1)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 로그인
	public boolean getLoginResult(String id, String pw) {
		boolean result = false;

		try {
			String sql = "select count(*) from member where id=? and pw=?";
			getPreparedStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 1)
					result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
