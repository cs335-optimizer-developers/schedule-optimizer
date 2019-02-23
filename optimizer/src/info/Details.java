package info;

public class CourseDetails {
	
	public final String title;
	public final String department;
	public final String xl;
	public final int number;
	public final Tag[] tags;
	
	public CourseDetails(String title, Tag[] tags, String department, int number, String xl) {
		this.title = title;
		this.tags = tags;
		this.department = department;
		this.number = number;
		this.xl = xl;
	}
}