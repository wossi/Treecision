package de.hwr.treecision.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import de.hwr.treecision.util.Tuple;

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

    /**
     * Creates two matrices out of this by the given percentage. It uses a random function to determine which rows
     * should belong to the matrix including the given percentage amount of rows.
     * 
     * @param percentage
     *            a float value between 0.0f and 1.0f
     * @return a tuple which includes two matrices, the first contains always NUMBER_ROWS-(NUMBER_ITEMS*percentage)
     *         rows. The second one is the rest of it.
     */
    public final Tuple<Matrix, Matrix> splitRandomMatrices(float percentage) {
	if (percentage < 0.0f || percentage > 1.0f) {
	    throw new IllegalArgumentException("Percentage must be between zero and one! Given " + percentage);
	}
	final Random r = new Random(System.nanoTime());
	int neededRows = Math.round(numRows * percentage);
	// we first choose neededRows number of items to pick
	final HashSet<Integer> alreadyUsedRowIndices = new HashSet<Integer>();
	while (neededRows > 0) {
	    // +1 because of exlusive usage of nextInt for upper boundary
	    final int nextIndex = r.nextInt(numRows + 1);
	    if (alreadyUsedRowIndices.add(nextIndex)) {
		neededRows--;
	    }
	}

	// smaller and larger are somehow dependend on the given percentage.. maybe there is a better naming than first,
	// second or larger and smaller.
	final int[][] largerMatrix = new int[numRows - alreadyUsedRowIndices.size()][numColumns];
	int largerMatrixIndex = 0;
	final int[][] smallerMatrix = new int[alreadyUsedRowIndices.size()][numColumns];
	int smallerMatrixIndex = 0;

	// then we loop over all items and put split the matrix
	for (int i = 0; i < alreadyUsedRowIndices.size(); i++) {
	    if (alreadyUsedRowIndices.contains(i)) {
		smallerMatrix[smallerMatrixIndex] = getRow(i).toArray();
		smallerMatrixIndex++;
	    } else {
		largerMatrix[largerMatrixIndex] = getRow(i).toArray();
		largerMatrixIndex++;
	    }
	}

	return new Tuple<Matrix, Matrix>(new Matrix(largerMatrix), new Matrix(smallerMatrix));
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
