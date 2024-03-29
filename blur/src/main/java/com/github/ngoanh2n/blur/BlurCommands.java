package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.appium.commands.SelenideAppiumCommands;
import com.codeborne.selenide.commands.Commands;
import com.github.ngoanh2n.blur.appium.commands.DoubleTap;
import com.github.ngoanh2n.blur.appium.commands.LongPress;
import com.github.ngoanh2n.blur.appium.commands.Swipe;
import com.github.ngoanh2n.blur.appium.commands.Tap;
import com.github.ngoanh2n.wdc.WebDriverChecker;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;

/**
 * Load {@link Commands com.codeborne.selenide.commands}.<br>
 * There are new nad overridden commands for Appium.<br><br>
 *
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @since 2020
 */
@ParametersAreNonnullByDefault
public class BlurCommands extends SelenideAppiumCommands {
    /**
     * Default constructor.<br>
     * Add new and overridden commands for Appium.
     */
    public BlurCommands() {
        if (WebDriverChecker.isMobile() || WebDriverChecker.isPCNative()) {
            getNewAppiumCommands().forEach(this::add);
            getOverriddenSelenideCommands().forEach(this::add);
        }
    }

    //-------------------------------------------------------------------------------//

    /**
     * Refresh the appropriate command list for each platform.
     */
    public static void refresh() {
        Commands commands = Commands.getInstance();
        if (WebDriverChecker.isMobile() || WebDriverChecker.isPCNative()) {
            getNewAppiumCommands().forEach(commands::add);
            getOverriddenSelenideCommands().forEach(commands::add);
        } else {
            getSelenideCommands().forEach(commands::add);
        }
    }

    //-------------------------------------------------------------------------------//

    private static HashMap<String, Command<?>> getNewAppiumCommands() {
        HashMap<String, Command<?>> commands = new HashMap<>();
        commands.put("tap", new Tap());
        commands.put("doubleTap", new DoubleTap());
        commands.put("longPress", new LongPress());
        commands.put("swipe", new Swipe());
        return commands;
    }

    private static HashMap<String, Command<?>> getOverriddenSelenideCommands() {
        HashMap<String, Command<?>> commands = new HashMap<>();
        commands.put("doubleClick", new DoubleTap());
        return commands;
    }

    private static HashMap<String, Command<?>> getSelenideCommands() {
        HashMap<String, Command<?>> commands = new HashMap<>();
        commands.put("doubleClick", new com.codeborne.selenide.commands.DoubleClick());
        return commands;
    }
}
