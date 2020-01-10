package com.github.elwyncrestha.darazreplica.util;

/**
 * @author Elvin Shrestha on 1/10/20
 */
public class StrictMode {

    public static void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy =
                new android.os.StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
}
