package com.aventstack.extentreports.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.ReporterConfigurable;

public class ExtentService
        implements
            Serializable {

    private static final long serialVersionUID = -5008231199972325650L;

    private static Properties properties;

    public static synchronized ExtentReports getInstance() {
        return ExtentReportsLoader.INSTANCE;
    }

    public static Object getProperty(String key) {
        String sys = System.getProperty(key);
        return sys == null ? (properties == null ? null : properties.get(key)) : sys;
    }

    @SuppressWarnings("unused")
    private ExtentReports readResolve() {
        return ExtentReportsLoader.INSTANCE;
    }

    private static class ExtentReportsLoader {

        private static final ExtentReports INSTANCE = new ExtentReports();
        private static final String[] DEFAULT_SETUP_PATH = new String[]{
                "extent.properties",
                "com/aventstack/adapter/extent.properties"
        };
        private static final String OUTPUT_PATH = "test-output/";
        private static final String EXTENT_REPORTER = "extent.reporter";
        private static final String START = "start";
        private static final String CONFIG = "config";
        private static final String OUT = "out";
        private static final String DELIM = ".";

        private static final String SPARK = "spark";
        private static final String INIT_SPARK_KEY = EXTENT_REPORTER + DELIM + SPARK + DELIM + START;
        private static final String CONFIG_SPARK_KEY = EXTENT_REPORTER + DELIM + SPARK + DELIM + CONFIG;
        private static final String OUT_SPARK_KEY = EXTENT_REPORTER + DELIM + SPARK + DELIM + OUT;

        static {
            createViaProperties();
            createViaSystem();
        }

        private static void createViaProperties() {
            ClassLoader loader = ExtentReportsLoader.class.getClassLoader();
            Optional<InputStream> is = Arrays.stream(DEFAULT_SETUP_PATH)
                    .map(x -> loader.getResourceAsStream(x))
                    .filter(x -> x != null)
                    .findFirst();
            if (is.isPresent()) {
                Properties properties = new Properties();
                try {
                    properties.load(is.get());
                    ExtentService.properties = properties;

                    if (properties.containsKey(INIT_SPARK_KEY)
                            && "true".equals(String.valueOf(properties.get(INIT_SPARK_KEY))))
                        initSpark(properties);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private static void createViaSystem() {
            if ("true".equals(System.getProperty(INIT_SPARK_KEY)))
                initSpark(null);
        }

        private static String getOutputPath(Properties properties, String key) {
            String out;
            if (properties != null && properties.get(key) != null)
                out = String.valueOf(properties.get(key));
            else
                out = System.getProperty(key);
            out = out == null || out.equals("null") || out.isEmpty() ? OUTPUT_PATH + key.split("\\.")[2] + "/" : out;
            return out;
        }

        private static void initSpark(Properties properties) {
            String out = getOutputPath(properties, OUT_SPARK_KEY);
            ExtentSparkReporter spark = new ExtentSparkReporter(out);
            attach(spark, properties, CONFIG_SPARK_KEY);
        }

        private static void attach(ReporterConfigurable r, Properties properties, String configKey) {
            Object configPath = properties == null
                    ? System.getProperty(configKey)
                    : properties.get(configKey);
            if (configPath != null && !String.valueOf(configPath).isEmpty())
                try {
                    r.loadXMLConfig(String.valueOf(configPath));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            INSTANCE.attachReporter((ExtentObserver<?>) r);
        }
    }

}
