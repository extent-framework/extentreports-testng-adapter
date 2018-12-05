## extentreports-testng-adapter

### Docs

See [here](http://extentreports.com/docs/versions/4/java/testng.html) for complete docs.

### Usage

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

Configuration can be added under `src/test/resources` as shown [here](https://github.com/extent-framework/examples/tree/master/extentreports-testng-adapter-example/src/test/resources).  Note the contents of `extent.properties` which can be used to enable/disable reporters, set path to the configuration file, and also to output to a desired location.

```
extent.reporter.avent.start=false
extent.reporter.bdd.start=false
extent.reporter.cards.start=false
extent.reporter.email.start=false
extent.reporter.html.start=true
extent.reporter.klov.start=false
extent.reporter.logger.start=false
extent.reporter.tabular.start=false

extent.reporter.avent.config=
extent.reporter.bdd.config=
extent.reporter.cards.config=
extent.reporter.email.config=
extent.reporter.html.config=src/test/resources/html-config.xml
extent.reporter.klov.config=
extent.reporter.logger.config=
extent.reporter.tabular.config=

extent.reporter.avent.out=test-output/AventReport/
extent.reporter.bdd.out=test-output/BddReport/
extent.reporter.cards.out=test-output/CardsReport/
extent.reporter.email.out=test-output/EmailReport/ExtentEmail.html
extent.reporter.html.out=test-output/HtmlReport/ExtentHtml.html
extent.reporter.logger.out=test-output/LoggerReport/
extent.reporter.tabular.out=test-output/TabularReport/
```

### License

extentreports-testng-adapter is MIT licensed.
