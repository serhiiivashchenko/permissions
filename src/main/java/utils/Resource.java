package utils;

import org.junit.Assert;

import java.io.File;
import java.net.URL;

/**
 * Created by Serhii on 5/8/16
 */
public class Resource {

    public static File getResource(String path) {
        URL resource = Resource.class.getClassLoader().getResource(path);
        Assert.assertNotNull("Could not load " + path + " resource", resource);
        return new File(resource.getPath());
    }
}
