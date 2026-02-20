package alberto.gila.finaldigila.controller;

import alberto.gila.finaldigila.DAO.VideojuegoDAO;
import alberto.gila.finaldigila.DAO.UsuarioDAO;
import alberto.gila.finaldigila.model.Videojuego;
import alberto.gila.finaldigila.model.Usuario;
import alberto.gila.finaldigila.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private TableView<Videojuego> tablaVideojuegos;
    @FXML private TableColumn<Videojuego, String> colNombre;
    @FXML private TableColumn<Videojuego, ?> colCompletado, colFecha;
    @FXML private Button btnCrear, btnEditar, btnEliminar, btnFavorito;
    @FXML private Label lblUsuario;

    @Override
    public void initialize(ResourceBundle r) {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCompletado.setCellValueFactory(new PropertyValueFactory<>("completado"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaLanzamiento"));


        cargarDatos();
        gestionarPermisos();
    }

    private void cargarDatos() {
        tablaVideojuegos.setItems(FXCollections.observableArrayList(VideojuegoDAO.getInstance().obtenerTodos()));
        tablaVideojuegos.refresh();
    }

    private void gestionarPermisos() {
        Usuario u = SessionManager.getInstance().getUsuarioLogueado();
        if (u == null) return;

        lblUsuario.setText("Hola: " + u.getUsername());

        boolean isAdmin = "ADMIN".equals(u.getRol());
        btnCrear.setVisible(isAdmin);
        btnEditar.setVisible(isAdmin);
        btnEliminar.setVisible(isAdmin);
        btnFavorito.setVisible(true);
    }

    @FXML
    public void marcarFavorito() {
        Videojuego sel = tablaVideojuegos.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        Usuario u = SessionManager.getInstance().getUsuarioLogueado();
        u.setEquipoFavorito(sel);
        UsuarioDAO.getInstance().guardar(u);

    }

    @FXML
    public void eliminarEquipo() {
        Videojuego sel = tablaVideojuegos.getSelectionModel().getSelectedItem();
        if (sel != null) {
            VideojuegoDAO.getInstance().borrar(sel);
            cargarDatos();
        }
    }

    @FXML public void abrirCrear() { abrirModal(null); }
    @FXML public void abrirEditar() {
        if(tablaVideojuegos.getSelectionModel().getSelectedItem() != null)
            abrirModal(tablaVideojuegos.getSelectionModel().getSelectedItem());
    }

    private void abrirModal(Videojuego videojuego) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/alberto/gila/finaldigila/registro-juego.fxml"));
            Parent root = loader.load();

            RegistroJuegoController controller = loader.getController();
            if (videojuego != null) controller.Videojuegos(Videojuego);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            cargarDatos();
        } catch (Exception e) { e.printStackTrace(); }
    }
}