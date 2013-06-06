package ufcg.si1;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class Lab01Test {

	@Test
	public void testMetodoXPTO() {
		Lab01 lab01 = new Lab01();
		Assert.assertEquals(lab01.xpto(), "Hello World do lab01 de SI1.");		
	}

	

}
