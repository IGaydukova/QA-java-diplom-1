package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class BunTest {
    private Bun bun;
    private String name;
    private float price;
    public BunTest(String name, float price){
        this.name=name;
        this.price=price;
    }
    @Parameterized.Parameters(name = "Тип булочки: {0}/ Цена {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Пшеничная", 3.45f},
                {"Ржаная", 5.40f}
        };
    }
    @Before
    public void setUp(){
        this.bun = new Bun(name, price);
    }
    @Test
    public void getNameReturnCorrectValue(){
       String actualName = bun.getName();
       assertEquals("Name Bun is incorrect", actualName, name);
    }

    @Test
    public void getPriceReturnCorrectValue(){
        float actualPrice = bun.getPrice();
        assertEquals("Name Bun is incorrect", actualPrice, price,0);
    }
}