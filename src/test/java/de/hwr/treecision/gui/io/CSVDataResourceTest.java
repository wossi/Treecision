package de.hwr.treecision.gui.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class CSVDataResourceTest extends TestCase {
    
    public void testReadWrite() throws IOException, MatrixFormatException {
	CSVDataResource csv = new CSVDataResource("src/test/resources/defaultTest.csv");
	StringMatrix sm = csv.readStringMatrix();
	
	StringMatrixTest.testDefaultMatrix(sm);
	
	sm.addColumn(1);
	sm.set(0, 1, "name");
	sm.set(1, 1, "Mandy");
	sm.set(2, 1, "Bob");
	sm.addRow(new ArrayList<String>(Arrays.asList(new String[] {"m", "Peter", "25", "1.8m", "0"})));
	
	CSVDataResource csvManipulated = new CSVDataResource("src/test/resources/defaultTest_manipulated.csv");
	csvManipulated.writeStringMatrix(sm);
	StringMatrix sm2 = csvManipulated.readStringMatrix();
	StringMatrixTest.testManipulatedDefaultMatrix(sm2);
    }

}
