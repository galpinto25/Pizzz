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
import android.widget.ImageView;
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
     * Sets up the RecycleView on the screen
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

        setTotalPrice();
    }

    void setTotalPrice() {
        TextView pizzasTotalPrice = findViewById(R.id.pizzas_total_price);
        SpannableString priceString = new SpannableString(PizzaFactory.getPizzaFactory().getTotalPizzasPriceDescription());
        priceString.setSpan(new RelativeSizeSpan(0.8f), priceString.length() - 3, priceString.length(), 0);
        pizzasTotalPrice.setText(priceString);
    }

    @Override
    public void onBackPressed()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            if (bundle.getInt("reorder_pressed") == 1)
            {
                super.onBackPressed();
            }
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.cannot_back_toast, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePizzaAdapter();
    }

    public void clickConfirm(View view) {
        Intent intent = new Intent(CheckoutActivity.this, ConfirmedOrderActivity.class);
        startActivity(intent);
    }

    //newFunctions:

    /**
     * add new Pizza
     * @param view
     * @throws Exception if there are maximum of pizzas (3)
     */
    public void clickAddNewPizza(View view)
    {
        if (!PizzaFactory.getPizzaFactory().isMaxPizzas())
        {
            PizzaFactory.getPizzaFactory().createNewPizza();
            Intent intent = new Intent(CheckoutActivity.this, PizzaDetailsActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.max_pizzas_order_message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onPizzaDeleteClick(View view, int position)
    {
        PizzaFactory.getPizzaFactory().getPizzas().remove(position);
        PizzaFactory.getPizzaFactory().setCurrentPizzaIndex(PizzaFactory.getPizzaFactory().getCurrentPizzaIndex() - 1);
        updatePizzaAdapter();
        setTotalPrice();
        if (PizzaFactory.getPizzaFactory().getPizzasNumber() == 0)
        {
            PizzaFactory.getPizzaFactory().reset();
            Intent intent = new Intent(CheckoutActivity.this, PizzaDetailsActivity.class);
            intent.putExtra("delete_pizza_pressed",1);
            startActivity(intent);
        }
    }

    @Override
    public void incPizzaQuantity(View view, int position) {
        if (PizzaFactory.getPizzaFactory().getPizzaByIndex(position).getQuantity() < 3)
        {
            PizzaFactory.getPizzaFactory().incPizzaQuantity(position);
            updatePizzaAdapter();
            setTotalPrice();
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.max_pizzas_order_message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void decPizzaQuantity(View view, int position) {
        if (PizzaFactory.getPizzaFactory().getPizzaByIndex(position).getQuantity() > 1)
        {
            PizzaFactory.getPizzaFactory().decPizzaQuantity(position);
            updatePizzaAdapter();
            setTotalPrice();
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.min_pizzas_order_message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    void updatePizzaAdapter()
    {
        PizzaAdapter adapter = new PizzaAdapter(CheckoutActivity.this, PizzaFactory.getPizzaFactory().getPizzas());
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
