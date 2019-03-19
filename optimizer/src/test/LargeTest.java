package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import algorithm.AlgArbitrary;
import algorithm.AlgComplex;
import core.Source;

public class LargeTest extends GenTest {
	
	@Test
	void writeGenerate() {
		opt.write();
		opt.generate();
	}
	
	@Test
	void generateWrite() {
		opt.generate();
		opt.write();
	}
	
	@Test
	public void arbitrary() {
		opt.setAlgorithm(new AlgArbitrary());
		opt.generate();
	}
	
	@Test
	public void complex() {
		opt.setAlgorithm(new AlgComplex());
		opt.generate();
	}
	
	@Test
	void manyGenerateWrite() {
		for (int x=0;x<100;x++)
			opt.generate();
		opt.write();
	}
	
	@Test
	void manyWriteGenerate() {
		for (int x=0;x<100;x++)
			opt.write();
		opt.generate();
	}
	
	@Test
	void allFilesFound() throws IllegalArgumentException, IllegalAccessException, FileNotFoundException {
		for (Field f : Source.class.getFields()) {
			String s = (String) f.get(null);
			File l = new File(s);
			assertTrue(l.isDirectory() || l.isFile());
		}
	}
}
