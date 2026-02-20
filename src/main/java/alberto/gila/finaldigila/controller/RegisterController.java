package alberto.gila.finaldigila.controller;

import alberto.gila.finaldigila.DAO.UsuarioDAO;
import alberto.gila.finaldigila.model.Usuario;
import alberto.gila.finaldigila.util.SecurityUtil;
import alberto.gila.finaldigila.util.Utils;
import alberto.gila.finaldigila.util.View;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass, txtPassConfirm;

    @FXML
    public void guardarUsuario() {
        String u = txtUser.getText();
        String p = txtPass.getText();
        String pc = txtPassConfirm.getText();

        if (u.isEmpty() || p.isEmpty()) {
            Utils.alerta("Completa todos los campos");
            return;
        }
        if (!p.equals(pc)) {
            Utils.alerta("Las contraseñas no coinciden");
            return;
        }

        Usuario nuevo = new Usuario(u, SecurityUtil.hashPassword(p), "USER");

        if (UsuarioDAO.getInstance().guardar(nuevo)) {
            Utils.alerta("Usuario registrado");
            volverAlLogin();
        } else {
            Utils.alerta("Usuario ya existe");
        }
    }

    @FXML
    public void volverAlLogin() {

        if (AppShellController.getInstance() != null) {
            AppShellController.getInstance().setView(View.LOGIN);
        }
    }
}