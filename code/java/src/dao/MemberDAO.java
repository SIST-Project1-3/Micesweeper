package dao;

import java.util.ArrayList;

import vo.BoardVO;
import vo.MemberVO;
import vo.MessageVO;

public class MemberDAO extends DAO {
	// Constructor
	public MemberDAO() {
		System.out.println("3: MemberDAO load");
	}

	// Method
	// 회원가입
	public boolean getJoinResult(MessageVO msg) {
		boolean result = false;

		try {
			String sql = "insert into member(ID, PW) values(?,?)";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId());
			pstmt.setString(2, msg.getPw());

			int val = pstmt.executeUpdate();
			if (val != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 아이디 중복확인
	public boolean getIdCheckResult(MessageVO msg) {
		boolean result = false;

		try {
			String sql = "select count(*) from member where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId());

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
	public boolean getLoginResult(MessageVO msg) {
		boolean result = false;

		try {
			String sql = "select count(*) from member where id=? and pw=?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId());
			pstmt.setString(2, msg.getPw());

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

	// 내 프로필 요청 & 정보 받기
	public MemberVO getProfileResult(MemberVO m_id) {
		MemberVO member = new MemberVO();
		try {
			String sql = "select * from member where id=? and win=? and lose=? and img=:";
			getPreparedStatement(sql);
			
			pstmt.setString(1, m_id.getId());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member.setId(rs.getString(1));
				member.setWin(rs.getInt(2));
				member.setLose(rs.getInt(3));
				member.setImg(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	// 프로필 이미지 가져오기
	public ArrayList<MemberVO> geImagResult() {
		ArrayList<MemberVO> img_list = new ArrayList<MemberVO>();
		try {
			String sql = "select * from member";
			getPreparedStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setImg(rs.getString(1));
				img_list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img_list;
	}

	// 프로필 이미지 수정
	

}
