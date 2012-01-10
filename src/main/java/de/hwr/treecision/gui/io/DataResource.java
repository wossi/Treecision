package de.hwr.treecision.gui.io;

import java.io.IOException;

import de.hwr.treecision.DataSet;

public interface DataResource {

    public DataSet readDataSet() throws IOException;

    public void writeDataSet(DataSet data) throws IOException;

}
