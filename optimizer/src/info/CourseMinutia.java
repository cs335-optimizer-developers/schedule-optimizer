package info;

public class CourseMinutia {

	public final int CRN;
	public final int section;
	public final int cred;
	public final String instructor;
	public final String room;
	public final int fee;
	
	public CourseMinutia(int cRN, int section, int cred, String instructor, String room, int fee) {
		super();
		CRN = cRN;
		this.section = section;
		this.cred = cred;
		this.instructor = instructor;
		this.room = room;
		this.fee = fee;
	}
	
	public CourseMinutia() {
		CRN = 0;
		section = 0;
		cred = 0;
		instructor = null;
		room = null;
		fee = 0;
	}
}