package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class creates ObservableList(s) of parts and products.
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** Adds a part to the allParts list.
     @param newPart The part that we want added to the allParts list.
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /** Adds a part to the allProducts list.
     @param newProduct The part that we want added to the allProducts list.
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /** Loops through the allParts list for a specific ID.
     Returns null if ID is NOT found.
     @param partId The ID of the part we want to search for.
     @return The part, if found by the ID.
     */
    public static Part lookupPart(int partId){
        for (int i=0; i<allParts.size(); i++){
            if (allParts.get(i).getId() == partId)
                return allParts.get(i);
        }
        return null;
    }

    /** Loops through the allProducts list for a specific ID.
     Returns null if ID is NOT found.
     @param productId The product being searched for.
     @return The product, if found by the ID.
     */
    public static Product lookupProduct(int productId){
        for (int i=0; i<allProducts.size(); i++){
            if (allProducts.get(i).getId() == productId)
                return allProducts.get(i);
        }
        return null;
    }

    /** Loops through allParts to return a list of parts with the given name.
     @param partName The name of the part we are searching for.
     @return List of parts that contain the name we are searching for.
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        for (int i=0; i<getAllParts().size(); i++){
            if(getAllParts().get(i).getName().toLowerCase().contains(partName.toLowerCase())){
                parts.add(getAllParts().get(i));
            }
        }
        return parts;
    }

    /** Loops through allProducts list to return a new list of products with the given name.
     @param productName The name of the product we are searching for.
     @return List of products that contain the name we are searching for.
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (int i=0; i<getAllProducts().size(); i++){
            if(getAllProducts().get(i).getName().toLowerCase().contains(productName.toLowerCase())){
                products.add(getAllProducts().get(i));
            }
        }
        return products;
    }

    /** Replaces the part at the given index with the selectedPart.
     @param index The index of the part we are replacing.
     @param selectedPart The NEW part that will be replacing the old part.
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /** Replaces the product at the given index with the newProduct.
     @param index The index of the part we are replacing.
     @param newProduct The NEW part that will be replacing the old part.
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /** Deletes the selectedPart from the allParts list, thus removing it from the table view.
     @param selectedPart The selectedPart we want to delete from the allParts list.
     @return Returns true if the part was successfully deleted.
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /** Deletes the selectedProduct from the allProducts list, thus removing it from the table view.
     @param selectedProduct The selectedPart we want to delete from the allProducts list.
     @return Returns true if the product was successfully deleted.
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /** Returns all the parts in our allParts list.
     @return All the parts in our allParts list.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /** Returns all the products in our allProducts list.
     @return All the products in our allProducts list.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
