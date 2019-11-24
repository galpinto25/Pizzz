package android.example.pizzz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private int mPrice = 0;
    private int mExtraPrice = 0;
    private PizzaSize mSize = PizzaSize.SMALL;
    private Button mTotalPrice;
    private ImageButton mSbutton;
    private ImageButton mMbutton;
    private ImageButton mLbutton;
    private ImageButton mOlivesbutton;
    private ImageButton mMushroomsbutton;
    private ImageButton mColabutton;
    private ImageButton mPepperoniButton;
    private ImageView mMushroomsImage;
    private ImageView mOnionImage;
    private ImageView mPepperoniImage;
    private boolean isOlives = false;
    private boolean isMushrooms = false;
    private boolean isCola = false;
    private boolean isPepperoni = false;
    private static final int smallPrice = 20;
    private static final int mediumPrice = 40;
    private static final int largePrice = 60;
    private static final String currency = " NIS";

    protected void changePizzaSize(PizzaSize pizzaSize) {
        if (pizzaSize == PizzaSize.SMALL) {
            mSbutton.setImageResource(R.drawable.ic_small);
            mPrice = smallPrice;
        } else {
            mSbutton.setImageResource(R.drawable.ic_small_w);
        }
        if (pizzaSize == PizzaSize.MEDIUM) {
            mMbutton.setImageResource(R.drawable.ic_medium);
            mPrice = mediumPrice;
        } else {
            mMbutton.setImageResource(R.drawable.ic_medium_w);
        }
        if (pizzaSize == PizzaSize.LARGE) {
            mLbutton.setImageResource(R.drawable.ic_large);
            mPrice = largePrice;
        } else {
            mLbutton.setImageResource(R.drawable.ic_large_w);
        }
        mSize = pizzaSize;
        updatePriceTag();
    }

    private void addExtras(PizzaExtra pizzaExtra) {
        if (pizzaExtra == PizzaExtra.ONION) {
            isOlives = true;
            mOlivesbutton.setImageResource(R.drawable.ic_onion_g);
            mOnionImage.setVisibility(View.VISIBLE);
            mExtraPrice += 4;
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS) {
            isMushrooms = true;
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms_g);
            mMushroomsImage.setVisibility(View.VISIBLE);
            mExtraPrice += 5;
        }
        else if (pizzaExtra == PizzaExtra.COLA) {
            isCola = true;
            mColabutton.setImageResource(R.drawable.ic_cola_b);
            mExtraPrice += 8;
        }
        else if (pizzaExtra == PizzaExtra.PEPPERONI) {
            isPepperoni = true;
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_b);
            mPepperoniImage.setVisibility(View.VISIBLE);
            mExtraPrice += 11;
        }
        updatePriceTag();
    }

    private void updatePriceTag(){
        SpannableString priceString = new SpannableString(mPrice + mExtraPrice + currency);
        int priceLen = Integer.toString(mPrice + mExtraPrice).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0); // set size
        mTotalPrice.setText(priceString);
    }

    private void removeExtras(PizzaExtra pizzaExtra) {
        if (pizzaExtra == PizzaExtra.ONION) {
            isOlives = false;
            mOlivesbutton.setImageResource(R.drawable.ic_onion);
            mOnionImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 4;
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS) {
            isMushrooms = false;
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms);
            mMushroomsImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 5;
        }
        else if (pizzaExtra == PizzaExtra.COLA) {
            isCola = false;
            mColabutton.setImageResource(R.drawable.ic_cola);
            mExtraPrice -= 8;
        }
        else if (pizzaExtra == PizzaExtra.PEPPERONI) {
            isPepperoni = false;
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_w);
            mPepperoniImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 11;
        }
        updatePriceTag();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSbutton = findViewById(R.id.s_button);
        mMbutton = findViewById(R.id.m_button);
        mLbutton = findViewById(R.id.l_button);
        mOlivesbutton = findViewById(R.id.button_onion);
        mMushroomsbutton = findViewById(R.id.button_mushrooms);
        mColabutton = findViewById(R.id.button_cola);
        mPepperoniButton = findViewById(R.id.button_pepperoni);
        mTotalPrice = findViewById(R.id.total_price_button);
        mMushroomsImage = findViewById(R.id.mushrooms_image);
        mMushroomsImage.setVisibility(View.INVISIBLE);
        mOnionImage = findViewById(R.id.onion_image);
        mOnionImage.setVisibility(View.INVISIBLE);
        mPepperoniImage = findViewById(R.id.pepperoni_image);
        mPepperoniImage.setVisibility(View.INVISIBLE);
        // todo ask Efrat about the default size (or empty)
        changePizzaSize(PizzaSize.MEDIUM);
    }

    public void ClickS(View view) {
        changePizzaSize(PizzaSize.SMALL);
    }

    public void ClickM(View view) {
        changePizzaSize(PizzaSize.MEDIUM);
    }

    public void ClickL(View view) {
        changePizzaSize(PizzaSize.LARGE);
    }

    public void ClickOlives(View view) {
        if (!isOlives) {
            addExtras(PizzaExtra.ONION);
        } else {
            removeExtras(PizzaExtra.ONION);
        }
    }

    public void ClickMushrooms(View view) {
        if (!isMushrooms) {
            addExtras(PizzaExtra.MUSHROOMS);
        } else {
            removeExtras(PizzaExtra.MUSHROOMS);
        }
    }

    public void ClickCola(View view) {
        if (!isCola) {
            addExtras(PizzaExtra.COLA);
        } else {
            removeExtras(PizzaExtra.COLA);
        }
    }

    public void ClickPepperoni(View view) {
        if (!isPepperoni) {
            addExtras(PizzaExtra.PEPPERONI);
        } else {
            removeExtras(PizzaExtra.PEPPERONI);
        }
    }

    public void ClickCheckout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
}
