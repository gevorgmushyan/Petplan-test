package model;

import java.io.File;

public abstract class OSInfo {
    public static final  String path = System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "webdrivers" + File.separator;
    public static final  Boolean arch32 = System.getProperty("os.arch").contains("32");
    public static final  Boolean arch64 = System.getProperty("os.arch").contains("64");
    public static final  Boolean Windows = System.getProperty("os.name").contains("Windows");
    public static final  Boolean Linux = System.getProperty("os.name").contains("Linux");
}
