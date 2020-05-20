package com.github.rusakovichma.dvwa.bdd.selenium;

public enum Driver {
    Gecko("webdriver.gecko.driver", "geckodriver"),
    Chrome("webdriver.chrome.driver", "chromedriver");

    private final String driverProp;
    private final String driverName;

    Driver(String driverProp, String driverName) {
        this.driverProp = driverProp;
        this.driverName = driverName;
    }

    public String getDriverProp() {
        return driverProp;
    }

    public String getDriverName() {
        return driverName;
    }

}
