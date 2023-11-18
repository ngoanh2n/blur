package com.github.ngoanh2n.blur.selenide;

import com.codeborne.selenide.SelenideConfig;
import com.github.ngoanh2n.blur.BlurConfig;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author ngoanh2n
 */
public class ExtendTest {
    @Test
    void selenideConfig() {
        int blurFields = BlurConfig.class.getDeclaredFields().length - 1;
        int selenideFields = FieldUtils.getAllFields(SelenideConfig.class).length;
        Assertions.assertEquals(selenideFields, blurFields);
    }
}
