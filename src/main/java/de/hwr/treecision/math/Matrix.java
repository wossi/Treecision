package de.hwr.treecision.math;

public class Matrix {

    private final int[][] matrix;

    public Matrix(int columns, int rows) {
	this.matrix = new int[columns][rows];
    }

    public int get(int col, int row) {
	return this.matrix[col][row];
    }

    public void set(int col, int row, int value) {
	this.matrix[col][row] = value;
    }
}
