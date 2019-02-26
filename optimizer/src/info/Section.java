package info;

public class Section extends ClassType {
	private ClassDetails internal;
	private int sectionNum;
	
	public Section(ClassDetails internal, int sectionNum) {
		super(internal);
		this.sectionNum = sectionNum;
	}

}
