package de.hwr.treecision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.hwr.treecision.math.Matrix;

public class DataSet {

    ArrayList<String> attributeNames;
    ArrayList<ArrayList<String>> objects;

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

    public void addAttribute(String name, String defaultValue) {
	attributeNames.add(name);
	for (ArrayList<String> object : objects) {
	    object.add(defaultValue);
	}
    }

    public int addObject() {
	return addObject(new ArrayList<String>(attributeNames.size()));
    }

    public int addObject(ArrayList<String> object) {
	objects.add(object);
	return objects.size() - 1;
    }

    public void changeAttributeOrder(int oldIndex, int newIndex) {
	if (newIndex > oldIndex) {
	    newIndex--;
	}
	attributeNames.add(newIndex, attributeNames.remove(oldIndex));
	for (ArrayList<String> object : objects) {
	    object.add(newIndex, object.remove(oldIndex));
	}
    }

    public void removeAttribute(int index) {
	attributeNames.remove(index);
	for (ArrayList<String> object : objects) {
	    object.remove(index);
	}
    }

    public void removeObject(int index) {
	objects.remove(index);
    }

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

    public Matrix toMatrix() {
	Matrix matrix = new Matrix(attributeNames.size(), objects.size());

	return matrix;
    }
}
