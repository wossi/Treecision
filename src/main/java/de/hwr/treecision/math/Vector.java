package de.hwr.treecision.math;

import java.util.Arrays;
import java.util.HashSet;

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

    public final int getNumberOfDistinctElements() {
	final HashSet<Integer> set = new HashSet<Integer>(5);
	final int length = getLength();
	for (int i = 0; i < length; i++) {
	    set.add(get(i));
	}
	return set.size();
    }

    // in our "hack" environment, this fast retrieval can be made because we have elements starting from 0 and then are
    // incremented. -> Experimental!
    public final int getNumberOfDistinctElementsFast() {
	int high = 0;
	final int length = getLength();
	for (int i = 0; i < length; i++) {
	    final int j = get(i);
	    if (high < j) {
		high = j;
	    }
	}
	// +1 because of zero
	return high + 1;
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
    
    public final int[] toArray(){
	return vector;
    }

    @Override
    public final String toString() {
	return Arrays.toString(vector);
    }

}
