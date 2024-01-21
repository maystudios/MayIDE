module net.maystudios.mayide {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires org.fxmisc.richtext;
    requires org.testng;

    opens net.maystudios.mayide to javafx.fxml;
    exports net.maystudios.mayide.Gui to javafx.graphics;
    exports net.maystudios.mayide;
}