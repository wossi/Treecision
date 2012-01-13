package de.hwr.treecision.gui.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public final class CSVDataResource implements DataResource {

    private final String fileName;
    private char seperator = ',';

    public CSVDataResource(String fileName) {
	this.fileName = fileName;
    }

    public CSVDataResource(String fileName, char seperator) {
	this.fileName = fileName;
	this.seperator = seperator;
    }

    @Override
    public final StringMatrix readStringMatrix() throws IOException {
	if (Files.notExists(Paths.get(fileName))) {
	    return new StringMatrix(0, 0);
	}
	
	CSVReader reader = new CSVReader(new FileReader(fileName), seperator);
	return new StringMatrix(reader.readAll());
    }

    @Override
    public final void writeStringMatrix(StringMatrix sm) throws IOException {
	CSVWriter writer = new CSVWriter(new FileWriter(fileName), seperator);
	for (ArrayList<String> row : sm.getMatrix()) {
	    writer.writeNext(row.toArray(new String[0]));
	}
	writer.close();
    }

}
