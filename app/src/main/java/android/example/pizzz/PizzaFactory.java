package android.example.pizzz;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Creates, holds and manages the pizzas list.
 */
class PizzaFactory
{
    final static private int maxPizzaTypesLimit = 3;

    // class private variables declaration:
    private static PizzaFactory pizzaFactorySingletonObject = null;
    private ArrayList<Pizza> pizzas;
    private int currentPizzaIndex;
    private int lastEditedPizzaIndex;

    /**
     * private constructor, makes the factory a kind of singleton.
     */
    private PizzaFactory()
    {
        currentPizzaIndex = 0;
        lastEditedPizzaIndex = 0;
        pizzas = new ArrayList<>();
        Pizza pizza = new Pizza();
        pizzas.add(pizza);
    }

    /**
     * @return the instance of the factory. If needed, construct it and than returns it.
     */
    static PizzaFactory getPizzaFactory()
    {
        if (pizzaFactorySingletonObject == null)
        {
            pizzaFactorySingletonObject = new PizzaFactory();
            return pizzaFactorySingletonObject;
        }
        return pizzaFactorySingletonObject;
    }

    /**
     * @return the pizza who pointed by currentPizzaIndex.
     */
    Pizza getCurrentPizza()
    {
        Pizza pizza =  pizzas.get(currentPizzaIndex);
        lastEditedPizzaIndex = currentPizzaIndex;
        return copyPizza(pizza);
    }

    /**
     * Sets a new pizza to the list in the index specified by pizzaToAdd.
     * @param pizzaToAdd - index in list to set the pizza into it.
     */
    void setNewPizza(Pizza pizzaToAdd)
    {
        pizzas.set(lastEditedPizzaIndex, pizzaToAdd);
        lastEditedPizzaIndex = currentPizzaIndex;
    }

    /**
     * Sets currentPizzaIndex to the given pizza.
     * @param newCurrentPizza - pizza to set as current.
     */
    void setCurrentPizzaIndex(int newCurrentPizza)
    {
        currentPizzaIndex = newCurrentPizza;
    }

    /**
     * @return index of the current edited pizza.
     */
    int getCurrentPizzaIndex()
    {
        return currentPizzaIndex;
    }

    /**
     * Creates a new pizza, and handle the relevant pointers and indices with respect to the change.
     */
    void createNewPizza()
    {
        currentPizzaIndex++;
        lastEditedPizzaIndex++;
        // Check whether there are pizzas in list and the order doesn't surpass the limitation
        if ((currentPizzaIndex <= maxPizzaTypesLimit - 1) && (currentPizzaIndex >= 0))
        {
            Pizza pizza = new Pizza();
            pizzas.add(pizza);
        }
    }

    /**
     * Creates the default pizza, used when re-order is pressed in order-types activity.
     */
    void createDefaultPizza() {
        Pizza pizza = PizzaFactory.getPizzaFactory().getCurrentPizza();
        pizza.setSize(PizzaSize.MEDIUM);
        pizza.setSizePrice(Pizza.MEDIUM_PRICE);
        pizza.setMushrooms(true);
        pizza.setBasil(true);
        pizza.setExtrasPrice(Pizza.MUSHROOMS_PRICE + Pizza.BASIL_PRICE);
        PizzaFactory.getPizzaFactory().setNewPizza(pizza);
    }

    /**
     * @param index - index of the wanted pizza.
     * @return the pizza specified by the given index.
     */
    Pizza getPizzaByIndex(int index)
    {
        if ((index >= 0) && (index <= maxPizzaTypesLimit) && pizzas.get(index) != null)
        {
            Pizza pizza =  pizzas.get(index);
            lastEditedPizzaIndex = index;
            return copyPizza(pizza);
        }
        return null;
    }

    /**
     * @return true if the current pizza is the last one in the pizzas list, false otherwise.
     */
    boolean isMaxPizzas()
    {
        return (currentPizzaIndex >= maxPizzaTypesLimit - 1);
    }

    /**
     * @return the pizzas arrayList
     */
    ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }

    /**
     * @return total price of all the pizzas in the pizzas list.
     */
    private int getTotalPizzasPrice()
    {
        int totalPrice = 0;
        for (Pizza pizza: pizzas)
        {
            totalPrice += pizza.getTotalPrice();
        }
        return totalPrice;
    }

    /**
     * @return the number of pizzas in the pizza list.
     */
    int getPizzasNumber()
    {
        return currentPizzaIndex + 1;
    }

    /**
     * @return string that describes the total price of all the pizzas.
     */
    String getTotalPizzasPriceDescription()
    {
        return this.getTotalPizzasPrice() + " NIS";
    }

    /**
     * Handles the cleaning of the pizza list and the adding of a new pizza.
     */
    void reset()
    {
        currentPizzaIndex = 0;
        pizzas = new ArrayList<>();
        Pizza pizza = new Pizza();
        pizzas.add(pizza);
    }

    /**
     * @param oldPizza - pizza to copy.
     * @return a copy of the pizza specified by the given pizza.
     */
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
            if(Objects.requireNonNull(newPizza.getExtrasForCopy().get(pizzaExtra)).second != null)
            {
                int extraPrice = Objects.requireNonNull(newPizza.getExtrasForCopy().get(pizzaExtra)).second;
                newPizza.getExtrasForCopy().put(pizzaExtra,  new Pair<>(true, extraPrice));
            }
        }
        return newPizza;
    }

    /**
     * Increments the number of pizzas of the type specified by the given position.
     * @param position - index of the type of pizza to increment it's quantity.
     */
    void incPizzaQuantity(int position)
    {
        Pizza pizza = getPizzaByIndex(position);
        pizza.incCount();
        setNewPizza(pizza);
    }

    /**
     * Decrements the number of pizzas of the type specified by the given position.
     * @param position - index of the type of pizza to decrement it's quantity.
     */
    void decPizzaQuantity(int position)
    {
        Pizza pizza = getPizzaByIndex(position);
        pizza.decCount();
        setNewPizza(pizza);
    }
}
