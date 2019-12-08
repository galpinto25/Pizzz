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

public class CheckoutActivity extends AppCompatActivity implements PizzaAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private PizzaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView confirmButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerView = (RecyclerView) findViewById(R.id.pizzas_descriptions);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        adapter = new PizzaAdapter(this, PizzaFactory.getPizzaFactory().getPizzas());
        adapter.setClickListener(new PizzaAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CheckoutActivity.this.onItemClick(view, position);
            }
        });
        recyclerView.setAdapter(adapter);

        confirmButton = (ImageView) findViewById(R.id.confirm_button);
        setTotalPrice();
    }

    void setTotalPrice() {
        TextView pizzasTotalPrice = (TextView) findViewById(R.id.pizzas_total_price);
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
            if (bundle.getInt("ReOrder pressed") == 1)
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
    public void clickAddNew(View view)
    {
        if (!PizzaFactory.getPizzaFactory().isMaxPizzas()) {
            PizzaFactory.getPizzaFactory().createNewPizza();
            Intent intent = new Intent(CheckoutActivity.this, PizzaDetailsActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, R.string.max_pizzas_order_message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        PizzaFactory.getPizzaFactory().getPizzas().remove(position);
        PizzaFactory.getPizzaFactory().setCurrentPizzaIndex(PizzaFactory.getPizzaFactory().getCurrentPizzaIndex() - 1);
        adapter = new PizzaAdapter(CheckoutActivity.this, PizzaFactory.getPizzaFactory().getPizzas());
        recyclerView.setAdapter(adapter);
        setTotalPrice();
        if (PizzaFactory.getPizzaFactory().getPizzasNumber() == 0) {
            Toast toast = Toast.makeText(this, R.string.max_pizzas_order_message, Toast.LENGTH_SHORT);
            toast.show();
//            Intent intent = new Intent(CheckoutActivity.this, PizzaDetailsActivity.class);
//            startActivity(intent);
        }
    }
}
