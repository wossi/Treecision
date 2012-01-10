package de.hwr.treecision.core;

import de.hwr.treecision.core.model.DecisionTree;
import de.hwr.treecision.math.Matrix;
import de.hwr.treecision.math.Vector;

public final class DecisionTreeLearner {

    /**
     * For ml-class.org mates inputFeatures is equal to "X" whereas outputVariable is equal to "y" in octave excersises.
     * 
     * @return a trained DecisionTree model.
     */
    public final DecisionTree train(Matrix inputFeatures, Vector outputVariable) {
	return null;
    }

    /**
     * This method is used to validate the trained decision tree on the cross-validation set.<br/>
     * TODO return precision, recall as well as the accuracy in percent.
     */
    public final void testModel(DecisionTree model, Matrix inputFeatures, Vector outputVariables) {

    }

    public static final void main(String[] args) {

    }

}
