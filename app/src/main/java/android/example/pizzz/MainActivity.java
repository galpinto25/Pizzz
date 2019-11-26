package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;



public class MainActivity extends AppCompatActivity
{
    private Button mTotalPrice;
    private ImageButton mSbutton, mMbutton, mLbutton, mOlivesbutton, mMushroomsbutton, mPepperoniButton, mBasilButton;
    private ImageView mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage;
    private static final int smallPrice = 20, mediumPrice = 40, largePrice = 60;
    private static final String currency = " NIS";
    private Pizza mPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSbutton = findViewById(R.id.s_button);
        mMbutton = findViewById(R.id.m_button);
        mLbutton = findViewById(R.id.l_button);
        mOlivesbutton = findViewById(R.id.button_onion);
        mMushroomsbutton = findViewById(R.id.button_mushrooms);
        mPepperoniButton = findViewById(R.id.button_pepperoni);
        mBasilButton = findViewById(R.id.button_basil);
        mTotalPrice = findViewById(R.id.total_price_button);
        mMushroomsImage = findViewById(R.id.mushrooms_image);
        mPepperoniImage = findViewById(R.id.pepperoni_image);
        mOnionImage = findViewById(R.id.onion_image);
        mBasilImage = findViewById(R.id.basil_image);
        mPizza = Pizza.getInstance();
        ArrayList<ImageView> list = new ArrayList<>(Arrays.asList(mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage));
        setInvisible(list);
        // todo ask Efrat about the default size (or empty)
        changePizzaSize(PizzaSize.MEDIUM);
    }

    protected void changePizzaSize(PizzaSize pizzaSize)
    {
        if (pizzaSize == PizzaSize.SMALL)
        {
            mSbutton.setImageResource(R.drawable.ic_small);
            mPizza.setSize(PizzaSize.SMALL);
            mPizza.setPrice(smallPrice);
        }
        else
        {
            mSbutton.setImageResource(R.drawable.ic_small_w);
        }

        if (pizzaSize == PizzaSize.MEDIUM)
        {
            mMbutton.setImageResource(R.drawable.ic_medium);
            mPizza.setSize(PizzaSize.MEDIUM);
            mPizza.setPrice(mediumPrice);
        }
        else
        {
            mMbutton.setImageResource(R.drawable.ic_medium_w);
        }

        if (pizzaSize == PizzaSize.LARGE)
        {
            mLbutton.setImageResource(R.drawable.ic_large);
            mPizza.setSize(PizzaSize.LARGE);
            mPizza.setPrice(largePrice);
        }
        else
        {
            mLbutton.setImageResource(R.drawable.ic_large_w);
        }

        updatePriceTag();
    }

    private void addExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = mPizza.getExtras_price();
        if (pizzaExtra == PizzaExtra.ONION)
        {
            mPizza.setOnion(true);
            mOlivesbutton.setImageResource(R.drawable.ic_onion_g);
            mOnionImage.setVisibility(View.VISIBLE);
            mPizza.setExtras_price(extraPrice + 4);
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            mPizza.setMushrooms(true);
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms_g);
            mMushroomsImage.setVisibility(View.VISIBLE);
            mPizza.setExtras_price(extraPrice + 5);
        }
        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            mPizza.setPepperoni(true);
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_b);
            mPepperoniImage.setVisibility(View.VISIBLE);
            mPizza.setExtras_price(extraPrice + 11);
        }
        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            mPizza.setBasil(true);
            mBasilButton.setImageResource(R.drawable.ic_paproni_b);
            mBasilImage.setVisibility(View.VISIBLE);
            mPizza.setExtras_price(extraPrice + 3);
        }
        updatePriceTag();
    }

    private void updatePriceTag()
    {
        SpannableString priceString = new SpannableString(mPizza.getPrice() +
                mPizza.getExtras_price() + currency);
        int priceLen = Integer.toString(mPizza.getPrice() + mPizza.getExtras_price()).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0); // set size
        mTotalPrice.setText(priceString);
    }

    private void removeExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = mPizza.getExtras_price();
        if (pizzaExtra == PizzaExtra.ONION)
        {
            mPizza.setOnion(false);
            mOlivesbutton.setImageResource(R.drawable.ic_onion);
            mOnionImage.setVisibility(View.INVISIBLE);
            mPizza.setExtras_price(extraPrice - 4);
        }

        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            mPizza.setMushrooms(false);
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms);
            mMushroomsImage.setVisibility(View.INVISIBLE);
            mPizza.setExtras_price(extraPrice - 5);
        }

        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            mPizza.setPepperoni(false);
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_w);
            mPepperoniImage.setVisibility(View.INVISIBLE);
            mPizza.setExtras_price(extraPrice - 11);
        }

        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            mPizza.setBasil(false);
            mBasilButton.setImageResource(R.drawable.ic_paproni_w);
            mBasilImage.setVisibility(View.INVISIBLE);
            mPizza.setExtras_price(extraPrice - 3);
        }

        updatePriceTag();
    }

    private void setInvisible(ArrayList<ImageView> imageViewArrayList) {
        for (ImageView imageView : imageViewArrayList) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }


    public void ClickS(View view)
    {
        changePizzaSize(PizzaSize.SMALL);
    }

    public void ClickM(View view)
    {
        changePizzaSize(PizzaSize.MEDIUM);
    }

    public void ClickL(View view)
    {
        changePizzaSize(PizzaSize.LARGE);
    }

    public void ClickOlives(View view)
    {
        if (!mPizza.isOnion())
        {
            addExtras(PizzaExtra.ONION);
        }
        else
        {
            removeExtras(PizzaExtra.ONION);
        }
    }

    public void ClickMushrooms(View view)
    {
        if (!mPizza.isMushrooms())
        {
            addExtras(PizzaExtra.MUSHROOMS);
        }
        else
        {
            removeExtras(PizzaExtra.MUSHROOMS);
        }
    }


    public void ClickPepperoni(View view) {
        if (!mPizza.isPepperoni())
        {
            addExtras(PizzaExtra.PEPPERONI);
        }
        else
        {
            removeExtras(PizzaExtra.PEPPERONI);
        }
    }

    public void ClickBasil(View view)
    {
        if (!mPizza.isBasil())
        {
            addExtras(PizzaExtra.BASIL);
        } else
        {
            removeExtras(PizzaExtra.BASIL);
        }
    }

    public void ClickCheckout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

}
