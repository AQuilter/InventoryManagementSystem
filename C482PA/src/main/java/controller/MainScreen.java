package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

/** The controller class for the MainScreen.fxml page. Implements Initializable.*/
public class MainScreen implements Initializable {
    @FXML private Label searchPartLabel;
    @FXML private Label searchProductLabel;
    @FXML private TextField searchPartField;
    @FXML private TextField searchProductField;
    @FXML private AnchorPane anchorPane;

    // PRODUCT TABLE
    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productIdCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productInventoryCol;
    @FXML private TableColumn<Product, Double> productPriceCol;

    // PART TABLE
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIdCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partInventoryCol;
    @FXML private TableColumn<Part, Double> partPriceCol;


    // ADD PART & PRODUCT
    /** Opens up the AddPart.fxml page when the "Add" button is clicked.
     @param actionEvent The source of the button press.
     */
    @FXML public void addPartBtnClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 450);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /** Opens up the AddProduct.fxml page when the "Add" button is clicked.
     @param actionEvent The source of the button press.
     */
    @FXML public void addProductBtnClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 650);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }
    // ----------------------------------------------------------------------------

    // MODIFY PART & PRODUCT
    /** Opens up the ModifyPart.fxml page when we click "MODIFY" button after selecting a part from the table view.
     @param actionEvent The source of the button press.
     */
    @FXML public void modifyPartsBtnClicked(ActionEvent actionEvent) throws IOException{
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyPart.fxml"));
            Parent parent = loader.load();
            Scene modifyPart = new Scene(parent);

            ModifyPart controller = loader.getController();
            controller.data(selectedPart);

            Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            window.setScene(modifyPart);
        }
    }

    /** Opens up the ModifyProduct.fxml page when we click "MODIFY" button after selecting a product from the table view.
     @param actionEvent The source of the button press.
     */
    @FXML public void modifyProductBtnClicked(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyProduct.fxml"));
            Parent parent = loader.load();
            Scene modifyProduct = new Scene(parent);

            ModifyProduct controller = loader.getController();
            controller.data(selectedProduct);

            Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            window.setScene(modifyProduct);
        }
    }
    // -----------------------------------------------------------------------------------

    // DELETE PART & PRODUCT
    /** Deletes a selected part from the allParts list which then removes it from the table view.
     Prompts the user with a confirmation before deleting.
     @param actionEvent The source of the button pressed.
     */
    @FXML public void deletePartBtnClicked(ActionEvent actionEvent){
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());
            alert.setHeaderText("Are you sure you want to delete " + selectedPart.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                Inventory.deletePart(selectedPart);
                partTableView.setItems(Inventory.getAllParts());
                 for (int i=0; i<Inventory.getAllProducts().size(); i++){
                    if (Inventory.getAllProducts().get(i).getAllAssociatedParts().contains(selectedPart))
                        Inventory.getAllProducts().get(i).deleteAssociatedPart(selectedPart);
                }
            }
        }
    }

    /** Deletes a selected product from the allProducts list which then removes it from the table view.
     Prompts the user with a confirmation before deleting.
     It won't let the user delete the product if it has any parts associated with it.
     @param actionEvent The source of the button pressed.
     */
    @FXML public void deleteProductBtnClicked(ActionEvent actionEvent) {
        Product product = productTableView.getSelectionModel().getSelectedItem();
        if (product != null && product.getAllAssociatedParts().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            alert.setHeaderText("Are you sure you want to delete " + product.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                Inventory.deleteProduct(product);
                productTableView.setItems(Inventory.getAllProducts());
            }
        }
        else if (product != null && product.getAllAssociatedParts().size() > 0 ){
            Alert alert = new Alert(Alert.AlertType.WARNING, "This product has one or more parts associated with it. Please delete those parts first.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            alert.setHeaderText("Cannot delete " + product.getName());
            alert.showAndWait();
        }
    }
    // ----------------------------------------------------------------------------------

    // USER SEARCH FIELDS
    /** Returns a list of matching results when a user looks for a part in the search field.
     A prompt will inform the user if a part was not found.
     @return Returns the list of part(s), if found.
     */
    @FXML public void userSearchPartsField(){
        String userText = searchPartField.getText().trim();

        if (userText.matches("")){
            partTableView.setItems(Inventory.getAllParts());
            searchPartField.setPromptText("Search by part ID or Name");
            anchorPane.requestFocus();
        }
        else {
            try{
                int id = Integer.parseInt(userText);
                ObservableList<Part> part = FXCollections.observableArrayList();
                part.add(Inventory.lookupPart(id));
                if (part.get(0) == null) {
                    searchPartField.setText("");
                    searchPartLabel.setText("Part not found in inventory!");
                    anchorPane.requestFocus();
                }
                else{
                    searchPartLabel.setText("");
                    partTableView.setItems(part);
                }
                return;
            }catch (Exception ignored){}
            if (Inventory.lookupPart(userText).size() == 0) {
                searchPartField.setText("");
                searchPartLabel.setText("Part not found in inventory!");
                anchorPane.requestFocus();
            }
            else {
                searchPartLabel.setText("");
                partTableView.setItems(Inventory.lookupPart(userText));
            }
        }
    }

    /** Returns a list of matching results when a user looks for a product in the search field.
     A prompt will inform the user if a product was not found.
     @return Returns the list of product(s), if found.
     */
    @FXML public void userSearchProductsField(){
        String text = searchProductField.getText().trim();
        if (text.matches("")){
            searchProductField.setPromptText("Search by ID or Name");
            productTableView.setItems(Inventory.getAllProducts());
            anchorPane.requestFocus();
        }
        else{
            try{
                int id = Integer.parseInt(text);
                ObservableList<Product> product = FXCollections.observableArrayList();
                product.add(Inventory.lookupProduct(id));
                if (product.get(0) == null) {
                    searchProductField.setText("");
                    searchProductLabel.setText("Product not found in inventory!");
                    anchorPane.requestFocus();
                }
                else{
                    searchProductLabel.setText("");
                    productTableView.setItems(product);
                }
                return;
            }catch (Exception ignored){}
            if (Inventory.lookupProduct(text).size() == 0) {
                searchProductField.setText("");
                searchProductLabel.setText("Product not found in inventory!");
                anchorPane.requestFocus();
            }
            else {
                searchProductLabel.setText("");
                productTableView.setItems(Inventory.lookupProduct(text));
            }
        }
    }
    // ----------------------------------------------------------------------------------

    /** Terminates the program when the "EXIT" button is clicked. */
    @FXML public void exitBtnClicked() {
        System.exit(0);
    }

    /** Initialize method to set up our table views.
     @param url URL
     @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Part Table
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        // Product Table
        productTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}