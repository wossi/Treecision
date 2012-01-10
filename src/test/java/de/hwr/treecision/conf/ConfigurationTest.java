package de.hwr.treecision.conf;

import java.nio.file.Paths;

import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {

    private static final String confPathValue = "/usr/local/treecision/conf";

    private Configuration conf = new Configuration();

    {
	// put various kinds of things to the conf
	conf.setInt(ConfigurationKeys.TEST_SET_SIZE_PERCENT, 20);
	conf.set(ConfigurationKeys.CONF_PATH, confPathValue);
	conf.setBoolean(ConfigurationKeys.TRAIN_ALL_ITEMS, true);
    }

    public void testConfiguration() throws Exception {
	// check boolean
	assertEquals(conf.getBoolean(ConfigurationKeys.TRAIN_ALL_ITEMS, false), true);
	// check default
	assertEquals(conf.getBoolean(ConfigurationKeys.TRAIN_ALL_ITEMS + " other key", false), false);
	// check boolean as string
	assertNotNull(conf.get(ConfigurationKeys.TRAIN_ALL_ITEMS));
	assertEquals(conf.get(ConfigurationKeys.TRAIN_ALL_ITEMS), Boolean.TRUE.toString());

	// check int
	assertEquals(conf.getInt(ConfigurationKeys.TEST_SET_SIZE_PERCENT, 0), 20);
	// check default
	assertEquals(conf.getInt(ConfigurationKeys.TEST_SET_SIZE_PERCENT + " other key", 0), 0);
	// check int as string
	assertNotNull(conf.get(ConfigurationKeys.TEST_SET_SIZE_PERCENT));
	assertEquals(conf.get(ConfigurationKeys.TEST_SET_SIZE_PERCENT), "20");

	// check string
	assertNotNull(conf.get(ConfigurationKeys.CONF_PATH));
	assertEquals(conf.get(ConfigurationKeys.CONF_PATH), confPathValue);
	// check default
	assertEquals(conf.get(ConfigurationKeys.CONF_PATH + " other key", "blubb"), "blubb");
    }

    public void testSerializeWithGSON() throws Exception {
	conf.saveToFile(Paths.get("/tmp/conf.json"));
	this.conf = Configuration.getConfiguration(Paths.get("/tmp/conf.json"));
	// just rerun the test again
	testConfiguration();
    }

}
