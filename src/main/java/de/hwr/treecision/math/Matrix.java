package de.hwr.treecision.math;

import java.util.Arrays;

public final class Matrix {

    private final int[][] matrix;
    private final int numRows;
    private final int numColumns;

    public Matrix(int rows, int columns) {
	this.matrix = new int[rows][columns];
	this.numColumns = columns;
	this.numRows = rows;
    }

    public Matrix(int[][] otherMatrix) {
	this.matrix = otherMatrix;
	this.numRows = otherMatrix.length;
	if (numRows > 0) {
	    this.numColumns = otherMatrix[0].length;
	} else {
	    this.numColumns = 0;
	}
    }

    public final int getRowCount() {
	return numRows;
    }

    public final int getColumnCount() {
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

    @Override
    public final String toString() {
	StringBuilder sb = new StringBuilder();
	int length = getRowCount();
	for (int i = 0; i < length; i++) {
	    sb.append(Arrays.toString(matrix[i]));
	}
	return sb.toString();
    }
}
