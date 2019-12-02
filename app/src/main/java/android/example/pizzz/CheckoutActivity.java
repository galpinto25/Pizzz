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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        TextView mPizzaDescription = findViewById(R.id.pizza_description);
        mPizzaDescription.setText(getPizzaDescription());
    }

    private String getPizzaDescription() {
        Pizza pizza = Pizza.getInstance();
//      if there is no pizza instance, choose default pizza
        if (pizza.getSize() == PizzaSize.NONE)
        {
            pizza.setSize(PizzaSize.SMALL);
            pizza.setPrice(Pizza.SMALL_PRICE);
        }
        String pizzaSizeText = pizza.getSize().toString().toLowerCase();
        String sizeDescription = "Pizza Size: " + pizzaSizeText.substring(0, 1).toUpperCase() + pizzaSizeText.substring(1);
        String extrasDescription = "\n\nExtras: " + pizza.getExtrasDescription();
        String totalPriceDescription = "\n\nTotal Price: " + (pizza.getTotalPrice()) + " NIS";
        return sizeDescription + extrasDescription + totalPriceDescription;
    }

    public void clickConfirm(View view) throws InterruptedException
    {
        Intent intent = new Intent(CheckoutActivity.this, ConfirmedOrderActivity.class);
//        TODO: change cofirm button to be seen as pushed after click on it
//        mTotalPrice.setBackgroundResource(R.drawable.ic_price_button_white);
//        mTotalPrice.setTextColor(Color.BLACK);
        startActivity(intent);
    }

}
