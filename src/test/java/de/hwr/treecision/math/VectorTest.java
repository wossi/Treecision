package de.hwr.treecision.math;

import junit.framework.TestCase;

public class VectorTest extends TestCase {

    public void testVectorInit() throws Exception {
	// by size
	Vector v = new Vector(2);
	assertEquals(v.getLength(), 2);
	assertEquals(v.get(0), 0);
	assertEquals(v.get(1), 0);
	v.set(0, 2);
	v.set(1, 3);
	assertEquals(v.get(0), 2);
	assertEquals(v.get(1), 3);
	assertEquals(v.getNumberOfDistinctElements(), 2);

	try {
	    v.set(3, 0);
	} catch (ArrayIndexOutOfBoundsException e) {
	    // everything should be okay in this case here
	    assertNotNull(v);
	}

	// by other array
	Vector vx = new Vector(new int[] { 2, 3 });
	assertEquals(vx.getLength(), 2);
	assertEquals(vx.get(0), 2);
	assertEquals(vx.get(1), 3);
	assertEquals(v.getNumberOfDistinctElements(), 2);

	// fast lookup test
	Vector vxx = new Vector(new int[] { 0, 1, 2, 3 });
	assertEquals(vxx.getNumberOfDistinctElementsFast(), 4);
	int[] array = vxx.toArray();
	assertEquals(array.length, 4);
    }

    public void testVectorSummation() throws Exception {
	Vector x = new Vector(new int[] { 2, 3 });
	x.add(new Vector(new int[] { 4, 5 }));
	assertEquals(x.getLength(), 2);
	assertEquals(x.get(0), 6);
	assertEquals(x.get(1), 8);
    }

}
