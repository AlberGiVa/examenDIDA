package alberto.gila.finaldigila.controller;

import alberto.gila.finaldigila.DAO.UsuarioDAO;
import alberto.gila.finaldigila.util.SessionManager;
import alberto.gila.finaldigila.util.Utils;
import alberto.gila.finaldigila.util.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Button btnLogin;

    @FXML
    public void irAHome() {
        String u = txtUser.getText();
        String p = txtPass.getText();


        var usuario = UsuarioDAO.getInstance().validarLogin(u, p);

        if (usuario != null) {

            SessionManager.getInstance().login(usuario);

            if (AppShellController.getInstance() != null) {

                AppShellController.getInstance().setBotonVisible(true);


                AppShellController.getInstance().setView(View.HOME);
            }

        } else {
            Utils.alerta("Credenciales incorrectos");
        }
    }

    @FXML
    public void irARegistro() {

        if (AppShellController.getInstance() != null) {
            AppShellController.getInstance().setView(View.REGISTER);
        }
    }
}