package com.github.rusakovichma.dvwa.bdd;

import java.io.File;

public class DriverHelper {

    private static final String DRIVER_BASE_PATH = "src/main/resources/drivers";

    private static String getDriverPath(Driver driver){
        if (driver == Driver.Gecko){
            throw new UnsupportedOperationException("The driver is not supported");
        }
        StringBuilder pathBuilder = new StringBuilder(DRIVER_BASE_PATH)
                .append(File.separator)
                .append(driver.name().toLowerCase())
                .append(File.separator);

        final OsCheck.OSType os = OsCheck.getOperatingSystemType();
        switch (os){
            case Windows:
                pathBuilder.append("win");
                break;
            case Linux:
                pathBuilder.append("linux");
                break;
            case MacOS:
                pathBuilder.append("mac");
                break;
            case Other:
                throw new UnsupportedOperationException("Unsupported OS");
        }
        pathBuilder.append(File.separator)
                .append(driver.getDriverName());

        if (os == OsCheck.OSType.Windows){
            pathBuilder.append(".exe");
        }

        return pathBuilder.toString();
    }

    public static void setupDriver(Driver driver){
        if (driver == Driver.Gecko){
            throw new UnsupportedOperationException("The driver is not supported");
        }
        System.setProperty(driver.getDriverProp(), getDriverPath(driver));
    }

}
