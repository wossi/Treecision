package de.hwr.treecision.gui.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;

public class CsvReader {

    public List<String[]> readFromFile(String filename) throws IOException {
	CSVReader reader = new CSVReader(new FileReader(filename));
	return reader.readAll();
    }

}
