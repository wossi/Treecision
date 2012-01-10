package de.hwr.treecision.conf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import com.google.gson.Gson;

public final class Configuration {

    private final HashMap<String, String> confMap = new HashMap<String, String>();

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

    public final void saveToFile(Path path) throws IOException {
	Gson gs = new Gson();
	String json = gs.toJson(this);
	Files.write(path, json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE,
		StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static final Configuration getConfiguration(Path path) throws IOException {
	Gson gs = new Gson();
	return gs.fromJson(new String(Files.readAllBytes(path)), Configuration.class);
    }

}
