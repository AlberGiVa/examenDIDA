package alberto.gila.finaldigila.util;

public enum View {
    LOGIN("/alberto/gila/finadigila/login.fxml", "Login"),
    HOME("/alberto/gila/finaldigila/biblioteca.fxml", "Home"),
    REGISTER("/alberto/gila/finaldigila/register.fxml", "Registro");

    private final String fxmlpath;
    private final String title;

    View(String fxmlpath, String title){
        this.fxmlpath = fxmlpath;
        this.title = title;
    }

    public String getFxmlpath(){
        return this.fxmlpath;
    }

    public String getTitle() {
        return this.title;
    }
}