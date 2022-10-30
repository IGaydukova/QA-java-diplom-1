package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
private IngredientType ingredientType;
private final String name;
public IngredientTypeTest(String name){
    this.name=name;
}
    @Parameterized.Parameters(name = "Тип Ингридиента: {0}")
    public static Object[][] getData() {
        return new Object[][]{
                {"SAUCE"},
                {"FILLING"}
        };
    }
    @Test
    public void ingredientTypeReturnCorrectValues() {
        boolean exists = true;
        try {
            ingredientType.valueOf(name);
        } catch (IllegalArgumentException e) {
            exists = false;
        }
        assertTrue(exists);
    }

}