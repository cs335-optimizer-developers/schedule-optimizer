package info;

public class Section extends ClassType {
	@SuppressWarnings("unused")
	private ClassDetails internal;
	private int sectionNum;
	
	public Section(ClassDetails internal, int sectionNum) {
		super(internal);
		this.sectionNum = sectionNum;
	}
	
	public int getSection() {
		return sectionNum;
	}

}
