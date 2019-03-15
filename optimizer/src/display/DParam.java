package display;

import java.util.ArrayList;
import java.util.List;

public class DParam {

	private List<String> programs = new ArrayList<>();
	private double preferGenEd;
	
	public void addProgram(String p) {programs.add(p);}

	public double getPreferGenEd() {return preferGenEd;}

	public void setPreferGenEd(double preferGenEd) {this.preferGenEd = preferGenEd;}

	public List<String> getPrograms() {return programs;}
}