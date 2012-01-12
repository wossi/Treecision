package de.hwr.treecision.gui.io;

import java.util.ArrayList;
import java.util.Arrays;
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
     * Creates a bidirectional map which assigns every string in the matrix to an integer. Same strings get the same
     * number. Null strings are assigned to -1 and empty string get -2. The first row of the matrix - mean to be the
     * header row - is ignored.
     * 
     * @return a bimap which contains the mapping between integers and their names.
     */
    public BiMap<String, Integer> createConverter() {
	BiMap<String, Integer> converter = HashBiMap.create();
	// not allowed entries are negative because we want to use arrays, and they start at 0.
	converter.put(null, -1);
	converter.put("", -2);

	int n = 0;
	for (int c = 0; c < cols; c++) {
	    for (int r = 1; r < rows; r++) {
		if (!converter.containsKey(get(r, c))) {
		    converter.put(get(r, c), n++);
		}
	    }
	}
	return converter;
    }

    /**
     * Use a bidirectional map to convert the StringMatrix to an matrix of integers. The first row of the matrix - mean
     * to be the header row - is ignored.
     * 
     * @param converter
     * @return
     */
    public Matrix toMatrix(BiMap<String, Integer> converter) {
	int[][] matrix = new int[rows - 1][cols];

	for (int r = 1; r < rows; r++) {
	    for (int c = 0; c < cols; c++) {
		matrix[r - 1][c] = converter.get(get(r, c));
	    }
	}

	return new Matrix(matrix);
    }
}
