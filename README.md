# Inventory Management System

This project is a JavaFX application designed to manage inventory through a graphical user interface (GUI). The application incorporates various functionalities for handling parts and products. </br>

## Project Structure

The project includes classes with data and logic that map to a UML class diagram. The code demonstrates concepts such as inheritance, abstract and concrete classes, instance and static variables, as well as instance and static methods.  </br>
## User Interface

The GUI consists of several forms, each corresponding to a specific functionality:

**Main form** </br>
* Parts pane: Add, modify, and delete parts; search parts by ID or name.
* Products pane: Add, modify, and delete products; search products by ID or name.
* Exit button: Closes the application.

**Add Part form** </br>
* Allows the user to add a new part with options for In-House or Outsourced types.
* Auto-generates a unique part ID.
* Validates and saves data, redirecting to the Main form upon completion.

**Modify Part form** </br>
* Allows the user to modify existing part data.
* Retains the part ID, updates data, and redirects to the Main form upon saving.

**Add Product form** </br>
* Auto-generates a unique product ID.
* Enables the user to associate parts with a product.
* Validates and saves data, redirecting to the Main form upon completion.

**Modify Product form** </br>
* Allows the user to modify existing product data, including associated parts.
* Validates and saves modifications, redirecting to the Main form.

## Documentation

The code is thoroughly documented using Javadoc comments. The documentation includes detailed descriptions of logical or runtime errors that were corrected during development, as well as suggestions for future enhancements to extend the application's functionality.

## Input Validation and Error Handling

The application incorporates input validation and logical error checks to ensure data integrity and prevent inappropriate user input. Descriptive error messages are displayed in the UI or in dialog boxes for various circumstances, including:

* Min should be less than Max; and Inv should be between those two values.
* The user should not delete a product that has a part associated with it.
* Confirmation for "Delete" and "Remove" actions.
* Handling inappropriate user data input without crashing the application.

This Inventory Management System aims to provide a robust and user-friendly interface for efficiently managing inventory in a JavaFX environment.
