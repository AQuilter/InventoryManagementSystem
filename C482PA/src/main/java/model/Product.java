package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** The Product class creates an ObservableList of associatedParts. */
public class Product {

    // FIELDS
    private int id;
    private String name;
    private double price;
    private int inventory;
    private int min;
    private int max;

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();;

    /** Product constructor.
     @param id The ID of the product.
     @param name The name of the product.
     @param price The price of the product.
     @param inventory The inventory of the product.
     @param min The minimum amount of inventory of the product.
     @param max The maximum amount of inventory of the product.
     */
    public Product(int id, String name, double price, int inventory, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.min = min;
        this.max = max;
    }

    // METHODS
    /** Deletes a selectedAssociatedPart from the associatedParts list.
     @param selectedAssociatedPart The
     @return Returns true if the part was successfully deleted, false if not.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        for (int i=0; i<getAllAssociatedParts().size(); i++){
            if (associatedParts.get(i) == selectedAssociatedPart){
                associatedParts.remove(i);
                return true;
            }
        }
        return false;
    }

    /** Adds a part to the associatedParts list.
     @param part The part we want to add to the associatedParts list.
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /** Returns all parts from the associatedParts list.
     @return Returns all parts from the associatedParts list.
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }



    // ----------- GETTERS -------------
    /**
     * @return Returns the product ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Returns the product name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return Returns the product inventory/stock.
     */
    public int getInventory() {
        return inventory;
    }

    /**
     * @return Returns the product minimum.
     */
    public int getMin() {
        return min;
    }

    /**
     * @return Returns the product maximum.
     */
    public int getMax() {
        return max;
    }


    //  ------------ SETTERS ------------
    /**
     * @param id Set the product ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name Set the product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price Set the product price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param inventory Set the product inventory.
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    /**
     * @param min Set the product minimum.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @param max Set the product maximum.
     */
    public void setMax(int max) {
        this.max = max;
    }
}
