package de.hwr.treecision.gui.io;

import java.io.IOException;


public interface DataResource {

    public StringMatrix readDataSet() throws IOException;

    public void writeDataSet(StringMatrix sm) throws IOException;

}
