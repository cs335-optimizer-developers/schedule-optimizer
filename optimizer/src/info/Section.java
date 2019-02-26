package info;

public class Section extends ClassType {
	private Details internal;
	private int sectionNum;
	
	public Section(Details internal, int sectionNum) {
		super(internal);
		this.sectionNum = sectionNum;
	}

}
