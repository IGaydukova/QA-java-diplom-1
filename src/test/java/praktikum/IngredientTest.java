package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class IngredientTest {
private Ingredient ingredient;
private IngredientType ingredientType;
private final String ingredientTypeValue;
private final String name;
private final float price;

    public IngredientTest(String ingredientTypeValue, String name, float price){
        this.ingredientTypeValue=ingredientTypeValue;
        this.name=name;
        this.price=price;
    }

    @Parameterized.Parameters(name = "")
    public static Object[][] getData() {
        return new Object[][]{
                {"FILLING", "Котлета",3.45f},
                {"SAUCE", "Сырный",5.40f},
        };
    }

    @Before
    public void setUp(){
        this.ingredient = new Ingredient(this.ingredientType.valueOf(ingredientTypeValue),name,price);
    }
    @Test
    public void getNameReturnCorrectValue(){
        String actualName = ingredient.getName();
        assertEquals("Name Ingredient is incorrect", actualName, name);
    }
    @Test
    public void getPriceReturnCorrectValue(){
        float actualPrice = ingredient.getPrice();
        assertEquals("Name Ingredient is incorrect", actualPrice, price, 0);
    }
    @Test
    public void getType(){
        assertEquals(this.ingredientType.valueOf(ingredientTypeValue), this.ingredient.getType());
    }


}