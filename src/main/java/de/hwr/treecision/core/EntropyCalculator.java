package de.hwr.treecision.core;

import java.util.HashSet;

import de.hwr.treecision.math.Matrix;
import de.hwr.treecision.math.Vector;

public final class EntropyCalculator {

    static final double LOG_BASE_TWO = Math.log(2);
    private static final HashSet<Integer> alreadyUsedIndicesList = new HashSet<Integer>();

    /**
     * Uses internal tracking of what attributes have been returned in this method.
     * 
     * @param inputFeatures
     * @param outputVariables
     * @return
     */
    public final int getIndexWithMaxEntropy(Matrix inputFeatures, Vector outputVariables) {
	return getIndexWithMaxEntropy(inputFeatures, outputVariables, alreadyUsedIndicesList);
    }

    /**
     * Returns the index of a feature which has the highest entropy and wasn't used before. This method can be used for
     * external tracking of what attributes have been returned to filter.
     * 
     * @return -1 if all features has been examined from outside. >=0 index of what attribute in feature should be used
     *         next. (based on max entropy).
     */
    public final int getIndexWithMaxEntropy(Matrix inputFeatures, Vector outputVariables,
	    HashSet<Integer> alreadyUsedIndicesList) {
	final int columns = inputFeatures.getColumnCount();

	int maxEntropyIndex = -1;
	double highestEntropy = 0.0d;
	for (int i = 0; i < columns; i++) {
	    if (alreadyUsedIndicesList.contains(i)) {
		final double weightedEntropySum = getWeightedEntropySum(inputFeatures.getColumn(i), outputVariables);
		if (weightedEntropySum > highestEntropy) {
		    highestEntropy = weightedEntropySum;
		    maxEntropyIndex = i;
		}
	    }
	}

	alreadyUsedIndicesList.add(maxEntropyIndex);
	return maxEntropyIndex;
    }

    public final double getWeightedEntropySum(Vector attributeColumn, Vector outputVariables) {
	final int length = attributeColumn.getLength();
	// We can change to the method getNumberOfDistinctElementsFast() because the vector contains consistent
	// positive incremented values from zero
	final int distinctAttributes = attributeColumn.getNumberOfDistinctElementsFast();
	final int[] attributeCount = new int[distinctAttributes];
	/*
	 * This is always two dimensional, on the first dimension is the attribute, in the second dimension are the
	 * counts for the predition.
	 */
	final int[][] preditionCount = new int[distinctAttributes][2];

	for (int i = 0; i < length; i++) {
	    final int attributeIndex = attributeColumn.get(i);
	    final int prediction = outputVariables.get(i);
	    // This "hack" is only working if our vector elements strictly start with zero and are incremented
	    // afterwards.
	    attributeCount[attributeIndex]++;
	    preditionCount[attributeIndex][prediction]++;
	}

	double weightedEntropySum = 0.0;
	// log base two is calculated as following:
	// (Math.log(x) / Math.log(2)
	for (int i = 0; i < distinctAttributes; i++) {
	    // only working for binary classification
	    // DANGER! Sophisticated math function incoming!
	    weightedEntropySum += ((double) attributeCount[i] / (double) length)
		    * (((-(double) preditionCount[i][0] / (double) attributeCount[i]) * (Math
			    .log((double) preditionCount[i][0] / (double) attributeCount[i]) / LOG_BASE_TWO)) - ((-(double) preditionCount[i][1] / (double) attributeCount[i]) * (Math
			    .log((double) preditionCount[i][1] / (double) attributeCount[i]) / LOG_BASE_TWO)));
	}
	// we have to absolute our calculation because negative values make no sense in probability.
	return Math.abs(weightedEntropySum);
    }
}
