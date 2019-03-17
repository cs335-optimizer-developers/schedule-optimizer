package test;

import org.junit.jupiter.api.Test;

import algorithm.AlgArbitrary;
import algorithm.AlgComplex;

public class LargeTest extends GenTest {
	
	@Test
	void writeGenerate() {
		reset();
		opt.write();
		opt.generate();
	}
	
	@Test
	void generateWrite() {
		reset();
		opt.generate();
		opt.write();
	}
	
	@Test
	public void arbitrary() {
		reset();
		opt.setAlgorithm(new AlgArbitrary());
		opt.generate();
	}
	
	@Test
	public void complex() {
		reset();
		opt.setAlgorithm(new AlgComplex());
		opt.generate();
	}
	
	@Test
	void manyGenerateWrite() {
		reset();
		for (int x=0;x<100;x++)
			opt.generate();
		opt.write();
	}
	
	@Test
	void manyWriteGenerate() {
		reset();
		for (int x=0;x<100;x++)
			opt.write();
		opt.generate();
	}
}
