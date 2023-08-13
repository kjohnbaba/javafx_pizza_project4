package projectfour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * The CurrentOrderController class is a class that holds the current
 * order of the user. This class handles all the pizzas in the current
 * order as well as removing certain pizzas, cancelling the order and
 * placing the order.
 *
 * @author Kerimcan Baba
 */
public class CurrentOrderController implements Initializable {

    @FXML
    private TextField orderNumber;
    @FXML
    private TextField subtotal;
    @FXML
    private TextField salesTax;
    @FXML
    private  TextField orderTotal;
    @FXML
    private Button clearOrder;
    @FXML
    private Button placeOrder;
    @FXML
    private Button removeOrder;
    @FXML
    private ListView<Pizza> OrderList;

    private static final double TAX = .06625;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * A method that will initialize the window the default settings. It will
     * populate the ListView of the current order.
     * @param url A URL Object
     * @param resourceBundle A ResourceBundle object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        salesTax.clear();
        subtotal.clear();
        orderNumber.clear();
        orderTotal.clear();
        OrderList.getItems().clear();
        if(ProjectFourController.getOrder().getOrdersList() != null){
            orderNumber.setText(String.valueOf(ProjectFourController.getOrder().getUniqueNum()));
            OrderList.getItems().addAll(ProjectFourController.getOrder().getOrdersList());
            calculatePrices();
        }
    }

    /**
     * A method that will remove a pizza from the current order based
     * on what the user has selected.
     * @param event An event that is the user clicking the remove a order button
     */
    @FXML
    public void RemoveAnOrder(ActionEvent event){
        if(ProjectFourController.getOrder().getOrdersList() != null &&
                OrderList.getSelectionModel().getSelectedItem() != null) {
            if(OrderList.getItems().size() == 1){
                OrderList.getItems().clear();
                calculatePrices();
                ProjectFourController.getOrder().remove(ProjectFourController.getOrder().getOrdersList().get(0));
            }
            OrderList.getItems().remove(OrderList.getSelectionModel().getSelectedItem());
            ProjectFourController.getOrder().remove(OrderList.getSelectionModel().getSelectedItem());
            calculatePrices();
        }
    }

    /**
     * A method that will place the entire order to store orders when the user
     * has clicked the place order button.
     * @param event An event that is the user clicking the place order button
     */
    @FXML
    public void placetheOrder(ActionEvent event){
        if(OrderList.getItems().size() != 0){
            ProjectFourController.getStoreOrder().add(ProjectFourController.getOrder());
            ProjectFourController.clearTheOrder();
            salesTax.clear();
            subtotal.clear();
            orderNumber.clear();
            orderTotal.clear();
            OrderList.getItems().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Order has been placed.");
            alert.show();
        }
    }

    /**
     * A helper method that will calculate the prices after removing a pizza.
     */
    public void calculatePrices(){
        double total = 0.0;
        double tax = 0.0;
        double allTogether = 0.0;
        for (int x = 0; x < ProjectFourController.getOrder().getOrdersList().size(); x++) {
            total = total + ProjectFourController.getOrder().getOrdersList().get(x).price();
        }
        tax = Double.parseDouble(String.valueOf(df.format(TAX * total)));
        salesTax.setText(String.valueOf(tax));
        total = Double.parseDouble(String.valueOf(df.format(total)));
        subtotal.setText(String.valueOf(total));
        allTogether = Double.parseDouble(String.valueOf(df.format(tax + total)));
        orderTotal.setText(String.valueOf(allTogether));
    }

    /**
     * A method that will cancel the entire order if the user has clicked
     * the cancel button.
     * @param event An event that is the user clicking the cancel order button.
     */
    @FXML
    public void ClearTheOrder(ActionEvent event){
        if(OrderList.getItems() != null){
            OrderList.getItems().clear();
            ProjectFourController.clearTheOrder();
        }
        orderTotal.setText("0.00");
        subtotal.setText("0.00");
        salesTax.setText("0.00");
    }


}
