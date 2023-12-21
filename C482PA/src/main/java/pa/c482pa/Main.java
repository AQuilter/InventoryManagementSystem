package pa.c482pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * This class creates an app that manages inventory of various parts and products.
 * @author Allyson Quilter
 * <p>
 * <b>RUNTIME_ERROR:</b> One issue I ran into in the beginning that took me a while to figure out was when
 * I was setting up my table views for the MainScreen. I was getting this error:
 * """ module javafx.base cannot access class model.Part (in module pa.c482pa) because module pa.c482pa does not open model to javafx.base """
 * I havent worked with a module-info.java file before, so it took me a while to realize I was missing information
 * that it needed in order to build the data within the tables. Eventually I noticed I was missing
 * "exports model;" and "opens model to javafx.base;" in the file. Once I added that code it worked fine.
 *</p>
 *
 * <b>FUTURE_ENHANCEMENT:</b> I think I would like to add a seperate class to handle
 * all the exceptions and alerts/warnings. I was rewritting the same blocks of code to handle these, so it would be
 * better to give them their own classes that I could implement later. This would help organize the code a bit better
 * would have saved the hassle of writting them out every time I needed to use them.
 */
public class Main extends Application {

    /** Start method. Loads our first screen in the program, MainScreen.fxml. */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene(root, 950,450));
        primaryStage.show();
    }

    /** The main method. It creates a few part and product objects that we can view/manipulate. */
    public static void main(String[] args) {

        InHouse paddleTire = new InHouse(1, "Paddle Tire", 60.00, 10,1,15, 1);
        InHouse brakes = new InHouse(2, "Brakes", 75.00, 3,1,5, 2);
        InHouse seat = new InHouse(3, "Seat", 45.00, 3,1,5, 2);

        Outsourced handlebars = new Outsourced(4, "Handlebars", 82.00, 15,1,15, "Pro Taper");
        Outsourced exhaust = new Outsourced(5, "Exhaust System", 180.50, 4,1,6, "FMF");
        Outsourced graphicsKit = new Outsourced(6, "Graphics Kit", 55.0, 16,1,18, "Honda");

        Product dirtBike = new Product(1001, "Dirt Bike", 7000.00,3, 1,4);
        Product fourWheeler = new Product(1002, "4-Wheeler", 5500.00, 2, 1, 4);
        Product goKart = new Product(1003, "Go-Kart", 499.00, 2, 1, 4);

        Inventory.addPart(paddleTire);
        Inventory.addPart(brakes);
        Inventory.addPart(seat);
        Inventory.addPart(handlebars);
        Inventory.addPart(exhaust);
        Inventory.addPart(graphicsKit);

        dirtBike.addAssociatedPart(paddleTire);
        dirtBike.addAssociatedPart(graphicsKit);
        dirtBike.addAssociatedPart(handlebars);

        fourWheeler.addAssociatedPart(brakes);
        fourWheeler.addAssociatedPart(exhaust);
        fourWheeler.addAssociatedPart(seat);

        goKart.addAssociatedPart(graphicsKit);

        Inventory.addProduct(dirtBike);
        Inventory.addProduct(fourWheeler);
        Inventory.addProduct(goKart);

        launch();
    }
}