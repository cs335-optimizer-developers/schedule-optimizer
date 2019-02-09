package info;

public class CourseTime {

	public final int year;
	public final String semester;
	public final int time;
	public final char[] days;
	public final Quad quad;
	
	public CourseTime(int year, String semester, int time, char[] days, Quad quad) {
		this.year = year;
		this.semester = semester;
		this.time = time;
		this.days = days;
		this.quad = quad;
	}
}