package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomVO implements Serializable, Cloneable {
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

	// 방 참가에서 소켓 통신을 할 때, 클라이언트에서 목록 갱신이 안돼서 cloneable을 적용 후 clone 오버라이딩
	public Object clone() {
		RoomVO result = null;
		try {
			result = (RoomVO) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
