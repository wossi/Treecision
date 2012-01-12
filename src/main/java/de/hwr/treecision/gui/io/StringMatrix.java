package de.hwr.treecision.gui.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.hwr.treecision.math.Matrix;

public final class StringMatrix {

    private int rows;
    private int cols;
    private final ArrayList<ArrayList<String>> matrix;

    public StringMatrix(int rows, int cols) {
	this.rows = rows;
	this.cols = cols;
	matrix = new ArrayList<>(rows);
	for (int r = 0; r < rows; r++) {
	    matrix.add(new ArrayList<String>(cols));
	}
    }

    public StringMatrix(ArrayList<ArrayList<String>> matrix) {
	this.matrix = matrix;
	rows = matrix.size();
	int maxCols = 0;
	for (ArrayList<String> row : matrix) {
	    if (row.size() > maxCols) {
		maxCols = row.size();
	    }
	}
	cols = maxCols;
	fillUpRows();
    }

    public StringMatrix(String[][] matrix) {
	rows = matrix.length;
	this.matrix = new ArrayList<>(rows);
	int maxCols = 0;
	for (int r = 0; r < rows; r++) {
	    this.matrix.add(new ArrayList<String>(Arrays.asList(matrix[r])));
	    if (matrix[r].length > maxCols) {
		maxCols = matrix[r].length;
	    }
	}
	cols = maxCols;
	fillUpRows();
    }

    public StringMatrix(List<String[]> table) {
	rows = table.size();
	matrix = new ArrayList<>(rows);
	int maxCols = 0;
	for (int r = 0; r < rows; r++) {
	    matrix.add(new ArrayList<String>(Arrays.asList(table.get(r))));
	    if (table.get(r).length > maxCols) {
		maxCols = table.get(r).length;
	    }
	}
	cols = maxCols;
	fillUpRows();
    }

    public final void addColumn() {
	addColumn(cols);
    }

    public final void addColumn(int index) {
	for (int r = 0; r < rows; r++) {
	    matrix.get(r).add(index, null);
	}
	cols++;
    }

    public final int addRow() throws MatrixFormatException {
	return addRow(rows, new ArrayList<String>(cols));
    }

    public final int addRow(ArrayList<String> row) throws MatrixFormatException {
	return addRow(rows, row);
    }

    public final int addRow(int position, ArrayList<String> row) throws MatrixFormatException {
	if (row.size() < cols) {
	    throw new MatrixFormatException("Row (1x" + row.size() + " doesn't match the matrix size (" + rows + "x"
		    + cols + ")");
	}
	matrix.add(position, row);
	return rows++;
    }

    public final boolean equals(StringMatrix otherMatrix) {
	return otherMatrix.getMatrix().equals(matrix);
    }

    public final String get(int row, int col) {
	return matrix.get(row).get(col);
    }

    public final ArrayList<ArrayList<String>> getMatrix() {
	return matrix;
    }

    public final ArrayList<String> getRow(int index) {
	return matrix.get(index);
    }

    public final ArrayList<String> getHeaderRow() {
	return matrix.get(0);
    }

    public final int getRowCount() {
	return rows;
    }

    public final int getColumnCount() {
	return cols;
    }

    public final void moveColumn(int oldPosition, int newPosition) {
	for (ArrayList<String> row : matrix) {
	    row.add(newPosition, row.remove(oldPosition));
	}
    }

    public void moveColumnToEnd(int index) {
	for (ArrayList<String> row : matrix) {
	    row.add(row.remove(index));
	}
    }

    public void set(int row, int col, String value) {
	matrix.get(row).set(col, value);
    }

    public String toString() {
	return matrix.toString();
    }

    private void fillUpRows() {
	for (ArrayList<String> row : matrix) {
	    while (row.size() > cols) {
		row.add(null);
	    }
	}
    }

    /**
     * Creates a matrix containing all attribute values. The first dimension is the attribute (equal to the column in
     * the string matrix) while the second is an index for each possible value. Same values of the same attribute have
     * the same index. The first line of the string matrix - meaned to be the header row - is ignored and empty or null
     * values, too.
     * 
     * @return
     */
    public String[][] createValueMatrix() {
	String[][] valueMatrix = new String[cols][];

	for (int c = 0; c < cols; c++) {
	    ArrayList<String> values = new ArrayList<>();
	    for (int r = 1; r < rows; r++) {
		String value = get(r, c);
		if (value != null && !value.isEmpty()) {
		    if (!values.contains(value)) {
			values.add(value);
		    }
		}
	    }
	    Collections.sort(values);
	    valueMatrix[c] = values.toArray(new String[0]);
	}

	return valueMatrix;
    }

    /**
     * Use a value matrix to convert the string matrix to an matrix of integers. The first row of the matrix - mean to
     * be the header row - is ignored.
     * 
     * @param valueMatrix
     * @return
     */
    public Matrix toMatrix(String[][] valueMatrix) {
	int[][] matrix = new int[rows - 1][cols];

	for (int c = 0; c < cols; c++) {
	    for (int r = 1; r < rows; r++) {
		String value = get(r, c);
		if (value == null || value.isEmpty()) {
		    matrix[r - 1][c] = -1;
		} else {
		    matrix[r - 1][c] = Arrays.binarySearch(valueMatrix[c], value);
		}
	    }
	}

	return new Matrix(matrix);
    }
}
