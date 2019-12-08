package android.example.pizzz;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pizza {

    final static int ONION_PRICE = 4, MUSHROOMS_PRICE = 5, PEPPERONI_PRICE = 11, BASIL_PRICE = 3, OLIVES_PRICE = 4, EXTRA_CHEESE_PRICE = 8;
    final static int SMALL_PRICE = 25, MEDIUM_PRICE = 35, LARGE_PRICE = 45;

    // static variable single_instance of type Singleton
//    private static Pizza pizza = null;

    // variable of type String
    private PizzaSize size;
    private int sizePrice = 0;
    private int extrasPrice = 0;
    private Map<PizzaExtra, Pair<Boolean, Integer>> extras = new HashMap<>();
    private int quantity = 1;

    void setSize(PizzaSize size) {
        this.size = size;
    }

    void setSizePrice(int sizePrice) {
        this.sizePrice = sizePrice;
    }

    void setOnion(Boolean onion) {
        extras.put(PizzaExtra.ONION, new Pair<Boolean, Integer>(onion, ONION_PRICE));
    }

    void setMushrooms(Boolean mushrooms) {
        extras.put(PizzaExtra.MUSHROOMS, new Pair<Boolean, Integer>(mushrooms, MUSHROOMS_PRICE));
    }

    void setOlives(Boolean olives) {
        extras.put(PizzaExtra.OLIVES, new Pair<Boolean, Integer>(olives, OLIVES_PRICE));
    }

    void setPepperoni(Boolean pepperoni) {
        extras.put(PizzaExtra.PEPPERONI, new Pair<Boolean, Integer>(pepperoni, PEPPERONI_PRICE));
    }

    void setBasil(Boolean basil) {
        extras.put(PizzaExtra.BASIL, new Pair<Boolean, Integer>(basil, BASIL_PRICE));
    }

    void setExtraCheese(Boolean extraCheese) {
        extras.put(PizzaExtra.EXTRA_CHEESE, new Pair<Boolean, Integer>(extraCheese, EXTRA_CHEESE_PRICE));
    }

    void setExtrasPrice(int extras_price) {
        this.extrasPrice = extras_price;
    }

    PizzaSize getSize() {
        return size;
    }

    public int getSizePrice() {
        return sizePrice;
    }

    Boolean isOnion() {
        return extras.get(PizzaExtra.ONION).first;
    }

    Boolean isMushrooms() {
        return extras.get(PizzaExtra.MUSHROOMS).first;
    }

    Boolean isOlives() {
        return extras.get(PizzaExtra.OLIVES).first;
    }

    Boolean isPepperoni() {
        return extras.get(PizzaExtra.PEPPERONI).first;
    }

    Boolean isBasil() {
        return extras.get(PizzaExtra.BASIL).first;
    }

    Boolean isExtraCheese() {
        return extras.get(PizzaExtra.EXTRA_CHEESE).first;
    }

    int getExtrasPrice() {
        return extrasPrice;
    }

    int getTotalPrice() {
        return (extrasPrice + sizePrice) * quantity;
    }

    List<PizzaExtra> getExtras() {
        List<PizzaExtra> list = new ArrayList<PizzaExtra>();
        for (Map.Entry entry : extras.entrySet()) {
            Pair pair = (Pair) entry.getValue();
            if (pair.first.equals(true)) {
                list.add((PizzaExtra) entry.getKey());
            }
        }
        return list;
    }

    void setExtras(Map<PizzaExtra, Pair<Boolean, Integer>> newExtras)
    {
        extras = newExtras;
    }

    Map<PizzaExtra, Pair<Boolean, Integer>> getExtrasForCopy()
    {
        return extras;
    }

    void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }

    void incCount() {
        quantity++;
    }

    void decCount() {
        quantity--;
    }

    int getQuantity() {
        return quantity;
    }

    // private constructor restricted to this class itself
    Pizza() {
        size = PizzaSize.NONE;
        this.initializeExtrasHashMap();
    }

//    /**
//     * static method to create instance of Singleton class
//     *
//     * @return todo
//     */
//    public static Pizza getInstance() {
//        if (pizza == null) {
//            pizza = new Pizza();
//            pizza.initializeExtrasHashMap();
//        }
//        return pizza;
//    }

    void reset() {
        sizePrice = 0;
        extrasPrice = 0;
        size = PizzaSize.NONE;
        quantity = 1;
        this.initializeExtrasHashMap();
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
     */
    String getExtrasDescription() {
        if (!hasExtras()) {
            return "\t\tno extras";
        }
        StringBuilder description = new StringBuilder();
        for (Map.Entry entry : extras.entrySet()) {
            Pair pair = (Pair) entry.getValue();
            if (pair.first.equals(true)) {
                String extraOutput = entry.getKey().toString().toLowerCase().replace("_", " ");
                description.append(extraOutput.substring(0, 1).toUpperCase()).append(extraOutput.substring(1)).append("\n");
            }
        }
        return description.toString().trim();
    }

    String getSizeDescription() {
        String pizzaSizeText = this.getSize().toString().toLowerCase();
        return "\t\t" + pizzaSizeText.substring(0, 1).toUpperCase() + pizzaSizeText.substring(1);
    }

    String getQuantityDescription() {
        return Integer.toString(this.getQuantity());
    }

    String getTotalPriceDescription() {
        return this.getTotalPrice() + " NIS";
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
