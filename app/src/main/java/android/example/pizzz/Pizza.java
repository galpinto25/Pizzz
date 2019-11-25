package android.example.pizzz;

public class Pizza {

    //TODO: magic numbers of prices

    // static variable single_instance of type Singleton
    private static Pizza pizza = null;

    // variable of type String
    private String size;
    private int price = 40;
    private boolean isOnion = false;
    private boolean isMushrooms = false;
    private boolean isOlives = false;
    private boolean isCola = false;
    private boolean isPepperoni = false;
    private boolean isBasil = false;
    private int extras_price = 0;


    // private constructor restricted to this class itself
    private Pizza()
    {
        size = "none";
    }

    // static method to create instance of Singleton class
    public static Pizza getInstance() {
        if (pizza == null)
            pizza = new Pizza();

        return pizza;
    }

}
