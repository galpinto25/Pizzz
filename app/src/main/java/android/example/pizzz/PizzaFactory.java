package android.example.pizzz;

public class PizzaFactory
{
    private static PizzaFactory pizzaFactory = null;
    private Pizza[] pizzas;
    private int currentPizza;
    final static public int maxPizzas = 3;

    private PizzaFactory()
    {
        currentPizza = 0;
        pizzas = new Pizza[maxPizzas];
        pizzas[0] = new Pizza();
    }

    public static PizzaFactory getPizzaFactory()
    {
        if (pizzaFactory == null)
        {
            pizzaFactory = new PizzaFactory();
            return pizzaFactory;
        }
        return pizzaFactory;
    }

    public Pizza getCurrentPizza()
    {
        return pizzas[currentPizza];
    }

    public void setCurrentPizza(int newCurrentPizza)
    {
        currentPizza = newCurrentPizza;
    }

    public void createNewPizza() throws Exception
    {
        currentPizza++;
        if ((currentPizza <= maxPizzas - 1) && (currentPizza >= 0))
        {
            Pizza pizza = new Pizza();
            pizzas[currentPizza] = pizza;
        }
        else
        {
            throw new Exception("PIZZA FULL");
        }
    }

    public Pizza getPizzaByIndex(int index)
    {
        if ((index >= 0) && (index <= maxPizzas) && pizzas[index] != null)
        {
            return pizzas[index];
        }
        return null;
    }

    public Pizza[] getPizzas()
    {
        return pizzas;
    }
}
