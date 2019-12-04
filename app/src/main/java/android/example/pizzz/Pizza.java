package android.example.pizzz;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pizza {

    final static public int ONION_PRICE = 4, MUSHROOMS_PRICE = 5, PEPPERONI_PRICE = 11, BASIL_PRICE = 3, OLIVES_PRICE = 4, EXTRA_CHEESE_PRICE = 8;
    final static public int SMALL_PRICE = 25, MEDIUM_PRICE = 35, LARGE_PRICE = 45;

    // static variable single_instance of type Singleton
    private static Pizza pizza = null;

    // variable of type String
    private PizzaSize size;
    private int price = 0;
    private int extras_price = 0;
    private Map<PizzaExtra, Pair<Boolean, Integer>> extras = new HashMap<>();
    private int count = 1;

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOnion(Boolean onion) {
        extras.put(PizzaExtra.ONION, new Pair<Boolean, Integer>(onion, ONION_PRICE));
    }

    public void setMushrooms(Boolean mushrooms) {
        extras.put(PizzaExtra.MUSHROOMS, new Pair<Boolean, Integer>(mushrooms, MUSHROOMS_PRICE));
    }

    public void setOlives(Boolean olives) {
        extras.put(PizzaExtra.OLIVES, new Pair<Boolean, Integer>(olives, OLIVES_PRICE));
    }

    public void setPepperoni(Boolean pepperoni) {
        extras.put(PizzaExtra.PEPPERONI, new Pair<Boolean, Integer>(pepperoni, PEPPERONI_PRICE));
    }

    public void setBasil(Boolean basil) {
        extras.put(PizzaExtra.BASIL, new Pair<Boolean, Integer>(basil, BASIL_PRICE));
    }

    public void setExtraCheese(Boolean extraCheese) {
        extras.put(PizzaExtra.EXTRA_CHEESE, new Pair<Boolean, Integer>(extraCheese, EXTRA_CHEESE_PRICE));
    }

    public void setExtrasPrice(int extras_price) {
        this.extras_price = extras_price;
    }

    public PizzaSize getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public Boolean isOnion() {
        return extras.get(PizzaExtra.ONION).first;
    }

    public Boolean isMushrooms() {
        return extras.get(PizzaExtra.MUSHROOMS).first;
    }

    public Boolean isOlives() {
        return extras.get(PizzaExtra.OLIVES).first;
    }

    public Boolean isPepperoni() {
        return extras.get(PizzaExtra.PEPPERONI).first;
    }

    public Boolean isBasil() {
        return extras.get(PizzaExtra.BASIL).first;
    }

    public Boolean isExtraCheese() {
        return extras.get(PizzaExtra.EXTRA_CHEESE).first;
    }

    public int getExtrasPrice() {
        return extras_price;
    }

    public int getTotalPrice() {
        return (extras_price + price) * count;
    }

    public List<PizzaExtra> getExtras() {
        List<PizzaExtra> list = new ArrayList<PizzaExtra>();
        for (Map.Entry entry : extras.entrySet()) {
            Pair pair = (Pair) entry.getValue();
            if (pair.first.equals(true)) {
                list.add((PizzaExtra) entry.getKey());
            }
        }
        return list;
    }

    public void incCount() {
        count++;
    }

    public void decCount() {
        count--;
    }

    public int getCount() {
        return count;
    }

    // private constructor restricted to this class itself
    private Pizza() {
        size = PizzaSize.NONE;
    }

    /**
     * static method to create instance of Singleton class
     *
     * @return todo
     */
    public static Pizza getInstance() {
        if (pizza == null) {
            pizza = new Pizza();
            pizza.initializeExtrasHashMap();
        }
        return pizza;
    }

    void reset() {
        price = 0;
        extras_price = 0;
        size = PizzaSize.NONE;
        count = 1;
        pizza.initializeExtrasHashMap();
    }

    private void initializeExtrasHashMap() {
        extras.put(PizzaExtra.ONION, new Pair<Boolean, Integer>(false, ONION_PRICE));
        extras.put(PizzaExtra.MUSHROOMS, new Pair<Boolean, Integer>(false, MUSHROOMS_PRICE));
        extras.put(PizzaExtra.OLIVES, new Pair<Boolean, Integer>(false, OLIVES_PRICE));
        extras.put(PizzaExtra.PEPPERONI, new Pair<Boolean, Integer>(false, PEPPERONI_PRICE));
        extras.put(PizzaExtra.BASIL, new Pair<Boolean, Integer>(false, BASIL_PRICE));
        extras.put(PizzaExtra.EXTRA_CHEESE, new Pair<Boolean, Integer>(false, EXTRA_CHEESE_PRICE));
    }

    /**
     * todo
     *
     * @return
     */
    public String getExtrasDescription() {
        if (!hasExtras()) {
            return "no extras";
        }
        StringBuilder description = new StringBuilder();
        for (Map.Entry entry : extras.entrySet()) {
            Pair pair = (Pair) entry.getValue();
            if (pair.first.equals(true)) {
                String extraOutput = entry.getKey().toString().toLowerCase().replace("_", " ");
                description.append("\n - ").append(extraOutput.substring(0, 1).toUpperCase()).append(extraOutput.substring(1))
                        .append(": ").append(pair.second);
            }
        }
        return description.toString();
    }

    /**
     * check if there is extras on the pizza
     *
     * @return true if there is extra, otherwise false
     */
    private boolean hasExtras() {

        for (Map.Entry entry : extras.entrySet()) {
            Pair pair = (Pair) entry.getValue();
            if (pair.first.equals(true)) {
                return true;
            }
        }
        return false;
    }
}
