package alberto.gila.finaldigila.controller;

import alberto.gila.finaldigila.util.SessionManager;
import alberto.gila.finaldigila.util.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppShellController implements Initializable {


    private static AppShellController instance;
    public static AppShellController getInstance() { return instance; }

    @FXML private StackPane mainContainer;
    @FXML private Button btnLogout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        setBotonVisible(false);
    }

    public void setBotonVisible(boolean visible) {
        if (btnLogout != null) btnLogout.setVisible(visible);
    }

    @FXML
    public void logout() {

        SessionManager.getInstance().logout();


        setBotonVisible(false);


        setView(View.LOGIN);
    }


    public void setView(View view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view.getFxmlpath()));
            Parent root = loader.load();
            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}