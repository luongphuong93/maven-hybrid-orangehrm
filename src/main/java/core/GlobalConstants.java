package core;

import java.io.File;

public class GlobalConstants {
    // System Info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    // Wait Infor
    public static final int SHORT_TIMEOUT = 5;
    public static final int LONG_TIMEOUT = 15;

    // Download/ Upload file
    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
}
