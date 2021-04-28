package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomVO implements Serializable, Cloneable {
	// Field
	public String title;
	public int no, userCount; // , 방 번호, 인원 수
	public ArrayList<String> userList = new ArrayList<String>(2); // 연결된 유저의 이름
	public GameVO game;

	// Method

	// 방 참가에서 소켓 통신을 할 때, 클라이언트에서 목록 갱신이 안돼서 cloneable을 적용 후 clone 오버라이딩
	public Object clone() {
		RoomVO result = new RoomVO();
		try {
			result.setTitle(title);
			result.setNo(no);
			result.setUserCount(userCount);
			ArrayList<String> list = new ArrayList<String>();
			for(String str : userList) {
				list.add(str);
			}
			result.setUserList(list);
			result.setGame(game);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public ArrayList<String> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<String> userList) {
		this.userList = userList;
	}

	public GameVO getGame() {
		return game;
	}

	public void setGame(GameVO game) {
		this.game = game;
	}

	// 방 정보 반환. ex) 1. 어서오세요 - 1/2
	public String getInfo() {
		return no + ". " + title + " - " + userCount + "/2";
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public ArrayList<String> getUserIdList() {
		return userList;
	}

	public void setUserIdList(ArrayList<String> userIdList) {
		this.userList = userIdList;
	}

}
