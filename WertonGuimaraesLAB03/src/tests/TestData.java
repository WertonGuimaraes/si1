package tests;

import static org.junit.Assert.*;
import metodos.data.Data;

import org.junit.Before;
import org.junit.Test;

public class TestData {

	
	Data data1;
	Data data2;
	@Before
	public void init() throws Exception{
		data1 = new Data(10,10,2010);
		data2 = new Data(12,5,2025);
		
	}
	
	@Test
	public void TestaDia(){
		assertTrue(data1.getDia()>0);
		assertTrue((data2.getDia()>0));
	
	}
	@Test
	public void TestaMes(){
		assertTrue(data1.getMes()>0 && data1.getMes()<13);
		assertTrue((data2.getMes()>0));
	
	}
	
	@Test
	public void TestaAno(){
		assertTrue(data1.getAno()>0);
		assertTrue((data2.getAno()>0));
	
	}


}
