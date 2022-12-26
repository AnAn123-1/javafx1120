package view;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import java.awt.*;

public class MyDefaultMetalTheme extends DefaultMetalTheme {
    public ColorUIResource getWindowTitleInactiveBackground() {
        return new ColorUIResource(Color.pink);
    }

    public ColorUIResource getWindowTitleBackground() {
        return new ColorUIResource(Color.pink);
    }

    public ColorUIResource getPrimaryControlHighlight() {
        return new ColorUIResource(Color.pink);
    }

    public ColorUIResource getPrimaryControlDarkShadow() {
        return new ColorUIResource(Color.pink);
    }

    public ColorUIResource getPrimaryControl() {
        return new ColorUIResource(Color.pink);
    }

    public ColorUIResource getControlHighlight() {
        return new ColorUIResource(Color.pink);
    }

    public ColorUIResource getControlDarkShadow() {
        return new ColorUIResource(Color.pink);
    }

    public ColorUIResource getControl() {
        return new ColorUIResource(Color.pink);
    }
}
