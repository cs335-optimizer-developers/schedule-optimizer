package info;

public class Course {
	
	public final CourseDetails courseClass;
	public final CourseMinutia courseDetails;
	public final CourseTime courseTime;
	
	public Course(CourseDetails courseClass, CourseMinutia courseDetails, CourseTime courseTime) {
		this.courseClass = courseClass;
		this.courseDetails = courseDetails;
		this.courseTime = courseTime;
	}
}