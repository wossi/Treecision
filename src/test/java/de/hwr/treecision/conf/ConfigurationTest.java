package de.hwr.treecision.conf;

import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {

    public void testConfiguration() throws Exception {
	Configuration conf = new Configuration();
	// put various kinds of things to the conf
	conf.setInt(ConfigurationKeys.TEST_SET_SIZE_PERCENT, 20);
	String confPathValue = "/usr/local/treecision/conf";
	conf.set(ConfigurationKeys.CONF_PATH, confPathValue);
	conf.setBoolean(ConfigurationKeys.TRAIN_ALL_ITEMS, true);

	// check boolean
	assertEquals(conf.getBoolean(ConfigurationKeys.TRAIN_ALL_ITEMS, false),
		true);
	// check default
	assertEquals(
		conf.getBoolean(ConfigurationKeys.TRAIN_ALL_ITEMS
			+ " other key", false), false);
	// check boolean as string
	assertNotNull(conf.get(ConfigurationKeys.TRAIN_ALL_ITEMS));
	assertEquals(conf.get(ConfigurationKeys.TRAIN_ALL_ITEMS),
		Boolean.TRUE.toString());

	// check int
	assertEquals(conf.getInt(ConfigurationKeys.TEST_SET_SIZE_PERCENT, 0),
		20);
	// check default
	assertEquals(
		conf.getInt(ConfigurationKeys.TEST_SET_SIZE_PERCENT
			+ " other key", 0), 0);
	// check int as string
	assertNotNull(conf.get(ConfigurationKeys.TEST_SET_SIZE_PERCENT));
	assertEquals(conf.get(ConfigurationKeys.TEST_SET_SIZE_PERCENT), "20");

	// check string
	assertNotNull(conf.get(ConfigurationKeys.CONF_PATH));
	assertEquals(conf.get(ConfigurationKeys.CONF_PATH), confPathValue);
	// check default
	assertEquals(
		conf.get(ConfigurationKeys.CONF_PATH + " other key", "blubb"),
		"blubb");

    }

}
