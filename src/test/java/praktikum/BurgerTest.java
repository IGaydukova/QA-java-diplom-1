package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.List;

import static org.junit.Assert.*;

public class BurgerTest {
    private Burger burger;
    private Database database;
   // @Spy
    private Bun bun;
    private Ingredient ingredient;
    String expectedNameIngridients;
    float expectedPriceIngridients;


    @Before
    public void setUp(){
        database = new Database();
    burger = new Burger();
    //взяли булочку
        bun = database.availableBuns().get(0); //new Bun("Ржаная", 3.45f);
        burger.setBuns(bun);
    //добавили первый ингридиент
        ingredient = database.availableIngredients().get(0);//new Ingredient(IngredientType.FILLING, expectedNameIngridients, expectedPriceIngridients);
        burger.addIngredient(ingredient);
    //добавили второй ингридиент
        ingredient = database.availableIngredients().get(1);//new Ingredient(IngredientType.FILLING, expectedNameIngridients, expectedPriceIngridients);
        burger.addIngredient(ingredient);
    }

    @Test
    public void setBunsReturnCorrectValue(){

      //  Mockito.when(bun.getName()).thenReturn("Ржаная");
      //  Mockito.when(bun.getPrice()).thenReturn(3.45f);

        String actualBunName = burger.bun.getName();
        float actualBunsPrice = burger.bun.getPrice();
        assertEquals("Name Bun in Burger is incorrect", actualBunName, bun.getName());
        assertEquals("Price Bun in Burger is incorrect", actualBunsPrice, bun.getPrice(),0);
    }

    @Test
    public void addIngredientAddValueToListCorrect(){
    int sizeList = burger.ingredients.size(); // зафиксировали текущий размер списка
    ingredient = database.availableIngredients().get(4);//new Ingredient(IngredientType.FILLING, expectedNameIngridients, expectedPriceIngridients);
    burger.addIngredient(ingredient);
    assertEquals("Size of List is incorrect", sizeList+1, burger.ingredients.size());
    assertEquals("Name of added ingridient is incorrect",burger.ingredients.get(sizeList).getName(),ingredient.getName());
    assertEquals("Price of added ingridient is incorrect",burger.ingredients.get(sizeList).getPrice(),ingredient.getPrice(),0);
    }

    @Test
    public void removeIngredientRemoveIngridientFromListCorrect(){
        int sizeList = burger.ingredients.size(); // зафиксировали текущий размер списка
        burger.removeIngredient(sizeList-1);
        assertEquals("The size of List is incorrect",sizeList-1,burger.ingredients.size());
        // подумать как проверить удалился ли нужный элемент, учитывая что возможны повторы
    }

    @Test
    public void moveIngredientMoveIngredientToLIst(){
        int sizeList = burger.ingredients.size(); // зафиксировали текущий размер списка
        String firstIngredient = burger.ingredients.get(0).getName();
        String lastIngredient = burger.ingredients.get(sizeList-1).getName();
        burger.moveIngredient(sizeList-1, 0);
        //проверили, что размер не изменился
        assertEquals("The Size of ListIngredient was changed!", sizeList, burger.ingredients.size());
        //проверили, что последний перешел на место первого
        assertEquals("First ingredient is incorrect", lastIngredient, burger.ingredients.get(0).getName());
        //проверили, что первый перешел на место последнего
        assertEquals("Lastt ingredient is incorrect", firstIngredient, burger.ingredients.get(sizeList-1).getName());
    }

    @Test
    public void getPriceCountPriceCorrect(){
        float expectedPrice = bun.getPrice()*2;
        for (int i = 0; i < burger.ingredients.size(); i ++) {
            expectedPrice += burger.ingredients.get(i).getPrice();
        }
        assertEquals("The Price of burger is incorrect",expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptReturnCorrectValue(){
        assertNotEquals(-1,burger.getReceipt().indexOf("(==== "+bun.getName()+" ====)"));
        assertNotEquals(-1,burger.getReceipt().indexOf("= "+burger.ingredients.get(0).getType().name().toLowerCase()+" " +burger.ingredients.get(0).getName() +" ="));
        assertNotEquals(-1,burger.getReceipt().indexOf("= "+burger.ingredients.get(1).getType().name().toLowerCase()+" " +burger.ingredients.get(0).getName() +" ="));
        assertNotEquals(-1,burger.getReceipt().indexOf(String.format("Price: %f%n", burger.getPrice())));
    }

}