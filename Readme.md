## ExtentReports Plugin for TestNG [![Maven Central](https://img.shields.io/maven-central/v/com.aventstack/extentreports-testng-adapter.svg?maxAge=300)](http://search.maven.org/#search|ga|1|g:"com.aventstack")

### Docs

See [here](http://extentreports.com/docs/versions/4/java/testng.html) for complete docs.

See the [Listeners](http://extentreports.com/docs/versions/4/java/testng.html#listeners) section for usage instructions.

### Examples

An example project is available [here](https://github.com/extent-framework/examples/tree/master/extentreports-testng-adapter-example) to understand the usage.

Note: when using this adapter, you are not required to add any code in your project. The adapter works for you to generate the report, perform auto-configuration through the configuration file, and you can also enable/disable reporters from the properties file.

See [this](https://github.com/extent-framework/examples/blob/master/extentreports-testng-adapter-example/src/test/java/com/aventstack/extentreports/adapter/testng/tests/SimpleAssertTests.java) example of how a test is created, marked with the listener.  Note: a good approach is to mark a common base class with the `@Listener` only once.

```
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ExtentITestListenerClassAdapter.class})
public class SimpleAssertTests {

    @Test
    public void passTest() {
        Assert.assertTrue(true);
    }
    
    @Test
    public void failTest() {
        Assert.assertTrue(false);
    }
    
}
```

### Config

Configuration can be added under `src/test/resources` as shown [here](https://github.com/extent-framework/examples/tree/master/extentreports-testng-adapter-example/src/test/resources).  Note the contents of `extent.properties` which can be used to enable/disable reporters, set path to the configuration file, and also to output to a desired location.

```
# spark-reporter
extent.reporter.spark.start=true
extent.reporter.spark.config=
extent.reporter.spark.out=test-output/SparkReport/Index.html

# klov-reporter
extent.reporter.klov.start=false
extent.reporter.klov.config=src/test/resources/klov.properties

# json-reporter
extent.reporter.json.start=false
extent.reporter.json.out=test-output/JsonReport/Extent.json
```

#### Klov

If you are using Klov (version 1.0+), extra configuration would be required, which can be loaded from `extent.reporter.klov.config` above.

```
mongodb.host=127.0.0.1
mongodb.port=27017
mongodb.uri=
klov.host=http://127.0.0.1
klov.port=80
klov.project.name=ProjectName
klov.report.name=
```

### License

TestNG plugin for ExtentReports is open-source software and licensed under Apache-2.
