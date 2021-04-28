package dao;

import java.util.ArrayList;

import vo.MemberVO;
import vo.MessageVO;

public class GameDAO extends DAO {
	// Constructor
	public GameDAO() {
		System.out.println("3: GameDAO load");
	}

	// Method
	// 게임화면 방장 이미지, 참가자 이미지 가져오기
	public String getGameImgResult(String id) {
		String gameImg = new String();
		try {
			String sql = "select img from member where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				gameImg = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gameImg;
	}

	// 게임화면 프로필 보기 (내 프로필, 상대 프로필)
	public MemberVO getGameProfileResult(MessageVO msg) {
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

	// 승리 기록 저장
	public void getWinResult(MessageVO msg) {
		try {
			// 승리 횟수 증가
			String sql = "update member set win = win+1 where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 패배 기록 저장
	public void getLoseResult(MessageVO msg) {
		try {
			// 승리 횟수 증가
			String sql = "update member set lose = lose+1 where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
