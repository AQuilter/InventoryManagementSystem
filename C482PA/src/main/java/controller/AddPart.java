package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import java.io.IOException;

/** The controller class for the AddPart.fmxl page. */
public class AddPart {
    @FXML private RadioButton inHouseBtn;
    @FXML private TextField nameField;
    @FXML private TextField inventoryField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private TextField typeField;
    @FXML private Label typeLabel;
    @FXML private Label exceptionLabel;

    /** Changes label text if the inHouse radio button is selected.
     @param actionEvent The button press.
     */
    @FXML public void inHouseBtnSelected(ActionEvent actionEvent){
        typeLabel.setText("Machine ID");
    }

    /** Changes label text if the Outsourced radio button is selected.
     @param actionEvent The button press.
     */
    @FXML public void outsourcedBtnSelected(ActionEvent actionEvent){
        typeLabel.setText("Company Name");
    }

    /** Returns the user to the MainScreen.fxml page if the "CANCEL" button is clicked.
     @param actionEvent The button press.
     */
    @FXML public void cancelBtnClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 950,450 );
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    /** When the "SAVE" button is clicked.
     Adds a part to the allParts list via text fields entered by the user.
     First it loops through the allParts list to assign an ID that is + 1 from the last.
     Checks for exceptions before adding the part to the allParts list.
     @param actionEvent The button press.
     */
    @FXML public void saveBtnClicked(ActionEvent actionEvent) throws IOException {
        int id = 0;
        for (int i = 0; i < Inventory.getAllParts().size(); i++){
            if (id <= Inventory.getAllParts().get(i).getId()){
                id = Inventory.getAllParts().get(i).getId() + 1;
            }
        } try {
            String name = nameField.getText();
            int inventory = Integer.parseInt(inventoryField.getText());
            double price = Double.parseDouble(priceField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());

            if (min > max) {
                throw new Exception("minGreaterThanMax");
            }
            if (inventory < min || inventory > max) {
                throw new Exception("inventoryError");
            }

            if (inHouseBtn.isSelected()) {
                int machineId = Integer.parseInt(typeField.getText());
                Inventory.addPart(new InHouse(id, name, price, inventory, min, max, machineId));
            }
            else{
                String companyName = typeField.getText();
                Inventory.addPart(new Outsourced(id, name, price, inventory, min, max, companyName));
            }
            cancelBtnClicked(actionEvent);
         } catch (Exception exception) {
            if (exception.getMessage().equals("minGreaterThanMax")) {
                exceptionLabel.setText("Min should be less than max.");
            }
            else if (exception.getMessage().equals("inventoryError")) {
                exceptionLabel.setText("Inventory should be between min and max.");
            }
            else {
                exceptionLabel.setText("Please enter appropriate data.");
            }
        }
    }
}
