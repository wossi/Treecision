package de.hwr.treecision.gui.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.hwr.treecision.math.Matrix;

public class StringMatrixTest extends TestCase {

    public void testStringMatrixConstructors() {
	String[] tr1 = { "gender", "age", "size", "fat" };
	String[] tr2 = { "f", "20", "1.7m", "0" };
	String[] tr3 = { "m", "20", "1.8m", "1" };
	
	// test array list constructor
	ArrayList<ArrayList<String>> matrix = new ArrayList<>(3);
	matrix.add(new ArrayList<>(Arrays.asList(tr1)));
	matrix.add(new ArrayList<>(Arrays.asList(tr2)));
	matrix.add(new ArrayList<>(Arrays.asList(tr3)));
	testDefaultMatrix(new StringMatrix(matrix));

	// test string array constructor
	String[][] array = new String[][] { tr1, tr2, tr3};
	testDefaultMatrix(new StringMatrix(array));
	
	// test string array list contructor
	ArrayList<String[]> list = new ArrayList<>(3);
	list.add(tr1);
	list.add(tr2);
	list.add(tr3);
	testDefaultMatrix(new StringMatrix(list));
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
    private void testDefaultMatrix(StringMatrix sm) {
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
     * 
     * as integer for the default test matrix should look like this:
     * <table>
     * <tr>
     * <td>1</td>
     * <td>3</td>
     * <td>4</td>
     * <td>6
     * </tr>
     * <tr>
     * 2</td>
     * <td>3</td>
     * <td>5</td>
     * <td>7
     * </tr>
     * </table>
     * 
     * @param m
     *            The integer matrix to test against.
     */
    private void testDefaultIntegerMatrix(Matrix m) {
	assertEquals(m.getRowCount(), 2);
	assertEquals(m.getColumnCount(), 4);
	assertEquals(m.get(0, 0), 1);
	assertEquals(m.get(0, 1), 3);
	assertEquals(m.get(0, 2), 4);
	assertEquals(m.get(0, 3), 6);
	assertEquals(m.get(1, 0), 2);
	assertEquals(m.get(1, 1), 3);
	assertEquals(m.get(1, 2), 4);
	assertEquals(m.get(1, 3), 6);
    }
}
