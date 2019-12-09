package android.example.pizzz;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class PizzaFactory
{
    private static PizzaFactory pizzaFactory = null;
    private ArrayList<Pizza> pizzas;
    private int currentPizza;
    final static private int maxPizzas = 3;
    private int indexOfPizzaThatEditedNow;

    private PizzaFactory()
    {
        currentPizza = 0;
        indexOfPizzaThatEditedNow = 0;
        pizzas = new ArrayList<>();
        Pizza pizza = new Pizza();
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
        Pizza pizza =  pizzas.get(currentPizza);
        indexOfPizzaThatEditedNow = currentPizza;
        return copyPizza(pizza);
    }

    void setNewPizza(Pizza pizzaToAdd)
    {
        //change the current pizza to be the new one with changes
        pizzas.set(indexOfPizzaThatEditedNow, pizzaToAdd);
        indexOfPizzaThatEditedNow = currentPizza;
    }

    void setCurrentPizzaIndex(int newCurrentPizza)
    {
        currentPizza = newCurrentPizza;
    }

    int getCurrentPizzaIndex()
    {
        return currentPizza;
    }

    void createNewPizza()
    {
        currentPizza++;
        indexOfPizzaThatEditedNow++;
        if ((currentPizza <= maxPizzas - 1) && (currentPizza >= 0))
        {
            Pizza pizza = new Pizza();
            pizzas.add(pizza);
        }
    }

    Pizza getPizzaByIndex(int index)
    {
        if ((index >= 0) && (index <= maxPizzas) && pizzas.get(index) != null)
        {
            Pizza pizza =  pizzas.get(index);
            indexOfPizzaThatEditedNow = index;
            return copyPizza(pizza);
        }
        return null;
    }

    boolean isMaxPizzas() {
        return (currentPizza >= maxPizzas - 1);
    }

    ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }

    private int getTotalPizzasPrice() {
        int totalPrice = 0;
        for (Pizza pizza: pizzas) {
            totalPrice += pizza.getTotalPrice();
        }
        return totalPrice;
    }

    int getPizzasNumber() {
        return currentPizza + 1;
    }

    String getTotalPizzasPriceDescription() {
        return this.getTotalPizzasPrice() + " NIS";
    }

    public void reset()
    {
        currentPizza = 0;
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
}
