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
 * The ChicagoPizzaController is a controller for the Chicago
 * pizza window. This class will create pizza orders of Chicago
 * style pizzas and add to the current order.
 *
 * @author Kerimcan Baba
 */
public class ChicagoPizzaController implements Initializable{


    @FXML
    private ComboBox<String> pizzaType = new ComboBox<>();
    @FXML
    private ListView<String> pizzaToppings = new ListView<>();
    @FXML
    private ListView<String> selectedToppings;
    @FXML
    private ImageView pizzaImage;
    @FXML
    private Button addTopping;
    @FXML
    private ComboBox<String> pizzaSize = new ComboBox<>();
    @FXML
    private TextField crustText;
    @FXML
    private Button addToOrder;
    @FXML
    private TextField pizzaPrice;
    @FXML
    private Button removeTopping;

    /**
     * A method to initialize the Chicago Style Pizza window and set the image and list
     * the options on the window.
     * @param url A URL Object
     * @param resourceBundle A ResourceBundle object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [] items = {"Deluxe", "BBQ Chicken", "Meatzza",
                "Build Your Own"};
        pizzaType.getItems().addAll(items);
        String [] toppingList = {"SAUSAGE", "PEPPERONI", "GREENPEPPER", "ONION", "MUSHROOM",
                "BBQCHICKEN", "PROVOLONE", "CHEDDAR", "BEEF", "HAM", "PINEAPPLE", "ANCHOVIES", "EXTRACHEESE"};
        pizzaToppings.getItems().addAll(toppingList);
        String [] pizzaSizes = {"SMALL", "MEDIUM", "LARGE"};
        pizzaSize.getItems().addAll(pizzaSizes);
        Image image = new Image(getClass().getResource("/chicagopizza.jpeg")+"");
        pizzaImage.setImage(image);
    }

    /**
     * A method that will disable toppings and set the image according to
     * the user's selection of pizza type.
     * @param event An event that is the user clicking on the type of pizza.
     */
    @FXML
    public void setDisplay(ActionEvent event){
        selectedToppings.getItems().clear();
        if(pizzaType.getSelectionModel().getSelectedItem().equals("Deluxe")){
            crustText.setText("Deep Dish");
            Image image = new Image(getClass().getResource("/deluxeChicago.jpeg")+"");
            pizzaImage.setImage(image);
            setDeluxeToppings();
            showPrice();
            pizzaToppings.setDisable(false);
            pizzaToppings.setFocusTraversable(false);
        }
        if(pizzaType.getSelectionModel().getSelectedItem().equals("Meatzza")){
            Image image = new Image(getClass().getResource("/deepdishmeatzza.jpeg")+"");
            crustText.setText("Stuffed");
            pizzaImage.setImage(image);
            setMeatzzaToppings();
            showPrice();
            pizzaToppings.setDisable(false);
            pizzaToppings.setFocusTraversable(false);
        }
        if(pizzaType.getSelectionModel().getSelectedItem().equals("BBQ Chicken")){
            Image image = new Image(getClass().getResource("/deepdishbbq.jpeg")+"");
            pizzaImage.setImage(image);
            crustText.setText("Pan");
            setBBQToppings();
            showPrice();
            pizzaToppings.setDisable(false);
            pizzaToppings.setFocusTraversable(false);
        }
        if(pizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own")){
            Image image = new Image(getClass().getResource("/buildurowndeepdish.jpeg")+"");
            pizzaImage.setImage(image);
            crustText.setText("Pan");
        }
    }

    /**
     * A method that will show the price of a pizza and changes automatically when the
     * user clicks on a different size or type of pizza.
     */
    @FXML
    public void showPrice(){
        if(pizzaSize.getSelectionModel().getSelectedItem() != null && pizzaType.getSelectionModel().getSelectedItem() != null) {
            PizzaFactory pizzaFactory = new ChicagoPizza();
            if (pizzaType.getSelectionModel().getSelectedItem().equals("Deluxe")) {
                Pizza pizza = pizzaFactory.createDeluxe();
                pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                pizzaPrice.setText(String.valueOf(pizza.price()));
            } else if (pizzaType.getSelectionModel().getSelectedItem().equals("BBQ Chicken")) {
                Pizza pizza = pizzaFactory.createBBQChicken();
                pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                pizzaPrice.setText(String.valueOf(pizza.price()));
            } else if (pizzaType.getSelectionModel().getSelectedItem().equals("Meatzza")) {
                Pizza pizza = pizzaFactory.createMeatzza();
                pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                pizzaPrice.setText(String.valueOf(pizza.price()));
            } else if (pizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own")) {
                Pizza pizza = pizzaFactory.createBuildYourOwn();
                pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                BuildOwnHelper(pizza);
            }
        }
    }

    /**
     * A helper method that will make a Build Your Own pizza type with the right
     * toppings and return the pizza.
     * @param pizza A pizza object that is to be modified.
     * @return A pizza object returned after toppings are added
     */
    public Pizza BuildOwnHelper(Pizza pizza){
        if(!(selectedToppings.getItems() == null)){
            ObservableList<String> temp = selectedToppings.getItems();
            for(int i = 0; i < temp.size(); i ++){
                pizza.add(Topping.valueOf(temp.get(i)));
            }
        }
        pizzaPrice.setText(String.valueOf(pizza.price()));
        return pizza;
    }

    /**
     * A method that will add a topping when the user selects a topping and clicks
     * the add button.
     * @param event An event that is the user clicking the add button
     */
    @FXML
    public void placeTopping(ActionEvent event){
        if(pizzaToppings.getSelectionModel().getSelectedItem() != null
                && (pizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own"))) {
            if(!selectedToppings.getItems().contains(pizzaToppings.getSelectionModel().getSelectedItem())) {
                selectedToppings.getItems().add(pizzaToppings.getSelectionModel().getSelectedItem());
            }
        }
        showPrice();
    }

    /**
     * A method that will remove a topping when the user selects a topping and clicks
     * the remove button.
     * @param event An event that is the user clicking the remove button
     */
    @FXML
    public void removeTheTopping(ActionEvent event){
        if(selectedToppings.getSelectionModel().getSelectedItem() != null
                && (pizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own"))) {
            selectedToppings.getItems().remove(selectedToppings.getSelectionModel().getSelectedItem());
        }
        showPrice();
    }


    /**
     * A method that will add a pizza the user has created to the current order when the
     * user clicks the add to order button.
     * @param event An event that is the user clicking on the add to order button.
     */
    @FXML
    public void addToOrderAction(ActionEvent event){
        if(pizzaType.getSelectionModel().getSelectedItem() != null && pizzaSize.getSelectionModel().getSelectedItem() != null){
            PizzaFactory pizzaFactory = new ChicagoPizza();
                if(pizzaType.getSelectionModel().getSelectedItem().equals("Deluxe")){
                    Pizza pizza = pizzaFactory.createDeluxe();
                    pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                    pizzaPrice.setText(String.valueOf(pizza.price()));
                    ProjectFourController.getOrder().add(pizza);

                }
                else if(pizzaType.getSelectionModel().getSelectedItem().equals("BBQ Chicken")){
                    Pizza pizza = pizzaFactory.createBBQChicken();
                    pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                    pizzaPrice.setText(String.valueOf(pizza.price()));
                    ProjectFourController.getOrder().add(pizza);
                }
                else if(pizzaType.getSelectionModel().getSelectedItem().equals("Meatzza")){
                    Pizza pizza = pizzaFactory.createMeatzza();
                    pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                    pizzaPrice.setText(String.valueOf(pizza.price()));
                    ProjectFourController.getOrder().add(pizza);
                }
                else if(pizzaType.getSelectionModel().getSelectedItem().equals("Build Your Own")){
                    Pizza pizza = pizzaFactory.createBuildYourOwn();
                    pizza.setSize(Size.valueOf(pizzaSize.getSelectionModel().getSelectedItem()));
                    ProjectFourController.getOrder().add(BuildOwnHelper(pizza));
                }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Order has been placed.");
            alert.show();
            }
        }

    /**
     * A helper method that will set the toppings for Deluxe.
     */
    public void setDeluxeToppings() {
        selectedToppings.getItems().add("Sausage");
        selectedToppings.getItems().add("Pepperoni");
        selectedToppings.getItems().add("Green Pepper");
        selectedToppings.getItems().add("Onion");
        selectedToppings.getItems().add("Mushroom");
        addTopping.setDisable(false);
        removeTopping.setDisable(false);
    }

    /**
     * A helper method that will set the toppings for Meatzza.
     */
    public void setMeatzzaToppings() {
        selectedToppings.getItems().add("Sausage");
        selectedToppings.getItems().add("Pepperoni");
        selectedToppings.getItems().add("Beef");
        selectedToppings.getItems().add("Ham");
        addTopping.setDisable(false);
        removeTopping.setDisable(false);
    }

    /**
     * A helper method that will set the toppings for BBQ Chicken.
     */
    public void setBBQToppings() {
        selectedToppings.getItems().add("BBQ Chicken");
        selectedToppings.getItems().add("Green Pepper");
        selectedToppings.getItems().add("Provolone");
        selectedToppings.getItems().add("Cheddar");
        addTopping.setDisable(false);
        removeTopping.setDisable(false);
    }
}
