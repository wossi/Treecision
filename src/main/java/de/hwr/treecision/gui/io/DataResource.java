package de.hwr.treecision.gui.io;

import java.io.IOException;


public interface DataResource {

    public DataSet readDataSet() throws IOException;

    public void writeDataSet(DataSet data) throws IOException;

}
