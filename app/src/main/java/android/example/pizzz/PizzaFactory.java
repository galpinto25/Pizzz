package android.example.pizzz;

import java.util.ArrayList;

public class PizzaFactory
{
    private static PizzaFactory pizzaFactory = null;
    private ArrayList<Pizza> pizzas;
    private int currentPizza;
    final static private int maxPizzas = 3;

    private PizzaFactory()
    {
        currentPizza = 0;
        pizzas = new ArrayList<>();
        Pizza pizza = new Pizza();
        pizza.setTitle("Pizzz #" + (currentPizza + 1));
        pizzas.add(pizza);
    }

    static PizzaFactory getPizzaFactory()
    {
        if (pizzaFactory == null)
        {
            pizzaFactory = new PizzaFactory();
            return pizzaFactory;
        }
        return pizzaFactory;
    }

    Pizza getCurrentPizza()
    {
        return pizzas.get(currentPizza);
    }

    void setCurrentPizza(int newCurrentPizza)
    {
        currentPizza = newCurrentPizza;
    }

    void createNewPizza() throws Exception
    {
        currentPizza++;
        if ((currentPizza <= maxPizzas - 1) && (currentPizza >= 0))
        {
            Pizza pizza = new Pizza();
            pizza.setTitle("Pizzz #" + (currentPizza + 1));
            pizzas.add(pizza);
        }
        else
        {
            throw new Exception("PIZZA FULL");
        }
    }

    Pizza getPizzaByIndex(int index)
    {
        if ((index >= 0) && (index <= maxPizzas) && pizzas.get(index) != null)
        {
            return pizzas.get(index);
        }
        return null;
    }

    ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }
}
