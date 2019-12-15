package android.example.pizzz;

import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class which represents a Pizza object.
 */
class Pizza {

    // the prices of the extras which can be added to a pizza:
    final static int ONION_PRICE = 1, MUSHROOMS_PRICE = 4, PEPPERONI_PRICE = 5, BASIL_PRICE = 3,
            OLIVES_PRICE = 3, EXTRA_CHEESE_PRICE = 6;
    final static int SMALL_PRICE = 25, MEDIUM_PRICE = 35, LARGE_PRICE = 45;

    // class private variables declaration:
    private PizzaSize size;
    private int sizePrice = 0;
    private int extrasPrice = 0;
    private Map<PizzaExtra, Pair<Boolean, Integer>> extras = new HashMap<>();
    private int quantity = 1;

    // package-private constructor used by the pizza factory.
    Pizza() {
        size = PizzaSize.NONE;
        this.initializeExtrasHashMap();
    }

    /**
     * Sets the pizza size.
     * @param size a given pizza size.
     */
    void setSize(PizzaSize size) {
        this.size = size;
    }

    /**
     * Sets the price of the pizza size.
     * @param sizePrice a given price of pizza size.
     */
    void setSizePrice(int sizePrice) {
        this.sizePrice = sizePrice;
    }

    /**
     * Sets the price of the extras of the pizza.
     * @param extras_price a given price of the extras of the pizza.
     */
    void setExtrasPrice(int extras_price) {
        this.extrasPrice = extras_price;
    }

    /**
     * Sets the extra 'onion', to be added to the pizza or to be removed, according to
     * the given state.
     * @param onion a given state: 'true' of 'false'.
     */
    void setOnion(Boolean onion) {
        extras.put(PizzaExtra.ONION, new Pair<>(onion, ONION_PRICE));
    }

    /**
     * Sets the extra 'mushrooms', to be added to the pizza or to be removed, according to
     * the given state.
     * @param mushrooms a given state: 'true' of 'false'.
     */
    void setMushrooms(Boolean mushrooms) {
        extras.put(PizzaExtra.MUSHROOMS, new Pair<>(mushrooms, MUSHROOMS_PRICE));
    }

    /**
     * Sets the extra 'olives', to be added to the pizza or to be removed, according to
     * the given state.
     * @param olives a given state: 'true' of 'false'.
     */
    void setOlives(Boolean olives) {
        extras.put(PizzaExtra.OLIVES, new Pair<>(olives, OLIVES_PRICE));
    }

    /**
     * Sets the extra 'pepperoni', to be added to the pizza or to be removed, according to
     * the given state.
     * @param pepperoni a given state: 'true' of 'false'.
     */
    void setPepperoni(Boolean pepperoni) {
        extras.put(PizzaExtra.PEPPERONI, new Pair<>(pepperoni, PEPPERONI_PRICE));
    }

    /**
     * Sets the extra 'basil', to be added to the pizza or to be removed, according to
     * the given state.
     * @param basil a given state: 'true' of 'false'.
     */
    void setBasil(Boolean basil) {
        extras.put(PizzaExtra.BASIL, new Pair<>(basil, BASIL_PRICE));
    }

    /**
     * Sets the extra 'extra_cheese', to be added to the pizza or to be removed, according to
     * the given state.
     * @param extraCheese a given state: 'true' of 'false'.
     */
    void setExtraCheese(Boolean extraCheese) {
        extras.put(PizzaExtra.EXTRA_CHEESE, new Pair<>(extraCheese, EXTRA_CHEESE_PRICE));
    }

    /**
     * Sets the quantity of the pizza.
     * @param newQuantity a given quantity.
     */
    void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }

    /**
     * Gets the size of the pizza.
     * @return the pizza size.
     */
    PizzaSize getSize() {
        return size;
    }

    /**
     * Gets the price of the pizza size.
     * @return the price of the size of the pizza.
     */
    int getSizePrice() {
        return sizePrice;
    }

    /**
     * Gets the price of the pizza extras.
     * @return the price of the extras of the pizza.
     */
    int getExtrasPrice() {
        return extrasPrice;
    }

    /**
     * Gets the total price of the pizza.
     * @return the total price of the pizza.
     */
    int getTotalPrice() {
        return (extrasPrice + sizePrice) * quantity;
    }

    /**
     * Gets the state of the extra 'onion'.
     * @return 'true' if the pizza includes 'onion', 'false' otherwise.
     */
    Boolean isOnion() {
        return Objects.requireNonNull(extras.get(PizzaExtra.ONION)).first;
    }

    /**
     * Gets the state of the extra 'mushrooms'.
     * @return 'true' if the pizza includes 'mushrooms', 'false' otherwise.
     */
    Boolean isMushrooms() {
        return Objects.requireNonNull(extras.get(PizzaExtra.MUSHROOMS)).first;
    }

    /**
     * Gets the state of the extra 'olives'.
     * @return 'true' if the pizza includes 'olives', 'false' otherwise.
     */
    Boolean isOlives() {
        return Objects.requireNonNull(extras.get(PizzaExtra.OLIVES)).first;
    }

    /**
     * Gets the state of the extra 'pepperoni'.
     * @return 'true' if the pizza includes 'pepperoni', 'false' otherwise.
     */
    Boolean isPepperoni() {
        return Objects.requireNonNull(extras.get(PizzaExtra.PEPPERONI)).first;
    }

    /**
     * Gets the state of the extra 'basil'.
     * @return 'true' if the pizza includes 'basil', 'false' otherwise.
     */
    Boolean isBasil() {
        return Objects.requireNonNull(extras.get(PizzaExtra.BASIL)).first;
    }

    /**
     * Gets the state of the extra 'extra_cheese'.
     * @return 'true' if the pizza includes 'extra_cheese', 'false' otherwise.
     */
    Boolean isExtraCheese() {
        return Objects.requireNonNull(extras.get(PizzaExtra.EXTRA_CHEESE)).first;
    }

    /**
     * @return a list which represents the extras which are included in the pizza.
     */
    List<PizzaExtra> getExtras() {
        List<PizzaExtra> list = new ArrayList<>();
        for (Map.Entry entry : extras.entrySet()) {
            Pair pair = (Pair) entry.getValue();
            if (pair.first.equals(true)) {
                list.add((PizzaExtra) entry.getKey());
            }
        }
        return list;
    }

    /**
     * @return a copy of the extras map of the pizza.
     */
    Map<PizzaExtra, Pair<Boolean, Integer>> getExtrasForCopy()
    {
        return extras;
    }

    /**
     * Gets the quantity of the pizza.
     * @return the quantity of the pizza.
     */
    int getQuantity() {
        return quantity;
    }

    /**
     * Increment the quantity of the pizza.
     */
    void incCount() {
        quantity++;
    }

    /**
     * Decrement the quantity of the pizza.
     */
    void decCount() {
        quantity--;
    }

    /**
     * Resets the pizza attributes.
     */
    void reset() {
        sizePrice = 0;
        extrasPrice = 0;
        size = PizzaSize.NONE;
        quantity = 1;
        this.initializeExtrasHashMap();
    }

    /**
     * Initializes the extras map to default.
     */
    private void initializeExtrasHashMap() {
        extras.put(PizzaExtra.ONION, new Pair<>(false, ONION_PRICE));
        extras.put(PizzaExtra.MUSHROOMS, new Pair<>(false, MUSHROOMS_PRICE));
        extras.put(PizzaExtra.OLIVES, new Pair<>(false, OLIVES_PRICE));
        extras.put(PizzaExtra.PEPPERONI, new Pair<>(false, PEPPERONI_PRICE));
        extras.put(PizzaExtra.BASIL, new Pair<>(false, BASIL_PRICE));
        extras.put(PizzaExtra.EXTRA_CHEESE, new Pair<>(false, EXTRA_CHEESE_PRICE));
    }

    /**
     * @return the description of extras of the pizza, line by line.
     * If there are no extras, returns 'No extras'.
     */
    String getExtrasDescription() {
        if (!hasExtras()) {
            return "No extras";
        }
        StringBuilder description = new StringBuilder();
        // loops over the extras map
        for (Map.Entry entry : extras.entrySet()) {
            Pair pair = (Pair) entry.getValue();
            // check if the extra included
            if (pair.first.equals(true)) {
                // changes the first letter to capital and append to the description
                String extraOutput = entry.getKey().toString().toLowerCase().replace("_", " ");
                description.append(extraOutput.substring(0, 1).toUpperCase()).append(extraOutput.substring(1)).append("\n");
            }
        }
        return description.toString().trim();
    }

    /**
     * todo check if needed with Efrat
     * @return string represents the size of the pizza.
     */
    String getSizeDescription() {
        String pizzaSizeText = this.getSize().toString().toLowerCase();
        return pizzaSizeText.substring(0, 1).toUpperCase() + pizzaSizeText.substring(1);
    }

    /**
     * @return string represents the quantity of the pizza.
     */
    String getQuantityDescription() {
        return Integer.toString(this.getQuantity());
    }

    /**
     * @return string represents the total price of the pizza.
     */
    String getTotalPriceDescription() {
        return this.getTotalPrice() + " NIS";
    }

    /**
     * Checks if there are extras on the pizza.
     * @return true if there is an extra, else false.
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
