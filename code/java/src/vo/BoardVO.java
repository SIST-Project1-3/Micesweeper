package vo;

public class BoardVO {
	String title, content, author, wdate;
	int no, viewcount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return wdate;
	}

	public void setDate(String date) {
		this.wdate = date;
	}

	public int getView() {
		return viewcount;
	}

	public void setView(int view) {
		this.viewcount = view;
	}
}
