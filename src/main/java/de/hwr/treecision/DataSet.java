package de.hwr.treecision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.hwr.treecision.math.Matrix;

/**
 * Class to handle the data for the decision tree. All attribute values are held as strings and the last attribute is
 * always the one to decide about.
 */
public class DataSet {

    ArrayList<String> attributeNames;
    ArrayList<ArrayList<String>> objects;

    /**
     * Creates a new DataSet from a table.
     * 
     * @param table
     *            The first row of the table should contain the name of the attributes while every row below describes
     *            an object with its attributes.
     */
    public DataSet(List<String[]> table) {
	// extract table header (first line contains the names of the attributes) from table and convert the rest to 2
	// dimensional array list, which can be modified later very easily
	if (table.size() == 0) {
	    this.attributeNames = new ArrayList<String>();
	    this.objects = new ArrayList<ArrayList<String>>();
	} else {
	    this.attributeNames = new ArrayList<String>(Arrays.asList(table.get(0)));
	    this.objects = new ArrayList<ArrayList<String>>(table.size());
	    for (int i = 1; i < table.size(); i++) {
		this.objects.add(new ArrayList<String>(Arrays.asList(table.get(i))));
	    }
	}
    }

    /**
     * Add a new attribute to all objects with a default value.
     * 
     * @param name
     * @param defaultValue
     */
    public void addAttribute(String name, String defaultValue) {
	attributeNames.add(name);
	for (ArrayList<String> object : objects) {
	    object.add(defaultValue);
	}
    }

    /**
     * Add a new empty object to the data set, all attributes are set to null.
     * 
     * @return
     */
    public int addObject() {
	return addObject(new ArrayList<String>(attributeNames.size()));
    }

    /**
     * Add the object to the data set.
     * 
     * @param object
     * @return
     */
    public int addObject(ArrayList<String> object) {
	objects.add(object);
	return objects.size() - 1;
    }

    /**
     * Change the ordering index of an attribute.
     * 
     * @param oldIndex
     * @param newIndex
     */
    public void changeAttributeOrder(int oldIndex, int newIndex) {
	if (newIndex > oldIndex) {
	    newIndex--;
	}
	attributeNames.add(newIndex, attributeNames.remove(oldIndex));
	for (ArrayList<String> object : objects) {
	    object.add(newIndex, object.remove(oldIndex));
	}
    }
    
    /**
     * Returns an string array with all names of all attributes.
     * 
     * @return
     */
    public String[] getAttributeNames() {
	return attributeNames.toArray(new String[0]);
    }
    
    /**
     * Returns a list of string arrays where each list item describes an object with its attributes.
     * 
     * @return
     */
    public List<String[]> getObjects() {
	ArrayList<String[]> objs = new ArrayList<String[]>(objects.size());
	for (ArrayList<String> object : objects) {
	    objs.add(object.toArray(new String[0]));
	}
	return objs;
    }

    /**
     * Remove an attribute, the corresponding values.
     * 
     * @param index
     */
    public void removeAttribute(int index) {
	attributeNames.remove(index);
	for (ArrayList<String> object : objects) {
	    object.remove(index);
	}
    }

    /** 
     * Remove an object.
     * 
     * @param index
     */
    public void removeObject(int index) {
	objects.remove(index);
    }

    /**
     * Create a bidirectional map which assigns an integer to every attribute value in the data set.
     * 
     * @return
     */
    public BiMap<String, Integer> createConverter() {
	BiMap<String, Integer> converter = HashBiMap.create();
	converter.put(null, 0);
	int n = 1;
	for (ArrayList<String> object : objects) {
	    for (int i = 0; i < object.size(); i++) {
		if (!converter.containsKey(object.get(i))) {
		    converter.put(object.get(i), n++);
		}
	    }
	}
	return converter;
    }

    /**
     * Use a bidirectional map to convert the whole data set into an integer matrix.
     * 
     * @param converter
     * @return
     */
    public Matrix toMatrix(BiMap<String, Integer> converter) {
	int[][] matrix = new int[attributeNames.size()][objects.size()];

	for (int r = 0; r < objects.size(); r++) {
	    ArrayList<String> object = objects.get(r);
	    for (int c = 0; c < object.size(); c++) {
		matrix[r][c] = converter.get(object.get(c));
	    }
	}

	return new Matrix(matrix);
    }
}
