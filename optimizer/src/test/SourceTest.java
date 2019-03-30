package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

import core.Source;

/**
 * Test will pass if all public fields within Source are either "normal"
 * files or directories. 
 * 
 * @author James White
 */
class SourceTest {

	@Test
	void allFilesFound() throws IllegalArgumentException, IllegalAccessException, FileNotFoundException {
		for (Field f : Source.class.getFields()) {
			String s = (String) f.get(null);
			File l = new File(s);
			assertTrue(l.isDirectory() || l.isFile());
		}
	}
}