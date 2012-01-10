package de.hwr.treecision.conf;

import java.util.HashMap;

public final class Configuration {

    private final HashMap<String, String> confMap = new HashMap<String, String>();
    
    public Configuration() {
    }
    
    // TODO add constructors for GSON serialization with path

    public final void set(String key, String value) {
	confMap.put(key, value);
    }

    public final void setInt(String key, int value) {
	confMap.put(key, Integer.toString(value));
    }

    public final void setBoolean(String key, boolean value) {
	confMap.put(key, Boolean.toString(value));
    }

    public final String get(String key) {
	return confMap.get(key);
    }

    public final String get(String key, String defaultReturn) {
	String ret = get(key);
	if (ret == null) {
	    return defaultReturn;
	} else {
	    return ret;
	}
    }

    public final int getInt(String key, int defaultReturn) {
	String ret = get(key);
	if (ret == null) {
	    return defaultReturn;
	} else {
	    return Integer.parseInt(ret);
	}
    }

    public final boolean getBoolean(String key, boolean defaultReturn) {
	String ret = get(key);
	if (ret == null) {
	    return defaultReturn;
	} else {
	    return Boolean.parseBoolean(ret);
	}
    }

}
