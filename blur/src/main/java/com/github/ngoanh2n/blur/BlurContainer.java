package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.github.ngoanh2n.Commons;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurContainer extends WebDriverThreadLocalContainer {
    public BlurContainer(Config config) {
        super();
        overrideSelenideFacade(config);
    }

    private void overrideSelenideFacade(Config config) {
        Driver driver = new BlurDriver(config);
        SelenideDriver selenideDriver = new SelenideDriver(config, driver);
        Commons.writeField(Configuration.class, "defaults", config);
        Commons.writeField(WebDriverRunner.class, "staticSelenideDriver", selenideDriver);
    }
}
