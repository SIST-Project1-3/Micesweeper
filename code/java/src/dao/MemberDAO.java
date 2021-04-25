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
	public MemberVO getProfileResult(MessageVO msg) {
		MemberVO profile = new MemberVO();
		try {
			String sql = "select * from member where id=?";
			getPreparedStatement(sql);
			
			pstmt.setString(1, msg.getId());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				profile.setId(rs.getString(1));
				profile.setWin(rs.getInt(3));
				profile.setLose(rs.getInt(4));
				profile.setImg(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}
	
	// 프로필 이미지 가져오기
	 public String[] getImgResult(MessageVO msg) {
		 String[] img = new String[6];
		
		try {
			String sql = "select * from img";
			getPreparedStatement(sql);
			int i =0;
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setImg(rs.getString(1));
				img[i] = member.getImg();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}

	// 프로필 이미지 수정
	 public boolean getUpdateResult(MessageVO msg) {
			boolean result = false;
			
			System.out.println("dao - getUpdateResult");
			
			try {
				String sql = "update member set img=? where id=?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, msg.getImg());
				pstmt.setString(2, msg.getId());
				
				int val = pstmt.executeUpdate();
				if (val == 1)
					result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

}
