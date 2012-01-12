package de.hwr.treecision.core;

import junit.framework.TestCase;
import de.hwr.treecision.math.Vector;

public class EntropyCalculatorTest extends TestCase {

    public void testEntropyByHoehne() throws Exception {
	EntropyCalculator calc = new EntropyCalculator();

	// female and male column
	Vector attributeColumn = new Vector(new int[] { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1 });
	// prediction
	Vector outputVariables = new Vector(new int[] { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1 });

	double weightedEntropySum = calc.getWeightedEntropySum(attributeColumn, outputVariables);

	double actual = (((double)6 / (double)11) * ((-((double)2 / (double)6) * (Math.log((double)2 / (double)6) / EntropyCalculator.LOG_BASE_TWO)) - (-((double)4 / (double)6) * (Math
		.log((double)4 / (double)6) / EntropyCalculator.LOG_BASE_TWO))))
		+ (((double)5 / (double)11) * ((-((double)3 / (double)5) * (Math.log((double)3 / (double)5) / EntropyCalculator.LOG_BASE_TWO)) - (-((double)2 / (double)5) * (Math
			.log((double)2 / (double)5) / EntropyCalculator.LOG_BASE_TWO))));
	assertEquals(weightedEntropySum, actual);
	System.out.println("Entropy by hoehne: " + actual);
	System.out.println("Entropy by our algoritm: " + weightedEntropySum);
    }

}
