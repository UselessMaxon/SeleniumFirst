package com.MA.tests.My.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static project.DriverManager.*;


public class DriverExtension implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        getWebDriver();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        closeDriver();
    }

}
