package de.hwr.treecision.gui.io;

public class MatrixFormatException extends Exception {

    private final String message;
    
    public MatrixFormatException(String message) {
	this.message = message;
    }
}
