package com.github.ngoanh2n.blur;

import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;

/**
 * Replace for {@link com.codeborne.selenide.impl.WebDriverThreadLocalContainer}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurContainer extends WebDriverThreadLocalContainer {
    private final BlurConfig config;

    public BlurContainer(BlurConfig config) {
        super();
        this.config = config;
    }

    //-------------------------------------------------------------------------------//

    public BlurConfig config() {
        return config;
    }
}
