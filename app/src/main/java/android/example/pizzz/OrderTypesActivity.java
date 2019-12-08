package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class OrderTypesActivity extends AppCompatActivity
{
    private ImageButton mNewOrder, mReOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_types);
        mNewOrder = findViewById(R.id.new_order);
        mReOrder = findViewById(R.id.re_order);
        Log.d("debug", "onCreate");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mReOrder.setImageResource(R.drawable.ic_re_order_white);
        mNewOrder.setImageResource(R.drawable.ic_neworder_white);
        if (PizzaFactory.getPizzaFactory().getPizzasNumber() != 0)
        {
            PizzaFactory.getPizzaFactory().reset();
        }
    }

    public void click_re_order(View view) throws Exception
    {
        mReOrder.setImageResource(R.drawable.ic_re_order_black);
        mNewOrder.setImageResource(R.drawable.ic_neworder_white);

        // todo maybe needs to move the price logic to the Pizza object
        // Sets the default re-ordered pizza
        Pizza pizza = PizzaFactory.getPizzaFactory().getCurrentPizza();
        pizza.setSize(PizzaSize.MEDIUM);
        pizza.setSizePrice(Pizza.MEDIUM_PRICE);
        pizza.setMushrooms(true);
        pizza.setBasil(true);
        pizza.setExtrasPrice(Pizza.MUSHROOMS_PRICE + Pizza.BASIL_PRICE);
        PizzaFactory.getPizzaFactory().setNewPizza(pizza);

        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("ReOrder pressed",1);
        startActivity(intent);
    }

    public void click_new(View view)
    {
        mNewOrder.setImageResource(R.drawable.ic_new_order_black);
        mReOrder.setImageResource(R.drawable.ic_re_order_white);

        Intent intent = new Intent(this, PizzaDetailsActivity.class);
        startActivity(intent);
    }

}
