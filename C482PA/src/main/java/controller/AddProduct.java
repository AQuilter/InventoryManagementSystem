package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** The controller class for the AddProduct.fxml page. Implements Initializable. */
public class AddProduct implements Initializable {

    @FXML private TextField searchPartsField;
    @FXML private TextField nameField;
    @FXML private TextField inventoryField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private AnchorPane anchorPane;

    // partsTableview
    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<Part, Integer> idCol;
    @FXML private TableColumn<Part, String> nameCol;
    @FXML private TableColumn<Part, Integer> inventoryCol;
    @FXML private TableColumn<Part, Double> priceCol;

    // associatedPartTableView
    @FXML private TableView<Part> associatedPartTableView;
    @FXML private TableColumn<Part, Integer> associatedIdCol;
    @FXML private TableColumn<Part, String> associatedNameCol;
    @FXML private TableColumn<Part, Integer> associatedInventoryCol;
    @FXML private TableColumn<Part, Double> associatedPriceCol;
    @FXML private Label exceptionLabel;
    @FXML private Label searchErrorLabel;

    ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    /** Saves the field information of the product.
     Checks for exceptions and will notify user if any are found.
     Occurs when the "SAVE" button is clicked.
     @param actionEvent The button press.
     */
    @FXML public void saveBtnClicked(ActionEvent actionEvent) throws IOException{
        int id = 0; // loops through the allProducts list and adds + 1 to the last ID it finds.
        for (int i=0; i<Inventory.getAllProducts().size(); i++){
            if (id <= Inventory.getAllProducts().get(i).getId())
                id = Inventory.getAllProducts().get(i).getId() + 1;
        }
        try{ // parsing text fields to appropriate variables.
            String name = nameField.getText().strip();
            double price = Double.parseDouble(priceField.getText().strip());
            int stock = Integer.parseInt(inventoryField.getText().strip());
            int max = Integer.parseInt(maxField.getText().strip());
            int min = Integer.parseInt(minField.getText().strip());

            // checking for exceptions
            if (max < min) {
                throw new Exception("minGreaterThanMax");
            }
            if (stock < min || stock > max) {
                throw new Exception("inventoryError");
            }

            // creating the product object (if there were no exceptions found)
            Product product = new Product(id, name, price, stock, min, max);

            // adding associatedParts to the product
            for (int i=0; i < associatedParts.size(); i++){
                product.addAssociatedPart(associatedParts.get(i));
            }
            Inventory.addProduct(product);
            backToMainScreen(actionEvent);
        } catch (Exception exception){
            if (exception.getMessage().equals("minGreaterThanMax")) {
                exceptionLabel.setText("Min should be less than max.");
            }
            else if (exception.getMessage().equals("inventoryError")) {
                exceptionLabel.setText("Inventory should be between min and max.");
            }
            else {
                exceptionLabel.setText("Please enter appropriate information.");
            }
        }
    }

    /** Adds a selected part from the parts list to the associatedParts list.
     Occurs when the "ADD" button is clicked.
     */
    @FXML public void addBtnClicked(){
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        if (part != null && !associatedParts.contains(part)){
            associatedParts.add(part);
            associatedPartTableView.setItems(associatedParts);
        }
    }


    /** Searches for a specific part either by ID or name.
     First it checks that a user has entered something into the text field.
     Then it looks for a matching ID or name and returns a list of parts that match.
     Returns a prompt if the part was not found.
     */
    @FXML public void searchPartsField(){
        String userText = searchPartsField.getText();
        if (userText.matches("")){
            partsTableView.setItems(Inventory.getAllParts());
            searchPartsField.setPromptText("Search by ID or Name");
            anchorPane.requestFocus();
        }
        else{
            try{
                int id = Integer.parseInt(userText);
                ObservableList<Part> part = FXCollections.observableArrayList();
                part.add(Inventory.lookupPart(id));
                if (part.get(0) != null)
                    partsTableView.setItems(part);
                else{
                    anchorPane.requestFocus();
                    searchPartsField.setText("");
                    searchErrorLabel.setText("Product not found!");
                }
                return;
            }catch (Exception ignored){}
            if (Inventory.lookupPart(userText).size() == 0){
                anchorPane.requestFocus();
                searchPartsField.setText("");
                searchErrorLabel.setText("Product not found!");
            }
            else
                partsTableView.setItems(Inventory.lookupPart(userText));
        }
    }

    /** Removes a selected part from the associatedParts.
     Occurs when the "REMOVE ASSOCIATED PART" button is clicked.
     @param actionEvent The button press.
     */
    @FXML public void removeAssociatedPartBtnClicked(ActionEvent actionEvent){
        Part part = associatedPartTableView.getSelectionModel().getSelectedItem();
        if (part != null && associatedParts.contains(part)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            alert.setHeaderText("Are you sure you want to remove " + part.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES){
                associatedParts.remove(part);
                associatedPartTableView.setItems(associatedParts);
            }
        }
    }


    /** Returns the user to the MainScreen.fxml page if the "CANCEL" button is clicked.
     @param actionEvent The button press.
     */
    @FXML public void backToMainScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 950, 450);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }


    /** Initialize method to set up our part and associatedPart table views.
     @param url URL
     @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // partsTableView
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTableView.setItems(Inventory.getAllParts());

        // associatedPartTableView
        associatedIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInventoryCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        associatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
