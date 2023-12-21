package model;

/** The InHouse class inherits the Part class. */
public class InHouse extends Part{

    //fields
    private int machineId;

    /** Constructor
     @param id The part ID.
     @param name The part name.
     @param price The part price.
     @param inventory The part inventory count.
     @param min The minimum amount of inventory.
     @param max The maximum amount of inventory.
     @param machineId The part machine ID.
     */
    public InHouse(int id, String name, double price, int inventory, int min, int max, int machineId) {
        super(id, name, price, inventory, min, max);
        this.machineId = machineId;
    }

    //methods
    /** Returns the part machine ID.
     @return Returns the part machine ID.
     */
    public String getMachineId() {
        return Integer.toString(this.machineId);
    }

    /** Sets the part machine ID.
     @param machineId Sets the part machine ID.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}

