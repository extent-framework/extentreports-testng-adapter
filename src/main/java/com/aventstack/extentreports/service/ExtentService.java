package com.aventstack.extentreports.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ConfigurableReporter;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentBDDReporter;
import com.aventstack.extentreports.reporter.ExtentCardsReporter;
import com.aventstack.extentreports.reporter.ExtentEmailReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import com.aventstack.extentreports.reporter.ExtentTabularReporter;

public class ExtentService 
    implements Serializable {

    private static final long serialVersionUID = -5008231199972325650L;

    public static synchronized ExtentReports getInstance() {
        return ExtentReportsLoader.INSTANCE;
    }

    @SuppressWarnings("unused")
    private ExtentReports readResolve() {
        return ExtentReportsLoader.INSTANCE;
    }
    
    private static class ExtentReportsLoader {
        
        private static final ExtentReports INSTANCE = new ExtentReports();
        private static final String[] DEFAULT_SETUP_PATH = new String[] {
                "extent.properties",
                "com/aventstack/adapter/extent.properties"
        };
        private static final String OUTPUT_PATH = "test-output/";
        private static final String EXTENT_REPORTER = "extent.reporter";
        private static final String START = "start";
        private static final String CONFIG = "config";
        private static final String OUT = "out";
        private static final String DELIM = ".";
        
        private static final String AVENT = "avent";
        private static final String BDD = "bdd";
        private static final String CARDS = "cards";
        private static final String EMAIL = "email";
        private static final String HTML = "html";
        private static final String KLOV = "klov";
        private static final String LOGGER = "logger";
        private static final String TABULAR = "tabular";
        
        private static final String INIT_AVENT_KEY = EXTENT_REPORTER + DELIM + AVENT + DELIM + START;
        private static final String INIT_BDD_KEY = EXTENT_REPORTER + DELIM + BDD + DELIM + START;
        private static final String INIT_CARDS_KEY = EXTENT_REPORTER + DELIM + CARDS + DELIM + START;
        private static final String INIT_EMAIL_KEY = EXTENT_REPORTER + DELIM + EMAIL + DELIM + START;
        private static final String INIT_HTML_KEY = EXTENT_REPORTER + DELIM + HTML + DELIM + START;
        private static final String INIT_KLOV_KEY = EXTENT_REPORTER + DELIM + KLOV + DELIM + START;
        private static final String INIT_LOGGER_KEY = EXTENT_REPORTER + DELIM + LOGGER + DELIM + START;
        private static final String INIT_TABULAR_KEY = EXTENT_REPORTER + DELIM + TABULAR + DELIM + START;
        
        private static final String CONFIG_AVENT_KEY = EXTENT_REPORTER + DELIM + AVENT + DELIM + CONFIG;
        private static final String CONFIG_BDD_KEY = EXTENT_REPORTER + DELIM + BDD + DELIM + CONFIG;
        private static final String CONFIG_CARDS_KEY = EXTENT_REPORTER + DELIM + CARDS + DELIM + CONFIG;
        private static final String CONFIG_EMAIL_KEY = EXTENT_REPORTER + DELIM + EMAIL + DELIM + CONFIG;
        private static final String CONFIG_HTML_KEY = EXTENT_REPORTER + DELIM + HTML + DELIM + CONFIG;
        private static final String CONFIG_KLOV_KEY = EXTENT_REPORTER + DELIM + KLOV + DELIM + CONFIG;
        private static final String CONFIG_LOGGER_KEY = EXTENT_REPORTER + DELIM + LOGGER + DELIM + CONFIG;
        private static final String CONFIG_TABULAR_KEY = EXTENT_REPORTER + DELIM + TABULAR + DELIM + CONFIG;

        private static final String OUT_AVENT_KEY = EXTENT_REPORTER + DELIM + AVENT + DELIM + OUT;
        private static final String OUT_BDD_KEY = EXTENT_REPORTER + DELIM + BDD + DELIM + OUT;
        private static final String OUT_CARDS_KEY = EXTENT_REPORTER + DELIM + CARDS + DELIM + OUT;
        private static final String OUT_EMAIL_KEY = EXTENT_REPORTER + DELIM + EMAIL + DELIM + OUT;
        private static final String OUT_HTML_KEY = EXTENT_REPORTER + DELIM + HTML + DELIM + OUT;
        private static final String OUT_LOGGER_KEY = EXTENT_REPORTER + DELIM + LOGGER + DELIM + OUT;
        private static final String OUT_TABULAR_KEY = EXTENT_REPORTER + DELIM + TABULAR + DELIM + OUT;
        
        static {
            if (INSTANCE.getStartedReporters().isEmpty()) {
                createViaProperties();
                createViaSystem();
            }
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
                    
                    if (properties.containsKey(INIT_AVENT_KEY) && "true".equals(String.valueOf(properties.get(INIT_AVENT_KEY))))
                        initAvent(properties);

                    if (properties.containsKey(INIT_BDD_KEY) && "true".equals(String.valueOf(properties.get(INIT_BDD_KEY))))
                        initBdd(properties);
                        
                    if (properties.containsKey(INIT_CARDS_KEY) && "true".equals(String.valueOf(properties.get(INIT_CARDS_KEY))))
                        initCards(properties);
                    
                    if (properties.containsKey(INIT_EMAIL_KEY) && "true".equals(String.valueOf(properties.get(INIT_EMAIL_KEY))))
                        initEmail(properties);
                    
                    if (properties.containsKey(INIT_HTML_KEY) && "true".equals(String.valueOf(properties.get(INIT_HTML_KEY))))
                        initHtml(properties);
                    
                    if (properties.containsKey(INIT_KLOV_KEY) && "true".equals(String.valueOf(properties.get(INIT_KLOV_KEY))))
                        initKlov(properties);

                    if (properties.containsKey(INIT_LOGGER_KEY) && "true".equals(String.valueOf(properties.get(INIT_LOGGER_KEY))))
                        initLogger(properties);

                    if (properties.containsKey(INIT_TABULAR_KEY) && "true".equals(String.valueOf(properties.get(INIT_TABULAR_KEY))))
                        initTabular(properties);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        private static void createViaSystem() {
            if ("true".equals(System.getProperty(INIT_AVENT_KEY)))
                initAvent(null);

            if ("true".equals(System.getProperty(INIT_BDD_KEY)))
                initBdd(null);
                
            if ("true".equals(System.getProperty(INIT_CARDS_KEY)))
                initCards(null);
            
            if ("true".equals(System.getProperty(INIT_EMAIL_KEY)))
                initEmail(null);
            
            if ("true".equals(System.getProperty(INIT_HTML_KEY)))
                initHtml(null);
            
            if ("true".equals(System.getProperty(INIT_KLOV_KEY)))
                initKlov(null);

            if ("true".equals(System.getProperty(INIT_LOGGER_KEY)))
                initLogger(null);

            if ("true".equals(System.getProperty(INIT_TABULAR_KEY)))
                initTabular(null);
        }
        
        private static void initAvent(Properties properties) {
            String out = getOutputPath(properties, OUT_AVENT_KEY);
            ExtentAventReporter avent = new ExtentAventReporter(out);
            attach(avent, properties, CONFIG_AVENT_KEY);
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
        
        private static void initBdd(Properties properties) {
            String out = getOutputPath(properties, OUT_BDD_KEY);
            ExtentBDDReporter bdd = new ExtentBDDReporter(out);
            attach(bdd, properties, CONFIG_BDD_KEY);
        }
        
        private static void initCards(Properties properties) {
            String out = getOutputPath(properties, OUT_CARDS_KEY);
            ExtentCardsReporter cards = new ExtentCardsReporter(out);
            attach(cards, properties, CONFIG_CARDS_KEY);
        }
        
        private static void initEmail(Properties properties) {
            String out = getOutputPath(properties, OUT_EMAIL_KEY);
            ExtentEmailReporter email = new ExtentEmailReporter(out);
            attach(email, properties, CONFIG_EMAIL_KEY);
        }
        
        private static void initHtml(Properties properties) {
            String out = getOutputPath(properties, OUT_HTML_KEY);
            ExtentHtmlReporter html = new ExtentHtmlReporter(out);
            attach(html, properties, CONFIG_HTML_KEY);
        }
        
        private static void initKlov(Properties properties) {
            ExtentKlovReporter klov = new ExtentKlovReporter();
            String configPath = properties == null 
                    ? System.getProperty(CONFIG_KLOV_KEY)
                    : String.valueOf(properties.get(CONFIG_KLOV_KEY));
            if (configPath != null && !configPath.isEmpty())
                try {
                    klov.loadInitializationParams(configPath);
                    INSTANCE.attachReporter(klov);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
        }
        
        private static void initLogger(Properties properties) {
            String out = getOutputPath(properties, OUT_LOGGER_KEY);
            ExtentLoggerReporter logger = new ExtentLoggerReporter(out);
            attach(logger, properties, CONFIG_LOGGER_KEY);
        }
        
        private static void initTabular(Properties properties) {
            String out = getOutputPath(properties, OUT_TABULAR_KEY);
            ExtentTabularReporter tabular = new ExtentTabularReporter(out);
            attach(tabular, properties, CONFIG_TABULAR_KEY);
        }
        
        private static void attach(ConfigurableReporter r, Properties properties, String configKey) {
            Object configPath = properties == null 
                    ? System.getProperty(configKey)
                    : properties.get(configKey);
            if (configPath != null && !String.valueOf(configPath).isEmpty())
                r.loadXMLConfig(String.valueOf(configPath));
            INSTANCE.attachReporter(((ExtentReporter) r));
        }
    }
    
}
