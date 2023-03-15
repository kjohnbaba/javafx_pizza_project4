package projectfour;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The NYPizzaController is a controller for the New York
 * pizza window. This class will create pizza orders of New York
 * style pizzas and add to the current order.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public class NYPizzaController implements Initializable{

    @FXML
    private ComboBox<String> NYpizzaType = new ComboBox<>();
    @FXML
    private ListView<String> NYpizzaToppings = new ListView<>();
    @FXML
    private ListView<String> NYselectedToppings;
    @FXML
    private ImageView NYpizzaImage;
    @FXML
    private Button NYaddTopping;
    @FXML
    private ComboBox<String> NYpizzaSize = new ComboBox<>();
    @FXML
    private TextField NYcrustText;
    @FXML
    private Button NYaddToOrder;
    @FXML
    private TextField NYpizzaPrice;
    @FXML
    private Button NYremoveTopping;


    /**
     * A method to initialize the New York Style Pizza window and set the image and list
     * the options on the window.
     * @param url A URL Object
     * @param resourceBundle A ResourceBundle object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [] items = {"Deluxe", "BBQ Chicken", "Meatzza",
                "Build Your Own"};
        NYpizzaType.getItems().addAll(items);
        String [] toppingList = {"SAUSAGE", "PEPPERONI", "GREENPEPPER", "ONION", "MUSHROOM",
                "BBQCHICKEN", "PROVOLONE", "CHEDDAR", "BEEF", "HAM", "PINEAPPLE", "ANCHOVIES", "EXTRACHEESE"};
        NYpizzaToppings.getItems().addAll(toppingList);
        String [] pizzaSizes = {"SMALL", "MEDIUM", "LARGE"};
        NYpizzaSize.getItems().addAll(pizzaSizes);
        Image image = new Image(getClass().getResource("/NYPizzaPie.jpeg")+"");
        NYpizzaImage.setImage(image);
    }

    /**
     * A method that will disable toppings and set the image according to
     * the user's selection of pizza type.
     * @param event An event that is the user clicking on the type of pizza.
     */
    @FXML
    public void NYsetDisplay(ActionEvent event){
        NYselectedToppings.getItems().clear();
        if(NYpizzaType.getSelectionModel().getSelectedItem().equals("Deluxe")){
            NYcrustText.setText("Brooklyn");
            Image image = new Image(getClass().getResource("/NYPizzaPie.jpeg")+"");
            NYpizzaImage.setImage(image);
            NYsetDeluxeToppings();
            NYshowPrice();
            NYpizzaToppings.setDisable(false);
            NYpizzaToppings.setFocusTraversable(false);
        }
        if(NYpizzaType.getSelectionModel().getSelectedItem().equals("Meatzza")){
            Image image = new Image(getClass().getResource("/meatzza.jpeg")+"");
            NYcrustText.setText("Hand Tossed");
            NYpizzaImage.setImage(image);
            NYsetMeatzzaToppings();
            NYshowPrice();
            NYpizzaToppings.setDisable(false);
            NYpizzaToppings.setFocusTraversable(false);
        }
        if(NYpizzaType.getSelectionModel().getSelectedItem().equals("BBQ Chicken")){
            Image image = new Image(getClass().getResource("/bbqchickenpizza.jpeg")+"");
            NYpizzaImage.setImage(image);
            NYcrustText.setText("Thin");
            NYsetBBQToppings();
            NYshowPrice();
            NYpizzaToppings.setDisable(false);
            NYpizzaToppings.setFocusTraversable(false);
        }
        if(NYpizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own")){
            Image image = new Image(getClass().getResource("/buildyourownpizza.jpeg")+"");
            NYpizzaImage.setImage(image);
            NYcrustText.setText("Hand Tossed");
        }
    }

    /**
     * A method that will show the price of a pizza and changes automatically when the
     * user clicks on a different size or type of pizza.
     */
    @FXML
    public void NYshowPrice(){
        if(NYpizzaSize.getSelectionModel().getSelectedItem() != null && NYpizzaType.getSelectionModel().getSelectedItem() != null) {
            PizzaFactory pizzaFactory = new NYPizza();
            if (NYpizzaType.getSelectionModel().getSelectedItem().equals("Deluxe")) {
                Pizza pizza = pizzaFactory.createDeluxe();
                pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                NYpizzaPrice.setText(String.valueOf(pizza.price()));
            } else if (NYpizzaType.getSelectionModel().getSelectedItem().equals("BBQ Chicken")) {
                Pizza pizza = pizzaFactory.createBBQChicken();
                pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                NYpizzaPrice.setText(String.valueOf(pizza.price()));
            } else if (NYpizzaType.getSelectionModel().getSelectedItem().equals("Meatzza")) {
                Pizza pizza = pizzaFactory.createMeatzza();
                pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                NYpizzaPrice.setText(String.valueOf(pizza.price()));
            } else if (NYpizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own")) {
                Pizza pizza = pizzaFactory.createBuildYourOwn();
                pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                NYBuildOwnHelper(pizza);
            }
        }
    }


    /**
     * A method that will add a topping when the user selects a topping and clicks
     * the add button.
     * @param event An event that is the user clicking the add button
     */
    @FXML
    public void NYplaceTopping(ActionEvent event){
        if(NYpizzaToppings.getSelectionModel().getSelectedItem() != null
                && (NYpizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own"))) {
            if(!NYselectedToppings.getItems().contains(NYpizzaToppings.getSelectionModel().getSelectedItem())) {
                NYselectedToppings.getItems().add(NYpizzaToppings.getSelectionModel().getSelectedItem());
            }
        }
        NYshowPrice();
    }

    /**
     * A method that will remove a topping when the user selects a topping and clicks
     * the remove button.
     * @param event An event that is the user clicking the remove button
     */
    @FXML
    public void NYremoveTheTopping(ActionEvent event){
        if(NYselectedToppings.getSelectionModel().getSelectedItem() != null
                && (NYpizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own"))) {
            NYselectedToppings.getItems().remove(NYselectedToppings.getSelectionModel().getSelectedItem());
        }
        NYshowPrice();
    }

    /**
     * A method that will add a pizza the user has created to the current order when the
     * user clicks the add to order button.
     * @param event An event that is the user clicking on the add to order button.
     */
    @FXML
    public void NYaddToOrderAction(ActionEvent event){
        if(NYpizzaType.getSelectionModel().getSelectedItem() != null && NYpizzaSize.getSelectionModel().getSelectedItem() != null){
            PizzaFactory pizzaFactory = new NYPizza();
                if(NYpizzaType.getSelectionModel().getSelectedItem().equals("Deluxe")){
                    Pizza pizza = pizzaFactory.createDeluxe();
                    pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                    NYpizzaPrice.setText(String.valueOf(pizza.price()));
                    ProjectFourController.getOrder().add(pizza);
                }
                else if(NYpizzaType.getSelectionModel().getSelectedItem().equals("BBQ Chicken")){
                    Pizza pizza = pizzaFactory.createBBQChicken();
                    pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                    NYpizzaPrice.setText(String.valueOf(pizza.price()));
                    ProjectFourController.getOrder().add(pizza);
                }
                else if(NYpizzaType.getSelectionModel().getSelectedItem().equals("Meatzza")){
                    Pizza pizza = pizzaFactory.createMeatzza();
                    pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                    NYpizzaPrice.setText(String.valueOf(pizza.price()));
                    ProjectFourController.getOrder().add(pizza);
                }
                else if(NYpizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own")){
                    Pizza pizza = pizzaFactory.createBuildYourOwn();
                    pizza.setSize(Size.valueOf(NYpizzaSize.getSelectionModel().getSelectedItem()));
                    ProjectFourController.getOrder().add(NYBuildOwnHelper(pizza));
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Order has been placed.");
                alert.show();
            }
        }

    /**
     * A helper method that will make a Build Your Own pizza type with the right
     * toppings and return the pizza.
     * @param pizza A pizza object that is to be modified.
     * @return A pizza object returned after toppings are added
     */
    public Pizza NYBuildOwnHelper(Pizza pizza){
        if(!(NYselectedToppings.getItems() == null)){
            ObservableList<String> temp = NYselectedToppings.getItems();
            for(int i = 0; i < temp.size(); i ++){
                pizza.add(Topping.valueOf(temp.get(i)));
            }
        }
        NYpizzaPrice.setText(String.valueOf(pizza.price()));
        return pizza;
    }

    /**
     * A helper method that will set the toppings for Deluxe.
     */
    public void NYsetDeluxeToppings() {
        NYselectedToppings.getItems().add("Sausage");
        NYselectedToppings.getItems().add("Pepperoni");
        NYselectedToppings.getItems().add("Green Pepper");
        NYselectedToppings.getItems().add("Onion");
        NYselectedToppings.getItems().add("Mushroom");
        NYaddTopping.setDisable(false);
        NYremoveTopping.setDisable(false);
    }

    /**
     * A helper method that will set the toppings for Meatzza.
     */
    public void NYsetMeatzzaToppings() {
        NYselectedToppings.getItems().add("Sausage");
        NYselectedToppings.getItems().add("Pepperoni");
        NYselectedToppings.getItems().add("Beef");
        NYselectedToppings.getItems().add("Ham");
        NYaddTopping.setDisable(false);
        NYremoveTopping.setDisable(false);
    }

    /**
     * A helper method that will set the toppings for BBQ Chicken.
     */
    public void NYsetBBQToppings() {
        NYselectedToppings.getItems().add("BBQ Chicken");
        NYselectedToppings.getItems().add("Green Pepper");
        NYselectedToppings.getItems().add("Provolone");
        NYselectedToppings.getItems().add("Cheddar");
        NYaddTopping.setDisable(false);
        NYremoveTopping.setDisable(false);
    }
}
