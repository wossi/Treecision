package de.hwr.treecision.gui.io;

import java.io.IOException;


public interface DataResource {

    public StringMatrix readStringMatrix() throws IOException;

    public void writeStringMatrix(StringMatrix sm) throws IOException;

}
