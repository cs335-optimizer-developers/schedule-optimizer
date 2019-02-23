package info;

public class Section {
	private Details internal;
	private int sectionNum;
	
	public Section(Details internal, int sectionNum) {
		if(internal==null) {
			System.out.println("Error when creating a new section, Details cannot be null... terminating");
			System.exit(1);
		}
		this.sectionNum = sectionNum;
	}

}
