package de.hwr.treecision.core.model;

import java.util.Iterator;

import de.hwr.treecision.core.model.DecisionTree.DecisionTreeNode;

/**
 * This should be the model class for a trained decision tree.
 * 
 */
public final class DecisionTree implements Iterable<DecisionTreeNode> {

    private final DecisionTreeNode root;

    public DecisionTree() {
	super();
	this.root = new DecisionTreeNode();
    }

    // TODO make some more thoughts on it...
    public DecisionTreeNode add(DecisionTreeNode node, int attributeIndex, int[] children) {

	return null;
    }

    @Override
    public Iterator<DecisionTreeNode> iterator() {
	// TODO in-order traversal
	return null;
    }

    /**
     * Simple node class for our decision tree.
     */
    public final class DecisionTreeNode {
	// index of the attribute in the matrix
	int attributeIndex;
	
	/*
	 * Indiced children attributes, since our attribute indices are always real and positive (including zero) we can
	 * just use an array for fast access of the child. This is mostly the case in classification scenarios.
	 */
	int[] children;
	// if this is a leaf, this is the prediction
	int prediction;

	public final boolean isLeaf() {
	    return children == null;
	}

	public final int getPrediction() {
	    return prediction;
	}
    }

}
