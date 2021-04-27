package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomVO implements Serializable {
	// Field
	public String title;
	public int no, userCount; // , 방 번호, 인원 수
	public ArrayList<String> userList = new ArrayList<String>(2); // 연결된 유저의 이름

	// Method
	
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
