package de.hwr.treecision.math;

import junit.framework.TestCase;
import de.hwr.treecision.util.Tuple;

public class MatrixTest extends TestCase {

    public void testMatrix() throws Exception {
	// empty init by col and row sizes
	Matrix m = new Matrix(2, 2);
	assertEquals(m.getColumnCount(), 2);
	assertEquals(m.getRowCount(), 2);
	m.set(0, 0, 1);
	m.set(0, 1, 2);
	m.set(1, 0, 3);
	m.set(1, 1, 4);

	equalityTests(m);

	// init by matrix like constructor
	m = new Matrix(new int[][] { { 1, 2 }, { 3, 4 }, });
	equalityTests(m);
    }

    public void testMatrixSplit() throws Exception {
	Matrix m = new Matrix(new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 }, { 9, 10 }, { 1, 2 }, { 3, 4 },
		{ 5, 6 }, { 7, 8 }, { 9, 10 } });

	Tuple<Matrix, Matrix> randomSubmatrix = m.splitRandomMatrices(0.2f);
	assertEquals(randomSubmatrix.getFirst().getRowCount(), 8);
	assertEquals(randomSubmatrix.getSecond().getRowCount(), 2);
    }

    /**
     * pre-defined test for the given matrix:<br/>
     * [ 1 , 2] <br/>
     * [ 3 , 4] <br/>
     */
    private void equalityTests(Matrix m) {
	assertEquals(m.get(0, 0), 1);
	assertEquals(m.get(0, 1), 2);
	assertEquals(m.get(1, 0), 3);
	assertEquals(m.get(1, 1), 4);

	Vector row = m.getRow(0);
	assertEquals(row.getLength(), 2);
	assertEquals(row.get(0), 1);
	assertEquals(row.get(1), 2);

	Vector column = m.getColumn(1);
	assertEquals(column.getLength(), 2);
	assertEquals(column.get(0), 2);
	assertEquals(column.get(1), 4);
    }

}
