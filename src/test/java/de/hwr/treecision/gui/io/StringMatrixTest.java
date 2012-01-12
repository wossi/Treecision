package de.hwr.treecision.gui.io;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

import com.google.common.collect.BiMap;

import de.hwr.treecision.math.Matrix;

public class StringMatrixTest extends TestCase {

    private static final String[] tr1 = { "gender", "age", "size", "fat" };
    private static final String[] tr2 = { "f", "20", "1.7m", "0" };
    private static final String[] tr3 = { "m", "20", "1.8m", "1" };
    private static final String[] tr4 = { "m", "25", "1.85m", "0" };

    public void testConstructors() {
	// test array list constructor
	ArrayList<ArrayList<String>> matrix = new ArrayList<>(3);
	matrix.add(new ArrayList<>(Arrays.asList(tr1)));
	matrix.add(new ArrayList<>(Arrays.asList(tr2)));
	matrix.add(new ArrayList<>(Arrays.asList(tr3)));
	testDefaultMatrix(new StringMatrix(matrix));

	// test string array constructor
	String[][] array = new String[][] { tr1, tr2, tr3 };
	testDefaultMatrix(new StringMatrix(array));

	// test string array list constructor
	ArrayList<String[]> list = new ArrayList<>(3);
	list.add(tr1);
	list.add(tr2);
	list.add(tr3);
	testDefaultMatrix(new StringMatrix(list));
    }

    public void testMatrixManipulations() {
	StringMatrix sm = getDefaultTestMatrix();

	sm.addColumn(1);
	assertEquals(sm.getColumnCount(), 5);
	try {
	    sm.addRow(new ArrayList<String>(Arrays.asList(new String[] { "m", "Peter", "25", "1.85m", "0" })));
	} catch (MatrixFormatException e) {
	    e.printStackTrace();
	}
	assertEquals(sm.getRowCount(), 4);

	sm.set(0, 1, "name");
	sm.set(1, 1, "Mandy");
	sm.set(2, 1, "Bob");

	testManipulatedDefaultMatrix(sm);

	sm.moveColumn(0, 2);
	assertEquals(sm.get(0, 0), "name");
	assertEquals(sm.get(0, 1), "age");
	assertEquals(sm.get(0, 2), "gender");
	assertEquals(sm.get(0, 3), "size");
	assertEquals(sm.get(0, 4), "fat");

	sm.moveColumn(2, 0);
	assertEquals(sm.get(0, 0), "gender");
	assertEquals(sm.get(0, 1), "name");
	assertEquals(sm.get(0, 2), "age");
	assertEquals(sm.get(0, 3), "size");
	assertEquals(sm.get(0, 4), "fat");

	sm.moveColumnToEnd(0);
	assertEquals(sm.get(0, 0), "name");
	assertEquals(sm.get(0, 1), "age");
	assertEquals(sm.get(0, 2), "size");
	assertEquals(sm.get(0, 3), "fat");
	assertEquals(sm.get(0, 4), "gender");
    }

    public void testMatrixConveration() {
	StringMatrix sm = new StringMatrix(new String[][] { tr1, tr2, tr3, tr4 });
	sm.addColumn(1);
	sm.set(0, 1, "name");
	sm.set(1, 1, "Mandy");
	sm.set(2, 1, "Bob");
	sm.set(3, 1, "Peter");
	testManipulatedDefaultMatrix(sm);
	sm.set(2, 1, "");
	sm.set(3, 1, null);	
	
	String[][] converter = sm.createValueMatrix();
	assertEquals(converter.length, 5); // five attributes
	assertEquals(converter[0].length, 2); // f, m
	assertEquals(converter[1].length, 1); // just "Mandy" because null and empty values are ignored
	assertEquals(converter[2].length, 2); // 20, 25
	assertEquals(converter[3].length, 3); // 1.7m, 1.8m, 1.85m
	assertEquals(converter[4].length, 2); // 0, 1
	
	assertEquals(converter[0][0], "f");
	assertEquals(converter[0][1], "m");
	assertEquals(converter[1][0], "Mandy");
	assertEquals(converter[2][0], "20");
	assertEquals(converter[2][1], "25");
	assertEquals(converter[3][0], "1.7m");
	assertEquals(converter[3][1], "1.85m");
	assertEquals(converter[3][2], "1.8m");
	assertEquals(converter[4][0], "0");
	assertEquals(converter[4][1], "1");

	Matrix m = sm.toMatrix(converter);
	assertEquals(m.getRowCount(), 3);
	assertEquals(m.getColumnCount(), 5);
	assertEquals(m.get(0, 0), 0);
	assertEquals(m.get(1, 0), 1);
	assertEquals(m.get(2, 0), 1);
	assertEquals(m.get(0, 1), 0);
	assertEquals(m.get(1, 1), -1);
	assertEquals(m.get(2, 1), -1);
	assertEquals(m.get(0, 2), 0);
	assertEquals(m.get(1, 2), 0);
	assertEquals(m.get(2, 2), 1);
	assertEquals(m.get(0, 3), 0);
	assertEquals(m.get(1, 3), 2);
	assertEquals(m.get(2, 3), 1);
	assertEquals(m.get(0, 4), 0);
	assertEquals(m.get(1, 4), 1);
	assertEquals(m.get(2, 4), 0);
    }

    public static StringMatrix getDefaultTestMatrix() {
	return new StringMatrix(new String[][] { tr1, tr2, tr3 });
    }

    /**
     * default test matrix:
     * <table>
     * <tr>
     * <td>gender</td>
     * <td>age</td>
     * <td>size</td>
     * <td>fat</td>
     * </tr>
     * <tr>
     * <td>f</td>
     * <td>20</td>
     * <td>1.7m</td>
     * <td>0</td>
     * </tr>
     * <tr>
     * <td>m</td>
     * <td>20</td>
     * <td>1.8m</td>
     * <td>1</td>
     * </tr>
     * </table>
     * 
     * @param sm
     *            The matrix to test against.
     * 
     */
    public static void testDefaultMatrix(StringMatrix sm) {
	assertEquals(sm.getRowCount(), 3);
	assertEquals(sm.getColumnCount(), 4);
	assertEquals(sm.get(0, 0), "gender");
	assertEquals(sm.get(0, 1), "age");
	assertEquals(sm.get(0, 2), "size");
	assertEquals(sm.get(0, 3), "fat");
	assertEquals(sm.get(1, 0), "f");
	assertEquals(sm.get(1, 1), "20");
	assertEquals(sm.get(1, 2), "1.7m");
	assertEquals(sm.get(1, 3), "0");
	assertEquals(sm.get(2, 0), "m");
	assertEquals(sm.get(2, 1), "20");
	assertEquals(sm.get(2, 2), "1.8m");
	assertEquals(sm.get(2, 3), "1");
    }

    /**
     * default test matrix:
     * <table>
     * <tr>
     * <td>gender</td>
     * <td>name</td>
     * <td>age</td>
     * <td>size</td>
     * <td>fat</td>
     * </tr>
     * <tr>
     * <td>f</td>
     * <td>Mandy</td>
     * <td>20</td>
     * <td>1.7m</td>
     * <td>0</td>
     * </tr>
     * <tr>
     * <td>m</td>
     * <td>Bob</td>
     * <td>20</td>
     * <td>1.8m</td>
     * <td>1</td>
     * </tr>
     * <tr>
     * <td>m</td>
     * <td>Peter</td>
     * <td>25</td>
     * <td>1.85m</td>
     * <td>0</td>
     * </tr>
     * </table>
     * 
     * @param sm
     *            The matrix to test against.
     * 
     */
    public static void testManipulatedDefaultMatrix(StringMatrix sm) {
	assertEquals(sm.get(0, 0), "gender");
	assertEquals(sm.get(0, 1), "name");
	assertEquals(sm.get(0, 2), "age");
	assertEquals(sm.get(0, 3), "size");
	assertEquals(sm.get(0, 4), "fat");
	assertEquals(sm.get(1, 0), "f");
	assertEquals(sm.get(1, 1), "Mandy");
	assertEquals(sm.get(1, 2), "20");
	assertEquals(sm.get(1, 3), "1.7m");
	assertEquals(sm.get(1, 4), "0");
	assertEquals(sm.get(2, 0), "m");
	assertEquals(sm.get(2, 1), "Bob");
	assertEquals(sm.get(2, 2), "20");
	assertEquals(sm.get(2, 3), "1.8m");
	assertEquals(sm.get(2, 4), "1");
	assertEquals(sm.get(3, 0), "m");
	assertEquals(sm.get(3, 1), "Peter");
	assertEquals(sm.get(3, 2), "25");
	assertEquals(sm.get(3, 3), "1.85m");
	assertEquals(sm.get(3, 4), "0");
    }

    /**
     * 
     * as integer for the default test matrix should look like this:
     * <table>
     * <tr>
     * <td>0</td>
     * <td>0</td>
     * <td>0</td>
     * <td>0
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td>0</td>
     * <td>1
     * </tr>
     * </table>
     * 
     * @param m
     *            The integer matrix to test against.
     */
    public static void testDefaultIntegerMatrix(Matrix m) {
	assertEquals(m.getRowCount(), 2);
	assertEquals(m.getColumnCount(), 4);
	assertEquals(m.get(0, 0), 0);
	assertEquals(m.get(0, 1), 0);
	assertEquals(m.get(0, 2), 0);
	assertEquals(m.get(0, 3), 0);
	assertEquals(m.get(1, 0), 1);
	assertEquals(m.get(1, 1), 1);
	assertEquals(m.get(1, 2), 0);
	assertEquals(m.get(1, 3), 1);
    }
}
