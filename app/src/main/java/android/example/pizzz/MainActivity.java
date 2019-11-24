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

    private int mPrice = 0, mExtraPrice = 0;
    private PizzaSize mSize = PizzaSize.SMALL;
    private Button mTotalPrice;
    private ImageButton mSbutton, mMbutton, mLbutton, mOlivesbutton, mMushroomsbutton, mPepperoniButton, mColabutton, mBasilButton;
    private ImageView mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage, mColaImage;
    private boolean isOnion = false, isMushrooms = false, isCola = false, isPepperoni = false, isBasil = false;
    private static final int smallPrice = 20, mediumPrice = 40, largePrice = 60;
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
            isOnion = true;
            mOlivesbutton.setImageResource(R.drawable.ic_onion_g);
            mOnionImage.setVisibility(View.VISIBLE);
            mExtraPrice += 4;
        } else if (pizzaExtra == PizzaExtra.MUSHROOMS) {
            isMushrooms = true;
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms_g);
            mMushroomsImage.setVisibility(View.VISIBLE);
            mExtraPrice += 5;
        } else if (pizzaExtra == PizzaExtra.COLA) {
            isCola = true;
            mColabutton.setImageResource(R.drawable.ic_cola_b);
            mColaImage.setVisibility(View.VISIBLE);
            mExtraPrice += 8;
        } else if (pizzaExtra == PizzaExtra.PEPPERONI) {
            isPepperoni = true;
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_b);
            mPepperoniImage.setVisibility(View.VISIBLE);
            mExtraPrice += 11;
        } else if (pizzaExtra == PizzaExtra.BASIL) {
            isBasil = true;
            mBasilButton.setImageResource(R.drawable.ic_paproni_b);
            mBasilImage.setVisibility(View.VISIBLE);
            mExtraPrice += 3;
        }
        updatePriceTag();
    }

    private void updatePriceTag() {
        SpannableString priceString = new SpannableString(mPrice + mExtraPrice + currency);
        int priceLen = Integer.toString(mPrice + mExtraPrice).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0); // set size
        mTotalPrice.setText(priceString);
    }

    private void removeExtras(PizzaExtra pizzaExtra) {
        if (pizzaExtra == PizzaExtra.ONION) {
            isOnion = false;
            mOlivesbutton.setImageResource(R.drawable.ic_onion);
            mOnionImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 4;
        } else if (pizzaExtra == PizzaExtra.MUSHROOMS) {
            isMushrooms = false;
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms);
            mMushroomsImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 5;
        } else if (pizzaExtra == PizzaExtra.COLA) {
            isCola = false;
            mColabutton.setImageResource(R.drawable.ic_cola);
            mColaImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 8;
        } else if (pizzaExtra == PizzaExtra.PEPPERONI) {
            isPepperoni = false;
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_w);
            mPepperoniImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 11;
        } else if (pizzaExtra == PizzaExtra.BASIL) {
            isBasil = false;
            mBasilButton.setImageResource(R.drawable.ic_paproni_w);
            mBasilImage.setVisibility(View.INVISIBLE);
            mExtraPrice -= 3;
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
        mBasilButton = findViewById(R.id.button_basil);
        mTotalPrice = findViewById(R.id.total_price_button);
        mMushroomsImage = findViewById(R.id.mushrooms_image);
        mMushroomsImage.setVisibility(View.INVISIBLE);
        mOnionImage = findViewById(R.id.onion_image);
        mOnionImage.setVisibility(View.INVISIBLE);
        mPepperoniImage = findViewById(R.id.pepperoni_image);
        mPepperoniImage.setVisibility(View.INVISIBLE);
        mBasilImage = findViewById(R.id.basil_image);
        mBasilImage.setVisibility(View.INVISIBLE);
        mColaImage = findViewById(R.id.cola_image);
        mColaImage.setVisibility(View.INVISIBLE);
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
        if (!isOnion) {
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

    public void ClickBasil(View view) {
        if (!isBasil) {
            addExtras(PizzaExtra.BASIL);
        } else {
            removeExtras(PizzaExtra.BASIL);
        }
    }

    public void ClickCheckout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

}
