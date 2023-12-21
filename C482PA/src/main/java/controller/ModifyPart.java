package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;

/** The controller class for the ModifyPart.fmxl page. */
public class ModifyPart {
    @FXML private RadioButton inHouseBtn;
    @FXML private RadioButton outsourcedBtn;
    @FXML private Label typeLabel;
    @FXML private TextField typeField;
    @FXML private AnchorPane anchorPane;
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField inventoryField;
    @FXML private TextField priceField;
    @FXML private TextField minField;
    @FXML private TextField maxField;
    @FXML private Label minMaxErrorLabel;
    boolean inHouseSelected = true;
    int partIndex;

    /** Sets the fields with the data of the part that was selected to modify.
     @param selectedPart The part that was selected to modify.
     */
    public void data(Part selectedPart){
        partIndex = Inventory.getAllParts().indexOf(selectedPart);
        if (selectedPart instanceof InHouse) {
            inHouseBtn.setSelected(true);
            outsourcedBtn.setSelected(false);
            typeLabel.setText("Machine ID");
            typeField.setText(((InHouse) selectedPart).getMachineId());
            typeField.setPromptText("Machine ID");
            inHouseSelected = true;
        }
        else if (selectedPart instanceof Outsourced){
            inHouseBtn.setSelected(false);
            outsourcedBtn.setSelected(true);
            typeField.setPromptText("Company Name");
            typeField.setText(((Outsourced) selectedPart).getCompanyName());
            typeLabel.setText("Company Name");
            inHouseSelected = false;
        }
        idField.setText(Integer.toString(selectedPart.getId()));
        nameField.setText(selectedPart.getName());
        inventoryField.setText(Integer.toString(selectedPart.getInventory()));
        priceField.setText(Double.toString(selectedPart.getPrice()));
        minField.setText(Integer.toString(selectedPart.getMin()));
        maxField.setText(Integer.toString(selectedPart.getMax()));
        anchorPane.requestFocus();
    }

    /** Changes label text if the InHouse button is selected.
     @param actionEvent The button press.
     */
    @FXML
    public void inHouseBtnSelected(ActionEvent actionEvent){
        if (!inHouseSelected){
            inHouseSelected = true;
            typeLabel.setText("Machine ID");
            typeField.setPromptText("Machine ID");
            typeField.setText("");
        }
    }

    /** Changes label text if the Outsourced button is selected.
     @param actionEvent The button press.
     */
    @FXML public void outsourcedBtnSelected(ActionEvent actionEvent){
        if (inHouseSelected){
            inHouseSelected = false;
            typeLabel.setText("Company Name");
            typeField.setPromptText("Company Name");
            typeField.setText("");
        }
    }

    /** Saves the field information of the part we modified.
     Checks for exceptions and will notify user if any are found.
     Occurs when the "SAVE" button is clicked.
     @param actionEvent The button press.
     */
    public void saveBtnClicked(ActionEvent actionEvent) throws IOException {
        try{
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText().strip();
            double price = Double.parseDouble(priceField.getText().strip());
            int inventory = Integer.parseInt(inventoryField.getText().strip());
            int max = Integer.parseInt(maxField.getText().strip());
            int min = Integer.parseInt(minField.getText().strip());

            if (min > max) {
                throw new Exception("minGreaterThanMax");
            }
            if (inventory < min || inventory > max) {
                throw new Exception("inventoryError");
            }

            if (inHouseSelected){
                int machineID = Integer.parseInt(typeField.getText());
                InHouse newPart = new InHouse(id, name, price, inventory, min, max, machineID);
                Inventory.updatePart(partIndex, newPart);
            }
            else {
                String companyName = typeField.getText();
                Outsourced newPart = new Outsourced(id, name, price, inventory, min, max, companyName);
                Inventory.updatePart(partIndex, newPart);
            }
            backToMainScreen(actionEvent);
        } catch (Exception exception){
            if (exception.getMessage().equals("minGreaterThanMax")) {
                minMaxErrorLabel.setText("Min should be less than max.");
            }
            else if (exception.getMessage().equals("inventoryError")) {
                minMaxErrorLabel.setText("Inventory should be between min and max.");
            }
            else {
                minMaxErrorLabel.setText("Please enter appropriate data.");
            }
        }
    }

    /** Returns to the MainScreen.fxml page.
     Occurs if the "CANCEL" is clicked.
     @param actionEvent The button press.
     */
    @FXML public void cancelBtnClicked(ActionEvent actionEvent) throws IOException {
        backToMainScreen(actionEvent);
    }

    /** Return to the MainScreen.fxml when user clicks "cancel" or "save".
     @param actionEvent The source of the original button press.
     */
    void backToMainScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/MainScreen.fxml"));
        Parent parent = loader.load();
        Scene mainScreen = new Scene(parent);
        Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        window.setScene(mainScreen);
    }
}
