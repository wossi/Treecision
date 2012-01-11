package de.hwr.treecision.gui.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import junit.framework.TestCase;

public class DataSetTest extends TestCase {

    public void testDataSetManipulation() throws Exception {
	// build simple test data table
	String[] header = {"Gender", "Age", "fat"};
	String[] tr1 = {"m", "young", "1"};
	String[] tr2 = {"f", "young", "0"};
	ArrayList<String[]> allRows = new ArrayList<>(3);
	allRows.add(header);
	allRows.add(tr1);
	allRows.add(tr2);
	ArrayList<String[]> dataRows = new ArrayList<>(2);
	dataRows.add(tr1);
	dataRows.add(tr2);

	DataSet dataSet = new DataSet(allRows);
	
	// check for correct initialization
	Assert.assertArrayEquals(dataSet.getAttributeNames(), header);
	List<String[]> objects = dataSet.getObjects();
	Assert.assertArrayEquals(objects.get(0), tr1);
	Assert.assertArrayEquals(objects.get(1), tr2);
	
	// test simple add manipulations
	dataSet.addAttribute("Size", "1.8m");
	String[] newHeader = {"Gender", "Age", "fat", "Size"};
	Assert.assertArrayEquals(dataSet.getAttributeNames(), newHeader);
	objects = dataSet.getObjects();
	assertEquals(objects.get(0)[3], "1.8m");
	assertEquals(objects.get(1)[3], "1.8m");
	
	String[] object = {"f", "old", "0", "1.7m"};
	dataSet.addObject(object);
	Assert.assertArrayEquals(dataSet.getObject(2), object);
	
    }
}
