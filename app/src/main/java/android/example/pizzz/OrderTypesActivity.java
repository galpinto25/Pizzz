package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * This class is responsible on the activity of the order types menu. In this activity the user
 * should choose between new order and re-order.
 */
public class OrderTypesActivity extends AppCompatActivity
{
    // Image button declaration
    private ImageButton newOrder, reOrder;

    /**
     * Sets up the menu on the screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_types);
        newOrder = findViewById(R.id.new_order);
        reOrder = findViewById(R.id.re_order);
        Log.d("debug", "onCreate");
    }

    /**
     * Resets the existing pizza list, because getting back to this activity means that the user
     * wants start over the order.
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        reOrder.setImageResource(R.drawable.ic_re_order_white);
        newOrder.setImageResource(R.drawable.ic_neworder_white);
        if (PizzaFactory.getPizzaFactory().getPizzasNumber() != 0)
        {
            PizzaFactory.getPizzaFactory().reset();
        }
    }

    /**
     * Sets the pizza list to have the re-ordered pizza and launch the checkout activity.
     */
    public void click_re_order(View view)
    {
        // Feedback for a click - colors the re-order button in black
        reOrder.setImageResource(R.drawable.ic_re_order_black);
        newOrder.setImageResource(R.drawable.ic_neworder_white);

        // Creates the default re-ordered pizza
        Pizza pizza = PizzaFactory.getPizzaFactory().getCurrentPizza();
        pizza.setSize(PizzaSize.MEDIUM);
        pizza.setSizePrice(Pizza.MEDIUM_PRICE);
        pizza.setMushrooms(true);
        pizza.setBasil(true);
        pizza.setExtrasPrice(Pizza.MUSHROOMS_PRICE + Pizza.BASIL_PRICE);
        PizzaFactory.getPizzaFactory().setNewPizza(pizza);

        // Launch the checkout activity with the current order (re-ordered pizza)
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("reorder_pressed",1);
        startActivity(intent);
    }

    /**
     *
     */
    public void click_new(View view)
    {
        // Feedback for a click - colors the new-order button in black
        newOrder.setImageResource(R.drawable.ic_new_order_black);
        reOrder.setImageResource(R.drawable.ic_re_order_white);

        // Launch the pizza-details activity
        Intent intent = new Intent(this, PizzaDetailsActivity.class);
        startActivity(intent);
    }

}
