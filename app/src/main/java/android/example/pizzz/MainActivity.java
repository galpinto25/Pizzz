package android.example.pizzz;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


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
    private boolean isOlives = false;
    private boolean isMushrooms = false;
    private static final int smallPrice = 20;
    private static final int mediumPrice = 40;
    private static final int largePrice = 60;
    private static final String currency = " NIS";

    @SuppressLint("SetTextI18n")
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
        SpannableString priceString = new SpannableString(mPrice + mExtraPrice + currency);
        int priceLen = Integer.toString(mPrice + mExtraPrice).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0); // set size
        mTotalPrice.setText(priceString);
    }

    @SuppressLint("SetTextI18n")
    private void addExtras(PizzaExtra pizzaExtra) {
        if (pizzaExtra == PizzaExtra.ONION) {
            isOlives = true;
            mOlivesbutton.setImageResource(R.drawable.ic_onion_g);
            mExtraPrice += 4;
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS) {
            isMushrooms = true;
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms_g);
            mExtraPrice += 5;
        }
        SpannableString priceString = new SpannableString(mPrice + mExtraPrice + currency);
        int priceLen = Integer.toString(mPrice + mExtraPrice).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0); // set size
        mTotalPrice.setText(priceString);
    }

    @SuppressLint("SetTextI18n")
    private void removeExtras(PizzaExtra pizzaExtra) {
        if (pizzaExtra == PizzaExtra.ONION) {
            isOlives = false;
            mOlivesbutton.setImageResource(R.drawable.ic_onion);
            mExtraPrice -= 4;
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS) {
            isMushrooms = false;
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms);
            mExtraPrice -= 5;
        }
        SpannableString priceString = new SpannableString(mPrice + mExtraPrice + currency);
        int priceLen = Integer.toString(mPrice + mExtraPrice).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0); // set size
        mTotalPrice.setText(priceString);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSbutton = findViewById(R.id.s_button);
        mMbutton = findViewById(R.id.m_button);
        mLbutton = findViewById(R.id.l_button);
        mOlivesbutton = findViewById(R.id.button_onion);
        mMushroomsbutton = findViewById(R.id.button_mushrooms);
        mTotalPrice = findViewById(R.id.total_price_button);
        // todo ask Efrat about the default size (or empty)
        changePizzaSize(PizzaSize.MEDIUM);
    }

    @SuppressLint("SetTextI18n")
    public void ClickS(View view) {
        changePizzaSize(PizzaSize.SMALL);
    }

    @SuppressLint("SetTextI18n")
    public void ClickM(View view) {
        changePizzaSize(PizzaSize.MEDIUM);
    }

    @SuppressLint("SetTextI18n")
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

    public void ClickCheckout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
}
