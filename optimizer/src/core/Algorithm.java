package core;

import info.*;

public interface Algorithm {
	Semester[] build(Course[] classes, CourseDetails[] curriculum);
}