package com.olebas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    private Properties prop = new Properties();

    public ReadConfig() {

        File file = new File((System.getProperty("user.dir") + "/src/main/resources/properties/config.properties"));

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGraphqlURL() {
        return prop.getProperty("GRAPHQL_URI");
    }

    public String getAdminLogin() {
        return prop.getProperty("ADMIN_LOGIN");
    }

    public String getAdminPassword() {
        return prop.getProperty("ADMIN_PASSWORD");
    }
}
