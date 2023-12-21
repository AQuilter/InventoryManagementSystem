package model;

/** The Outsourced class inherits the Part class. */
public class Outsourced extends Part{

    //fields
    private String companyName;

    //constructor
    /** Outsourced constructor
     @param id The part id.
     @param name The part name.
     @param price The part price.
     @param inventory The part inventory count.
     @param min The minimum amount of inventory.
     @param max The maximum amount of inventory.
     @param companyName The company name of the part.
     */
    public Outsourced(int id, String name, double price, int inventory, int min, int max, String companyName) {
        super(id, name, price, inventory, min, max);
        this.companyName = companyName;
    }

    // methods
    /** Gets the company name of the part.
     @return Returns the company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /** Sets the company name of the part.
     @param companyName The company name of the part.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
