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

public class CheckoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
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
        mAdapter = new PizzaAdapter(this, PizzaFactory.getPizzaFactory().getPizzas());
        recyclerView.setAdapter(mAdapter);

        confirmButton = (ImageView) findViewById(R.id.confirm_button);

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
        confirmButton.setImageResource(R.drawable.ic_ok_button_black);
    }

    //    private String getPizzaDescription() {
//        Pizza pizza = PizzaFactory.getPizzaFactory().getCurrentPizza();
////      if there is no pizza instance, choose default pizza
//        if (pizza.getSize() == PizzaSize.NONE) {
//            pizza.setSize(PizzaSize.SMALL);
//            pizza.setSizePrice(Pizza.SMALL_PRICE);
//        }
//        String pizzaSizeText = pizza.getSize().toString().toLowerCase();
//        String sizeDescription = "Pizza Size: " + pizzaSizeText.substring(0, 1).toUpperCase() + pizzaSizeText.substring(1);
//        String extrasDescription = "\n\nExtras: " + pizza.getExtrasDescription();
//        String totalPriceDescription = "\n\nTotal Price: " + (pizza.getTotalPrice()) + " NIS";
//        return sizeDescription + extrasDescription + totalPriceDescription;
//    }

    public void clickConfirm(View view) {
        confirmButton.setImageResource(R.drawable.ic_ok_button_white);
        Intent intent = new Intent(CheckoutActivity.this, ConfirmedOrderActivity.class);
        startActivity(intent);
        //        TODO: change confirm button to be seen as pushed after click on it
//        mTotalPrice.setBackgroundResource(R.drawable.ic_price_button_white);
//        mTotalPrice.setTextColor(Color.BLACK);
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
            Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, R.string.max_pizzas_order_message, Toast.LENGTH_SHORT);
            toast.show();
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
            if (pizza.getQuantity() >=3 )
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
        if (pizza.getQuantity() >=3 )
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
        if (pizza.getQuantity() >=3 )
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
