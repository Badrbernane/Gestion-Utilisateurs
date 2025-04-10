package org.bernard.myappjavafx.Controllers;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bernard.myappjavafx.DAO.UserDAO;
import org.bernard.myappjavafx.Models.User;

public class UserController {
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox adminCheckBox;                  //@FXML: Links these fields to corresponding UI elements in the FXML file.
    @FXML private TableView<User> userTable;               //Fields: Represent UI components like text fields, checkboxes, and table columns.
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colLogin;
    @FXML private TableColumn<User, Boolean> colAdmin;

    private ObservableList<User> userList;   //ObservableList: A dynamic list that updates the UI automatically when modified.

    @FXML //  @FXML: Marks this method to be called automatically after the FXML file is loaded.
    public void initialize() { //setCellValueFactory: Binds table columns to specific properties of the User model.
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdUser()).asObject());
        colLogin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));
        colAdmin.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAdmin()).asObject());

        loadUsers(); // loadUsers(): Loads user data into the table.
        // Ajout d'un listener pour remplir les champs lorsqu'un utilisateur est sélectionné
        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loginField.setText(newSelection.getLogin());
                passwordField.setText(newSelection.getPassword());
                adminCheckBox.setSelected(newSelection.isAdmin());
            }  //Listener: Updates form fields when a user is selected in the table.
        });
    }

    private void loadUsers() { //Fetches all users from the database using UserDAO and populates the table.
        userList = FXCollections.observableArrayList(UserDAO.getAllUsers());
        userTable.setItems(userList);
    }

    @FXML
    private void addUser() {
        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert("Erreur", "Les champs Login et Mot de passe sont obligatoires.");
            return;
        } //Checks if the login and password fields are not empty before proceeding.
        User newUser = new User(loginField.getText(), passwordField.getText(), 0); // idAdmin par défaut 0 // create new User object
        newUser.setAdmin(adminCheckBox.isSelected()); // Set Admin: Sets the admin status based on the checkbox.

        UserDAO.addUser(newUser);
        loadUsers();                             //Save User: Adds the user to the database and refreshes the table.
        clearFields();
    }

    @FXML
    private void updateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem(); //Selection Check: Ensures a user is selected in the table.
        if (selectedUser != null) {  //Validation: Checks if login and password fields are filled.
            if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert("Erreur", "Les champs Login et Mot de passe sont obligatoires.");
                return;
            }
            selectedUser.setLogin(loginField.getText());
            selectedUser.setPassword(passwordField.getText());
            selectedUser.setAdmin(adminCheckBox.isSelected());

            UserDAO.updateUser(selectedUser);    //Update User: Updates the selected user's properties and saves changes to the database.
            loadUsers();
        } else {
            showAlert("Erreur", "Sélectionnez un utilisateur à modifier.");
        }
    }

    @FXML
    private void deleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserDAO.deleteUser(selectedUser.getIdUser());
            loadUsers();
        } else {
            showAlert("Erreur", "Sélectionnez un utilisateur à supprimer.");
        }
    }

    private void clearFields() {
        loginField.clear();
        passwordField.clear();
        adminCheckBox.setSelected(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML private TextField passwordVisibleField;
    @FXML private Button togglePasswordButton;

    private boolean isPasswordVisible = false;

    @FXML
    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            passwordVisibleField.setText(passwordField.getText());
            passwordVisibleField.setVisible(true);
            passwordField.setVisible(false);
        } else {
            passwordField.setText(passwordVisibleField.getText());
            passwordField.setVisible(true);
            passwordVisibleField.setVisible(false);
        }
    }
}


