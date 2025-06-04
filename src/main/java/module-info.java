module fatec.sjc.sp.exame2_lp1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens fatec.sjc.sp.exame2_lp1.classes to javafx.base;
    opens fatec.sjc.sp.exame2_lp1 to javafx.fxml;
    exports fatec.sjc.sp.exame2_lp1;
    exports fatec.sjc.sp.exame2_lp1.controller;
    opens fatec.sjc.sp.exame2_lp1.controller to javafx.fxml;
}