package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        TextView mPizzaDescription = findViewById(R.id.pizza_description);
        mPizzaDescription.setText(getPizzaDescription());
    }

    private String getPizzaDescription() {
        Pizza pizza = PizzaFactory.getPizzaFactory().getCurrentPizza();
//      if there is no pizza instance, choose default pizza
        if (pizza.getSize() == PizzaSize.NONE) {
            pizza.setSize(PizzaSize.SMALL);
            pizza.setPrice(Pizza.SMALL_PRICE);
        }
        String pizzaSizeText = pizza.getSize().toString().toLowerCase();
        String sizeDescription = "Pizza Size: " + pizzaSizeText.substring(0, 1).toUpperCase() + pizzaSizeText.substring(1);
        String extrasDescription = "\n\nExtras: " + pizza.getExtrasDescription();
        String totalPriceDescription = "\n\nTotal Price: " + (pizza.getTotalPrice()) + " NIS";
        return sizeDescription + extrasDescription + totalPriceDescription;
    }

    public void clickConfirm(View view) throws InterruptedException {
        Intent intent = new Intent(CheckoutActivity.this, ConfirmedOrderActivity.class);
//        TODO: change cofirm button to be seen as pushed after click on it
//        mTotalPrice.setBackgroundResource(R.drawable.ic_price_button_white);
//        mTotalPrice.setTextColor(Color.BLACK);
        startActivity(intent);
    }


    //newFunctions:

    /**
     * add new Pizza
     * @param view
     * @throws Exception if there are maximum of pizzas (3)
     */
    public void clickAddNew(View view) throws Exception
    {
        try
        {
            PizzaFactory.getPizzaFactory().createNewPizza();
            Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
            startActivity(intent);
        }
        catch (Exception e)
        {
            //TODO: print a message that cannot add more than 3 pizzas
        }
    }

    /**
     * edit pizza 1
     */
    public void clickEditSamePizza1(View view)
    {
        Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
        PizzaFactory.getPizzaFactory().setCurrentPizza(0);
        startActivity(intent);
    }

    /**
     * edit pizza 2
     */
    public void clickEditSamePizza2(View view)
    {
        Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
        PizzaFactory.getPizzaFactory().setCurrentPizza(1);
        startActivity(intent);
    }

    /**
     * edit pizza 3
     */
    public void clickEditSamePizza3(View view)
    {
        Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
        PizzaFactory.getPizzaFactory().setCurrentPizza(2);
        startActivity(intent);
    }

    /**
     * addSamePizza1
     */
    public void clickAddSamePizza1()
    {
            Pizza pizza = PizzaFactory.getPizzaFactory().getPizzaByIndex(0);
            if (pizza.getCount() >=3 )
            {
                int x = 5;
                //TODO: print a message that cannot add more than 3 pizzas
            }
            else
            {
                pizza.incCount();
                //TODO: update counter/details, print newCount
            }
    }

    /**
     * addSamePizza2
     */
    public void clickAddSamePizza2()
    {
        Pizza pizza = PizzaFactory.getPizzaFactory().getPizzaByIndex(1);
        if (pizza.getCount() >=3 )
        {
            int x = 5;
            //TODO: print a message that cannot add more than 3 pizzas
        }
        else
        {
            pizza.incCount();
            //TODO: update counter/details, print newCount
        }
    }

    /**
     * addSamePizza2
     */
    public void clickAddSamePizza3()
    {
        Pizza pizza = PizzaFactory.getPizzaFactory().getPizzaByIndex(2);
        if (pizza.getCount() >=3 )
        {
            int x = 5;
            //TODO: print a message that cannot add more than 3 pizzas
        }
        else
        {
            pizza.incCount();
            //TODO: update counter/details, print newCount
        }
    }


}
