package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is responsible on the activity of the checkout. In this activity the user
 * should see his order and can edit it or proceed to checkout.
 */
public class CheckoutActivity extends AppCompatActivity implements PizzaAdapter.ItemClickListener {

    // class private variables declaration:
    private RecyclerView recyclerView;

    /**
     * Sets up the checkout activity on the screen
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerView = findViewById(R.id.pizzas_descriptions);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specifies a PizzaAdapter
        updatePizzaAdapter();
        updateTotalPrice();
    }

    /**
     * Updates the total price to the checkout activity screen.
     */
    void updateTotalPrice() {
        TextView pizzasTotalPrice = findViewById(R.id.pizzas_total_price);
        SpannableString priceString = new SpannableString(PizzaFactory.getPizzaFactory().
                getTotalPizzasPriceDescription());
        priceString.setSpan(new RelativeSizeSpan(0.8f), priceString.length() - 3,
                priceString.length(), 0);
        pizzasTotalPrice.setText(priceString);
    }

    /**
     * When the user back pressed, if 're-order' was pressed go to previous screen (which is
     * order types activity), otherwise toasts 'cannot_back_toast' message to the screen.
     */
    @Override
    public void onBackPressed()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            // checks if we came to this activity by pressing 're-order'
            if (bundle.getInt("reorder_pressed") == 1)
            {
                super.onBackPressed();
            }
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.cannot_back_message,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * When this activity is resumed, updates the pizza adapter.
     */
    @Override
    protected void onResume() {
        super.onResume();
        updatePizzaAdapter();
    }

    /**
     * When confirm button is pressed, go to confirmed order activity.
     */
    public void clickConfirm(View view) {
        Intent intent = new Intent(CheckoutActivity.this,
                ConfirmedOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Adds new pizza when the circled red '+' button is pressed. If no more pizzas can be added,
     * toasts 'max_pizzas_order' message to the screen.
     */
    public void clickAddNewPizza(View view)
    {
        if (!PizzaFactory.getPizzaFactory().isMaxPizzas())
        {
            PizzaFactory.getPizzaFactory().createNewPizza();
            Intent intent = new Intent(CheckoutActivity.this,
                    PizzaDetailsActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.max_pizzas_order_message,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Deletes a pizza when the 'x' button is pressed.
     * @param position the pizza position in the list.
     */
    @Override
    public void onPizzaDeleteClick(View view, int position)
    {
        PizzaFactory.getPizzaFactory().getPizzas().remove(position);
        PizzaFactory.getPizzaFactory().setCurrentPizzaIndex(PizzaFactory.getPizzaFactory().
                getCurrentPizzaIndex() - 1);
        updatePizzaAdapter();
        updateTotalPrice();
        // if the first pizza is deleted, go to the pizza details activity, and marks that it
        // came from delete pizza as 'delete_pizza_pressed'.
        if (PizzaFactory.getPizzaFactory().getPizzasNumber() == 0)
        {
            PizzaFactory.getPizzaFactory().reset();
            Intent intent = new Intent(CheckoutActivity.this,
                    PizzaDetailsActivity.class);
            intent.putExtra("delete_pizza_pressed",1);
            startActivity(intent);
        }
    }

    /**
     * Increment the pizza quantity when the '+' button is pressed. If the number of pizza types
     * is the maximum than can be ordered, toasts a 'max_pizzas_order' message to the screen.
     * @param position the pizza position in the list.
     */
    @Override
    public void incPizzaQuantity(View view, int position) {
        if (PizzaFactory.getPizzaFactory().getPizzaByIndex(position).getQuantity() < 3)
        {
            PizzaFactory.getPizzaFactory().incPizzaQuantity(position);
            updatePizzaAdapter();
            updateTotalPrice();
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.max_pizzas_order_message,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Decrement the pizza quantity when the '-' button is pressed.  If the number of pizza types
     * is the minimum than can be ordered, toasts a 'min_pizzas_order' message to the screen.
     * @param position the pizza position in the list.
     */
    @Override
    public void decPizzaQuantity(View view, int position) {
        if (PizzaFactory.getPizzaFactory().getPizzaByIndex(position).getQuantity() > 1)
        {
            PizzaFactory.getPizzaFactory().decPizzaQuantity(position);
            updatePizzaAdapter();
            updateTotalPrice();
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.min_pizzas_order_message,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Updates the pizza adapter, and sets the OnClickListener functions.
     */
    void updatePizzaAdapter()
    {
        PizzaAdapter adapter = new PizzaAdapter(CheckoutActivity.this,
                PizzaFactory.getPizzaFactory().getPizzas());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new PizzaAdapter.ItemClickListener() {
            @Override
            public void onPizzaDeleteClick(View view, int position) {
                CheckoutActivity.this.onPizzaDeleteClick(view, position);
            }

            @Override
            public void incPizzaQuantity(View view, int position) {
                CheckoutActivity.this.incPizzaQuantity(view, position);
            }

            @Override
            public void decPizzaQuantity(View view, int position) {
                CheckoutActivity.this.decPizzaQuantity(view, position);
            }
        });
    }
}
