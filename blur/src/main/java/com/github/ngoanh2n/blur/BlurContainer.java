package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.github.ngoanh2n.Commons;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurContainer extends WebDriverThreadLocalContainer {
    public BlurContainer(Config config) {
        super();
        Commons.writeField(Configuration.class, "defaults", config);
        config.browser();
    }
}
