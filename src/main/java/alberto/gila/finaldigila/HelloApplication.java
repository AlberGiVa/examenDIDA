package alberto.gila.finaldigila;

import alberto.gila.finaldigila.controller.AppShellController;
import alberto.gila.finaldigila.util.HibernateUtil;
import alberto.gila.finaldigila.util.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("🚀 Arrancando aplicación...");

        // CONEXIÓN Y CREACIÓN DE TABLAS
        try {
            System.out.println("⏳ Conectando con Hibernate...");
            // Esto fuerza la lectura del hibernate.cfg.xml y la creación de tablas
            HibernateUtil.getSessionFactory();
            System.out.println("✅ Conexión establecida y tablas revisadas.");
        } catch (Exception e) {
            System.err.println("❌❌ ERROR CRÍTICO AL INICIAR BASE DE DATOS ❌❌");
            e.printStackTrace(); // <--- POR QUÉ NO SE CREA LA DB
            mostrarError("Error de Base de Datos", "No se pudo conectar o crear las tablas.\nMira la consola para más detalles.");
        }

        // 2. CARGA App Shell
        String rutaShell = "/alberto/gila/finaldigila/appshell.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaShell));

        // Comprobación de seguridad
        if (loader.getLocation() == null) {
            System.err.println("❌ ERROR: No encuentro el archivo FXML en: " + rutaShell);
            mostrarError("Error FXML", "No se encuentra el archivo de vista principal:\n" + rutaShell);
            return;
        }

        Parent root = loader.load();

        // Cargar Login
        AppShellController controller = loader.getController();
        if (controller != null) {
            controller.setView(View.LOGIN);
        }

        Scene scene = new Scene(root);
        stage.setTitle("Examen DI - Alberto Gila");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        HibernateUtil.shutdown();
    }

    // Alertas visuales si algo falla
    private void mostrarError(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Fatal");
        alert.setHeaderText(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}