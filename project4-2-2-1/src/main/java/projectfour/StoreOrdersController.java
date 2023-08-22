package projectfour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * The StoreOrderController class is responsible for storing
 * all orders placed by users and has features such as removing
 * an order or exporting all orders to a text file.
 *
 * @author Kerimcan Baba
 */
public class StoreOrdersController implements Initializable {
    @FXML
    private Button cancelOrder;
    @FXML
    private Button export;
    @FXML
    private ComboBox orderNumbers;
    @FXML
    private ListView<Pizza> storeOrderList;
    @FXML
    private TextField orderTotal;
    private static final double TAX = .06625;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    /**
     * A method that will initialize the window the default settings. It will
     * populate the ListView as well as the ComboBox with the respective customer
     * order numbers and info.
     * @param url A URL Object
     * @param resourceBundle A ResourceBundle object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ProjectFourController.getStoreOrder().getStoreOrdersList().size() != 0) {
            for (int x = 0; x < ProjectFourController.getStoreOrder().getStoreOrdersList().size(); x++) {
                orderNumbers.getItems().add(ProjectFourController.getStoreOrder().getStoreOrdersList().get(x).getUniqueNum());
            }
            storeOrderList.getItems().addAll(ProjectFourController.getStoreOrder().getStoreOrdersList().get(0).getOrdersList());
            orderNumbers.getSelectionModel().selectFirst();
            double total = ProjectFourController.getStoreOrder().getStoreOrdersList().get(0).getTotalPrice();
            total = Double.parseDouble(String.valueOf(df.format(total + (total * TAX))));
            orderTotal.setText(String.valueOf(total));
            cleanUpSelection();
        }
    }

    /**
     * A helper method that will remove an order if it is null.
     */
    public void cleanUpSelection(){
        for(int x = 0; x < storeOrderList.getItems().size(); x++){
            if(storeOrderList.getItems().get(x) == null){
                storeOrderList.getItems().remove(x);
            }
        }
    }

    /**
     *  A method that will display an order with the correct information
     *  when a user uses the ComboBox to select a certain order to see.
     * @param event An event that is the user clicking on the ComboBox
     */
    @FXML
    public void switchOrders(ActionEvent event){
        if(orderNumbers.getSelectionModel().getSelectedItem() != null){
            String unique = orderNumbers.getSelectionModel().getSelectedItem().toString();
            int uniqueNum = Integer.parseInt(unique);
            int temp = ProjectFourController.getStoreOrder().getOrder(uniqueNum);
            storeOrderList.getItems().clear();
            storeOrderList.getItems().addAll(ProjectFourController.getStoreOrder().getStoreOrdersList().get(temp).getOrdersList());
            double total = ProjectFourController.getStoreOrder().getStoreOrdersList().get(temp).getTotalPrice();
            total = Double.parseDouble(String.valueOf(df.format(total + (total * TAX))));
            orderTotal.setText(String.valueOf(total));
        }
    }

    /**
     * A method that will cancel a specific order based on the user's selection
     * and reset all text fields as well as remove the order from the store list.
     * @param event An event that is the user clicking on the cancel order button
     */
    @FXML
    public void cancelOrder(ActionEvent event){
        if(orderNumbers.getSelectionModel().getSelectedItem() != null){
                String unique = orderNumbers.getSelectionModel().getSelectedItem().toString();
                int uniqueNum = Integer.parseInt(unique);
                int temp = ProjectFourController.getStoreOrder().getOrder(uniqueNum);
                storeOrderList.getItems().clear();
                ProjectFourController.getStoreOrder().remove(ProjectFourController.getStoreOrder().getStoreOrdersList().get(temp));
                orderTotal.clear();
                orderNumbers.getItems().remove(orderNumbers.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * A method that will export all orders to a text file.
     * @param event An event that is the user clicking on the export store orders button
     */
    @FXML
    public void exportOrders(ActionEvent event){
        if(ProjectFourController.getStoreOrder().getStoreOrdersList().size() != 0){
            ProjectFourController.getStoreOrder().export();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Store Orders have been exported");
            alert.showAndWait();
        }
    }
}
