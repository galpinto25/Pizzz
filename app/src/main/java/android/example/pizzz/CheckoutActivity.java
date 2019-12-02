package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        TextView mPizzaDescription = findViewById(R.id.pizza_description);
        mPizzaDescription.setText(getPizzaDescription());
    }

    private String getPizzaDescription() {
        Pizza pizza = Pizza.getInstance();
        String pizzaSizeText = pizza.getSize().toString().toLowerCase();
        String sizeDescription = "Pizza Size: " + pizzaSizeText.substring(0, 1).toUpperCase() + pizzaSizeText.substring(1);
        String extrasDescription = "\n\nExtras: " + pizza.getExtrasDescription();
        String totalPriceDescription = "\n\nTotal Price: " + (pizza.getTotalPrice()) + " NIS";
        return sizeDescription + extrasDescription + totalPriceDescription;
    }

}
