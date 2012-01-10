package de.hwr.treecision.math;

import java.util.Arrays;

public final class Vector {

    private final int[] vector;

    public Vector(int size) {
	this.vector = new int[size];
    }

    public Vector(int[] arr) {
	// normally, we should make a defensive copy
	this.vector = arr;
    }

    public final int get(int index) {
	return vector[index];
    }

    public final int getLength() {
	return vector.length;
    }

    public final void set(int index, int value) {
	vector[index] = value;
    }

    public final void add(Vector v) {
	if (v.getLength() != this.getLength()) {
	    throw new UnsupportedOperationException("Vectors must match in size! " + v.getLength() + " != "
		    + v.getLength());
	}

	for (int i = 0; i < v.getLength(); i++) {
	    this.set(i, this.get(i) + v.get(i));
	}
    }

    @Override
    public final String toString() {
	return Arrays.toString(vector);
    }

}
