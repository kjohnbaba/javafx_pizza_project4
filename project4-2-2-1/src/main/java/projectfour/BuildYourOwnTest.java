package projectfour;
import static org.junit.Assert.*;
import org.junit.Test;

public class BuildYourOwnTest {

    // 3 test cases here:
    // small, med, large with under 7 toppings
    @Test
    public void testPriceUnder7Toppings(){
        // Test case 1, small pizza with less than 7 toppings. Expected result should be $8.99
        Pizza testCase1 = new BuildYourOwn(Crust.PAN);
        testCase1.setSize(Size.SMALL);
        testCase1.add(Topping.GREENPEPPER);
        testCase1.add(Topping.EXTRACHEESE);
        assertEquals(8.99, testCase1.price(), 0.00);

        // Test case 2, medium pizza with less than 7 toppings. Expected result should be $10.99
        Pizza testCase2 = new BuildYourOwn(Crust.PAN);
        testCase2.setSize(Size.MEDIUM);
        testCase2.add(Topping.GREENPEPPER);
        testCase2.add(Topping.EXTRACHEESE);
        assertEquals(10.99, testCase2.price(), 0.00);

        //Test case 3, large pizza with less than 7 toppings. Expected result should be $12.99
        Pizza testCase3 = new BuildYourOwn(Crust.PAN);
        testCase3.setSize(Size.LARGE);
        testCase3.add(Topping.GREENPEPPER);
        testCase3.add(Topping.EXTRACHEESE);
        assertEquals(12.99, testCase3.price(), 0.00);
    }

    // 3 test cases:
    // small, med, large with exactly 7 toppings
    @Test
    public void testPrice7Toppings(){
        // Test case 1, small pizza with 7 toppings. Expected result should be $8.99
        Pizza testCase1 = new BuildYourOwn(Crust.PAN);
        testCase1.setSize(Size.SMALL);
        testCase1.add(Topping.GREENPEPPER);
        testCase1.add(Topping.EXTRACHEESE);
        testCase1.add(Topping.ANCHOVIES);
        testCase1.add(Topping.BBQCHICKEN);
        testCase1.add(Topping.CHEDDAR);
        testCase1.add(Topping.PEPPERONI);
        testCase1.add(Topping.PROVOLONE);
        assertEquals(8.99, testCase1.price(), 0.00);

        // Test case 2, medium pizza with 7 toppings. Expected result should be $10.99
        Pizza testCase2 = new BuildYourOwn(Crust.PAN);
        testCase2.setSize(Size.MEDIUM);
        testCase2.add(Topping.GREENPEPPER);
        testCase2.add(Topping.EXTRACHEESE);
        testCase2.add(Topping.ANCHOVIES);
        testCase2.add(Topping.BBQCHICKEN);
        testCase2.add(Topping.CHEDDAR);
        testCase2.add(Topping.PEPPERONI);
        testCase2.add(Topping.PROVOLONE);
        assertEquals(10.99, testCase2.price(), 0.00);

        //Test case 3, large pizza with 7 toppings. Expected result should be $12.99
        Pizza testCase3 = new BuildYourOwn(Crust.PAN);
        testCase3.setSize(Size.LARGE);
        testCase3.add(Topping.GREENPEPPER);
        testCase3.add(Topping.EXTRACHEESE);
        testCase3.add(Topping.ANCHOVIES);
        testCase3.add(Topping.BBQCHICKEN);
        testCase3.add(Topping.CHEDDAR);
        testCase3.add(Topping.PEPPERONI);
        testCase3.add(Topping.PROVOLONE);
        assertEquals(12.99, testCase3.price(), 0.00);
    }

    // 3 test cases:
    // small, medium, large with over 7 toppings
    @Test
    public void testPriceOver7Toppings(){
        // Test case 1, small pizza with 8 toppings. Expected result should be $10.58
        Pizza testCase1 = new BuildYourOwn(Crust.PAN);
        testCase1.setSize(Size.SMALL);
        testCase1.add(Topping.GREENPEPPER);
        testCase1.add(Topping.EXTRACHEESE);
        testCase1.add(Topping.ANCHOVIES);
        testCase1.add(Topping.BBQCHICKEN);
        testCase1.add(Topping.CHEDDAR);
        testCase1.add(Topping.PEPPERONI);
        testCase1.add(Topping.PROVOLONE);
        testCase1.add(Topping.BEEF);
        assertEquals(10.58, testCase1.price(), 0.00);

        // Test case 2, medium pizza with 9 toppings. Expected result should be $14.17
        Pizza testCase2 = new BuildYourOwn(Crust.PAN);
        testCase2.setSize(Size.MEDIUM);
        testCase2.add(Topping.GREENPEPPER);
        testCase2.add(Topping.EXTRACHEESE);
        testCase2.add(Topping.ANCHOVIES);
        testCase2.add(Topping.BBQCHICKEN);
        testCase2.add(Topping.CHEDDAR);
        testCase2.add(Topping.PEPPERONI);
        testCase2.add(Topping.PROVOLONE);
        testCase2.add(Topping.HAM);
        testCase2.add(Topping.BEEF);
        assertEquals(14.17, testCase2.price(), 0.00);

        //Test case 3, large pizza with 10 toppings. Expected result should be $17.76
        Pizza testCase3 = new BuildYourOwn(Crust.PAN);
        testCase3.setSize(Size.LARGE);
        testCase3.add(Topping.GREENPEPPER);
        testCase3.add(Topping.EXTRACHEESE);
        testCase3.add(Topping.ANCHOVIES);
        testCase3.add(Topping.BBQCHICKEN);
        testCase3.add(Topping.CHEDDAR);
        testCase3.add(Topping.PEPPERONI);
        testCase3.add(Topping.PROVOLONE);
        testCase3.add(Topping.BEEF);
        testCase3.add(Topping.HAM);
        testCase3.add(Topping.MUSHROOM);
        assertEquals(17.76, testCase3.price(), 0.00);
    }
}
