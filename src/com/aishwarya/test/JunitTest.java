package com.aishwarya.test;

import static org.junit.Assert.*;
import com.aishwarya.barrenLand.BarrenLandAnalysis;

import org.junit.Test;



public class JunitTest {

	BarrenLandAnalysis br=new BarrenLandAnalysis();;
		
	@Test
	//testing with no data ""
	public void testNoData() {
		//resulting area should be whole area only
		String resultAreas = "240000";		
		//making data ""
		String data = new String("");
		br.shadeBarrenLands(data);
		assertEquals(resultAreas,br.calcFertileAreas());
	}
	
	@Test
	//testing with data to be empty {""} or {} both yields the same
	public void testEmptyData() {
		//resulting area should be whole area only	
		String resultAreas = "240000";		
		//empty data {""}
		String data = new String("{\"\"}");
		br.shadeBarrenLands(data);
		assertEquals(resultAreas,br.calcFertileAreas());
	}

	@Test
	//testing with given sample data1
	public void testSampleData1() {
		//resulting area should be 116800 116800	
		String resultAreas = "116800 116800";		
		String data = new String("{\"0 292 399 307\"}");
		//sample data 1
		br.shadeBarrenLands(data);
		assertEquals(resultAreas,br.calcFertileAreas());
	}
	
	@Test
	//testing with given sample data2
	public void testSampleData2() {
		//resulting area should be 22816 192608	
		String resultAreas = "22816 192608";	
		String data = new String("{\"48 192 351 207\" , \"48 392 351 407\" , \"120 52 135 547\" , \"260 52 275 547\"}");
		//sample data 2
		br.shadeBarrenLands(data);
		assertEquals(resultAreas,br.calcFertileAreas());
	}
	
}
