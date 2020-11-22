package utils;

import org.junit.After;
import org.junit.Before;

public class BaseHooks {
    @Before
    public void setupDriver() {
        DriverManager.setupDriver();
    }

    @After
    public void quitDriver() {
        DriverManager.quitDriver();
    }
}
