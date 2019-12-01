package android.example.pizzz;

public class Pizza {

    final static public int ONION_PRICE = 4, MUSHROOMS_PRICE = 5, PEPPERONI_PRICE = 11, BASIL_PRICE = 3;
    final static public int SMALL_PRICE = 20, MEDIUM_PRICE = 40, LARGE_PRICE = 60;

    // static variable single_instance of type Singleton
    private static Pizza pizza = null;

    // variable of type String
    private PizzaSize size;
    private int price = 0;
    private boolean isOnion = false;
    private boolean isMushrooms = false;
    private boolean isOlives = false;
    private boolean isPepperoni = false;
    private boolean isBasil = false;
    private int extras_price = 0;
    private boolean isCola = false;


    public void setSize(PizzaSize size)
    {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOnion(boolean onion) {
        isOnion = onion;
    }

    public void setMushrooms(boolean mushrooms) {
        isMushrooms = mushrooms;
    }

    public void setOlives(boolean olives) {
        isOlives = olives;
    }

    public void setCola(boolean cola) {
        isCola = cola;
    }

    public void setPepperoni(boolean pepperoni) {
        isPepperoni = pepperoni;
    }

    public void setBasil(boolean basil) {
        isBasil = basil;
    }

    public void setExtras_price(int extras_price) {
        this.extras_price = extras_price;
    }

    public PizzaSize getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public boolean isOnion() {
        return isOnion;
    }

    public boolean isMushrooms() {
        return isMushrooms;
    }

    public boolean isOlives() {
        return isOlives;
    }

    public boolean isCola() {
        return isCola;
    }

    public boolean isPepperoni() {
        return isPepperoni;
    }

    public boolean isBasil() {
        return isBasil;
    }

    public int getExtras_price() {
        return extras_price;
    }

    // private constructor restricted to this class itself
    private Pizza()
    {
        size = PizzaSize.NONE;
    }

    // static method to create instance of Singleton class
    public static Pizza getInstance() {
        if (pizza == null)
            pizza = new Pizza();

        return pizza;
    }

}
