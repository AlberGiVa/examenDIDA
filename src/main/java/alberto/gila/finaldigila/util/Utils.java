package alberto.gila.finaldigila.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Utils {

    // Alerta rápida
    public static void alerta(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK).showAndWait();
    }
}