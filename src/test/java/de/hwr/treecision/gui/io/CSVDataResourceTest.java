package de.hwr.treecision.gui.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class CSVDataResourceTest extends TestCase {
    
    public void testReadWrite() throws IOException, MatrixFormatException {
	// test missing file
	CSVDataResource csv = new CSVDataResource("fileNotExisting.csv");
	StringMatrix sm = csv.readStringMatrix();
	assertEquals(sm.getRowCount(), 0);
	assertEquals(sm.getColumnCount(), 0);
	
	// test read normal csv file
	csv = new CSVDataResource("src/test/resources/defaultTest.csv");
	sm = csv.readStringMatrix();	
	StringMatrixTest.testDefaultMatrix(sm);
	
	// change string matrix and test the write method
	sm.addColumn(1);
	sm.set(0, 1, "name");
	sm.set(1, 1, "Mandy");
	sm.set(2, 1, "Bob");
	sm.addRow(new ArrayList<String>(Arrays.asList(new String[] {"m", "Peter", "25", "1.85m", "0"})));
	
	Files.delete(Paths.get("src/test/resources/defaultTest_manipulated.csv"));
	CSVDataResource csvManipulated = new CSVDataResource("src/test/resources/defaultTest_manipulated.csv");
	csvManipulated.writeStringMatrix(sm);
	StringMatrix sm2 = csvManipulated.readStringMatrix();
	StringMatrixTest.testManipulatedDefaultMatrix(sm2);
	
	// test csv file with semicolons
	csv = new CSVDataResource("src/test/resources/defaultTest_semicolon.csv", ';');
	sm = csv.readStringMatrix();
	StringMatrixTest.testDefaultMatrix(sm);
    }

}
