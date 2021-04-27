package dao;

import vo.MemberVO;
import vo.MessageVO;

public class GameDAO extends DAO {
	// Constructor
	public GameDAO() {
		System.out.println("4: GameDAO load");
	}

	// Method
	// 게임화면 프로필 (내 프로필, 상대 프로필)
	 public MemberVO getGameProfileResult(MessageVO msg) {
		 System.out.println("dao");
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

	//승패기록 저장
	public void getScoreResult() {

	}

	
}
