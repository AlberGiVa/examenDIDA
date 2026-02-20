module alberto.gila.finaldigila {

    // 1. JAVAFX
    requires javafx.controls;
    requires javafx.fxml;

    // 2. LIBRERÍAS EXTERNAS
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    requires jbcrypt;
    requires java.desktop;

    // 3. PERMISOS (EXPORTS Y OPENS)

    // Raíz
    opens alberto.gila.finaldigila to javafx.fxml;
    exports alberto.gila.finaldigila;

    // Controladores
    exports alberto.gila.finaldigila.controller;
    opens alberto.gila.finaldigila.controller to javafx.fxml;

    // Modelos para BD
    exports alberto.gila.finaldigila.model;
    opens alberto.gila.finaldigila.model to org.hibernate.orm.core;

    // Permite que Utils y SecurityUtil sean visibles
    exports alberto.gila.finaldigila.util;
    opens alberto.gila.finaldigila.util to javafx.fxml, org.hibernate.orm.core;
}