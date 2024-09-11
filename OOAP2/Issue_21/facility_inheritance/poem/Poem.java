package poem;

public class Poem {
	
	private String text;
	private String author;
	
	public Poem(String author) {
		this.author = author;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return author + " " + text;
	}
}
