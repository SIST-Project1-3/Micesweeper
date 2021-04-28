package vo;

import java.io.Serializable;

public class BoardVO implements Serializable {
	String title, content, writer, wdate, comments; // 제목, 내용, 작성자, 작성일자, 댓글
	int no, viewcount; // 글번호, 조회수

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
}
