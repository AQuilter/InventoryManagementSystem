package model;

/** Abstract class called Part.*/
public abstract class Part {

    // fields
    private int id;
    private String name;
    private double price;
    private int inventory;
    private int min;
    private int max;


    // constructor
    /** Part constructor.
     @param id The part ID.
     @param name The part name.
     @param price The part price.
     @param inventory The part inventory count.
     @param min The minimum amount of inventory.
     @param max The maximum amount of inventory.
     */
    public Part(int id, String name, double price, int inventory, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.min = min;
        this.max = max;
    }

    // --------- GETTERS ----------
    /** Returns the part ID.
     @return Returns the part ID.
     */
    public int getId() {
        return id;
    }

    /** Returns the part name.
     @return Returns the part name.
     */
    public String getName() {
        return name;
    }

    /** Returns the part price.
     @return Returns the part price.
     */
    public double getPrice() {
        return price;
    }

    /** Returns the part inventory.
     @return Returns the part inventory.
     */
    public int getInventory() {
        return inventory;
    }

    /** Returns the minimum amount of inventory.
     @return Returns the minimum amount of inventory.
     */
    public int getMin() {
        return min;
    }

    /** Returns the maximum amount of inventory.
     @return Returns the maximum amount of inventory.
     */
    public int getMax() {
        return max;
    }


    // ---------- SETTERS -----------
    /** Sets the part ID.
     @param id Sets the part id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Sets the part name.
     @param name Sets the part name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets the part price.
     @param price Sets the part price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Sets the inventory amount.
     @param inventory Sets the part inventory amount.
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    /** Sets the part minimum.
     @param min Sets the part minimum.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** Sets the part maximum.
     @param max Sets the part maximum.
     */
    public void setMax(int max) {
        this.max = max;
    }
}
