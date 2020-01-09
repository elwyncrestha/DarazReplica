package com.github.elwyncrestha.darazreplica.endpoint;

/**
 * @author Elvin Shrestha on 1/10/20
 */
public class ResourceLoader {
    public static String getCollectionImage(String imageName) {
        return "https://github.com/elwyncrestha/daraz-api/blob/master/images/collections/" + imageName + "?raw=true";
    }

    public static String getProductImage(String imageName) {
        return "https://github.com/elwyncrestha/daraz-api/blob/master/images/products/" + imageName + "?raw=true";
    }
}
