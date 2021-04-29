package dao;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import vo.MemberVO;
import vo.MessageVO;

public class GameDAO extends DAO {
	// Constructor
	public GameDAO() {
		System.out.println("3: GameDAO load");
	}

	// Method
	// 게임화면 방장 이미지, 참가자 이미지 가져오기
	public ImageIcon getGameImgResult(String id) {
		ImageIcon gameImg = null;
		try {
			String sql = "select img from member where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ImageIcon icon = new ImageIcon(rs.getString(1));
				icon.setDescription(rs.getString(1));
				gameImg = icon;
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
				profile.setImg(new ImageIcon(rs.getString(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

	// 승리 기록 저장
	public boolean getWinResult(MessageVO msg) {
		boolean result = false;
		try {
			// 승리 횟수 증가
			String sql = "update member set win = win+1 where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId());
			int val = pstmt.executeUpdate();

			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 패배 기록 저장
	public boolean getLoseResult(MessageVO msg) {
		boolean result = false;
		try {
			// 승리 횟수 증가
			String sql = "update member set lose = lose+1 where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId());
			int val = pstmt.executeUpdate();

			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
