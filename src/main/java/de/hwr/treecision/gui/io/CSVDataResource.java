package de.hwr.treecision.gui.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import de.hwr.treecision.DataSet;

public class CSVDataResource implements DataResource {

    private String fileName;
    private char seperator = ',';

    public CSVDataResource(String fileName) {
	this.fileName = fileName;
    }

    public CSVDataResource(String fileName, char seperator) {
	this.fileName = fileName;
	this.seperator = seperator;
    }

    @Override
    public DataSet readDataSet() throws IOException {
	CSVReader reader = new CSVReader(new FileReader(fileName), seperator);
	return new DataSet(reader.readAll());
    }

    @Override
    public void writeDataSet(DataSet data) throws IOException {
	CSVWriter writer = new CSVWriter(new FileWriter(fileName), seperator);
	writer.writeNext(data.getAttributeNames());
	writer.writeAll(data.getObjects());
	writer.close();
    }

}
