package Tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        glue = {"steps_definitions"},
        plugin = {"pretty", "html:report"},
        tags = "@buyInMyShop or @newAddress"
)

public class RunCucumberTest {

}
