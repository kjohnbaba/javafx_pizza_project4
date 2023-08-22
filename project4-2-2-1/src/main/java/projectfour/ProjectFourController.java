package projectfour;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The ProjectFourController is a controller class for the entire
 * project. This class will initialize the first window and when
 * a user clicks on a certain image, it will initialize a new window.
 *
 * @author Kerimcan Baba
 */
public class ProjectFourController extends Application {
    private static Order order = new Order();
    private static StoreOrder storeOrder = new StoreOrder();

    /**
     * A method that gets the storeOrder object and returns it.
     * @return A StoreOrder object that is the storeOrder.
     */
    public static StoreOrder getStoreOrder(){
        return storeOrder;
    }

    /**
     * A method that gets the Order object and returns it.
     * @return An Order object that is the order.
     */
    public static Order getOrder(){
        return order;
    }

    /**
     * A method that will clear the order after a user has
     * placed an order.
     */
    public static void clearTheOrder(){
        order = new Order();
    }

    /**
     * A method that will initialize the Chicago Pizza window when a user
     * clicks on the Chicago pizza image.
     * @param event An event that is the user clicking on the image.
     * @throws IOException If the fxml is not found
     */
    @FXML
    public void startChicago(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ChicagoPizzaView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Chicago Style Pizza");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * A method that will initialize the New York Pizza window when a user
     * clicks on the New York pizza image.
     * @param event An event that is the user clicking on the image.
     * @throws IOException If the fxml is not found
     */
    @FXML
    public void startNewYork(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/NYPizzaView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("New York Style Pizza");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * A method that will initialize the Store Orders window when a user
     * clicks on the Store Orders image.
     * @param event An event that is the user clicking on the image.
     * @throws IOException If the fxml is not found
     */
    @FXML
    public void startStoreOrders(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/StoreOrdersView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Store Orders");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * A method that will initialize the Current Order window when a user
     * clicks on the Current Order image.
     * @param event An event that is the user clicking on the image.
     * @throws IOException If the fxml is not found
     */
    @FXML
    public void startCurrentOrder(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CurrentOrderView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Current Order");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * A method that will initialize the project and open a window, displaying
     * the project contents.
     * @param stage that will open a window to start the program.
     * @throws IOException If the fxml is not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setTitle("Project 4");
        stage.setScene(scene);
        stage.show();
    }
}
