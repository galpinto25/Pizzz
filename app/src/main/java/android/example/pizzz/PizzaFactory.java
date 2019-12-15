package android.example.pizzz;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class PizzaFactory
{
    private static PizzaFactory pizzaFactorySingletonObject = null;
    private ArrayList<Pizza> pizzas;
    private int currentPizzaIndex;
    final static private int maxPizzasCanOrder = 3;
    private int pizzaThatEditedNowIndex;

    /**
     * private constructor because factory is singleton
     */
    private PizzaFactory()
    {
        currentPizzaIndex = 0;
        pizzaThatEditedNowIndex = 0;
        pizzas = new ArrayList<>();
        Pizza pizza = new Pizza();
        pizzas.add(pizza);
    }

    static PizzaFactory getPizzaFactory()
    {
        if (pizzaFactorySingletonObject == null)
        {
            pizzaFactorySingletonObject = new PizzaFactory();
            return pizzaFactorySingletonObject;
        }
        return pizzaFactorySingletonObject;
    }

    Pizza getCurrentPizza()
    {
        Pizza pizza =  pizzas.get(currentPizzaIndex);
        pizzaThatEditedNowIndex = currentPizzaIndex;
        return copyPizza(pizza);
    }

    void setNewPizza(Pizza pizzaToAdd)
    {
        pizzas.set(pizzaThatEditedNowIndex, pizzaToAdd);
        pizzaThatEditedNowIndex = currentPizzaIndex;
    }

    void setCurrentPizzaIndex(int newCurrentPizza)
    {
        currentPizzaIndex = newCurrentPizza;
    }

    int getCurrentPizzaIndex()
    {
        return currentPizzaIndex;
    }

    void createNewPizza()
    {
        currentPizzaIndex++;
        pizzaThatEditedNowIndex++;
        if ((currentPizzaIndex <= maxPizzasCanOrder - 1) && (currentPizzaIndex >= 0))
        {
            Pizza pizza = new Pizza();
            pizzas.add(pizza);
        }
    }

    Pizza getPizzaByIndex(int index)
    {
        if ((index >= 0) && (index <= maxPizzasCanOrder) && pizzas.get(index) != null)
        {
            Pizza pizza =  pizzas.get(index);
            pizzaThatEditedNowIndex = index;
            return copyPizza(pizza);
        }
        return null;
    }

    boolean isMaxPizzas()
    {
        return (currentPizzaIndex >= maxPizzasCanOrder - 1);
    }

    ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }

    private int getTotalPizzasPrice()
    {
        int totalPrice = 0;
        for (Pizza pizza: pizzas)
        {
            totalPrice += pizza.getTotalPrice();
        }
        return totalPrice;
    }

    int getPizzasNumber()
    {
        return currentPizzaIndex + 1;
    }

    String getTotalPizzasPriceDescription()
    {
        return this.getTotalPizzasPrice() + " NIS";
    }

    public void reset()
    {
        currentPizzaIndex = 0;
        pizzas = new ArrayList<>();
        Pizza pizza = new Pizza();
        pizzas.add(pizza);
    }

    private Pizza copyPizza(Pizza oldPizza)
    {
        Pizza newPizza = new Pizza();
        newPizza.setSize(oldPizza.getSize());
        newPizza.setSizePrice(oldPizza.getSizePrice());
        newPizza.setExtrasPrice(oldPizza.getExtrasPrice());
        newPizza.setQuantity(oldPizza.getQuantity());
        List<PizzaExtra> extras = oldPizza.getExtras();
        for (PizzaExtra pizzaExtra: extras)
        {
            if(newPizza.getExtrasForCopy().get(pizzaExtra).second != null)
            {
                int extraPrice = newPizza.getExtrasForCopy().get(pizzaExtra).second;
                newPizza.getExtrasForCopy().put(pizzaExtra,  new Pair<Boolean, Integer>(true, extraPrice));
            }
        }
        return newPizza;
    }

    void incPizzaQuantity(int position)
    {
        Pizza pizza = getPizzaByIndex(position);
        pizza.incCount();
        setNewPizza(pizza);
    }

    void decPizzaQuantity(int position)
    {
        Pizza pizza = getPizzaByIndex(position);
        pizza.decCount();
        setNewPizza(pizza);
    }
}
