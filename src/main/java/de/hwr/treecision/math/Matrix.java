package de.hwr.treecision.math;

public final class Matrix {

    private final int[][] matrix;
    private final int numRows;
    private final int numColumns;

    public Matrix(int rows, int columns) {
	this.matrix = new int[rows][columns];
	this.numColumns = columns;
	this.numRows = rows;
    }

    public final int getRowLength() {
	return numRows;
    }

    public final int getColumnLength() {
	return numColumns;
    }

    public final int get(int row, int col) {
	return this.matrix[row][col];
    }

    public final void set(int row, int col, int value) {
	this.matrix[row][col] = value;
    }

    public final Vector getRow(int row) {
	return new Vector(matrix[row]);
    }

    public final Vector getColumn(int column) {
	final int[] col = new int[matrix.length];
	for (int i = 0; i < matrix.length; i++) {
	    col[i] = matrix[i][column];
	}
	return new Vector(col);
    }
}
