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
    private Pizza mPizza;
    private Button mTotalPrice;
    private ImageButton mSbutton, mMbutton, mLbutton, mOlivesbutton, mMushroomsbutton, mPepperoniButton, mBasilButton;
    private ImageView mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage;
    private static final String currency = " NIS";

    /**
     * Create a pizza instance, and set all the button in the activity in their default state
     * (visible or invisible).
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPizza = Pizza.getInstance();

        // todo change name to small_button
        mSbutton = findViewById(R.id.s_button);
        // todo change name to medium_button
        mMbutton = findViewById(R.id.m_button);
        // todo change name to large_button
        mLbutton = findViewById(R.id.l_button);
        // todo olives or onion??
        mOlivesbutton = findViewById(R.id.button_onion);
        mMushroomsbutton = findViewById(R.id.button_mushrooms);
        mPepperoniButton = findViewById(R.id.button_pepperoni);
        mBasilButton = findViewById(R.id.button_basil);
        mTotalPrice = findViewById(R.id.total_price_button);
        mMushroomsImage = findViewById(R.id.mushrooms_image);
        mPepperoniImage = findViewById(R.id.pepperoni_image);
        mOnionImage = findViewById(R.id.onion_image);
        mBasilImage = findViewById(R.id.basil_image);
        ArrayList<ImageView> extrasImages = new ArrayList<>(Arrays.asList(mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage));
        setInvisible(extrasImages);
        // todo the deafault size is medium and the button should be grey (not black). please validate it!
        changePizzaSize(PizzaSize.MEDIUM);
    }

    /**
     * todo complete the documentation
     */
    private void updatePriceTag()
    {
        SpannableString priceString = new SpannableString(mPizza.getPrice() +
                mPizza.getExtras_price() + currency);
        int priceLen = Integer.toString(mPizza.getPrice() + mPizza.getExtras_price()).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0); // set size
        mTotalPrice.setText(priceString);
    }

    /**
     * Change the size of the pizza to the size that the user chose, and update the price with
     * respect to it. The function set the button of pizzaSize pressed and the other button of size
     * not pressed.
     * @param pizzaSize - the size of the pizza after the change
     */
    protected void changePizzaSize(PizzaSize pizzaSize)
    {
        // update the small button
        if (pizzaSize == PizzaSize.SMALL)
        {
            // todo change the button name to ic_small_black
            mSbutton.setImageResource(R.drawable.ic_small);
            mPizza.setSize(PizzaSize.SMALL);
            mPizza.setPrice(Pizza.SMALL_PRICE);
        }
        else
        {
            // todo change the button name to ic_small_white
            mSbutton.setImageResource(R.drawable.ic_small_w);
        }
        // update the medium button
        if (pizzaSize == PizzaSize.MEDIUM)
        {
            // todo change the button name to ic_medium_black
            mMbutton.setImageResource(R.drawable.ic_medium);
            mPizza.setSize(PizzaSize.MEDIUM);
            mPizza.setPrice(Pizza.MEDIUM_PRICE);
        }
        else
        {
            // todo change the button name to ic_medium_white
            mMbutton.setImageResource(R.drawable.ic_medium_w);
        }
        // update the large button
        if (pizzaSize == PizzaSize.LARGE)
        {
            // todo change the button name to ic_large_black
            mLbutton.setImageResource(R.drawable.ic_large);
            mPizza.setSize(PizzaSize.LARGE);
            mPizza.setPrice(Pizza.LARGE_PRICE);
        }
        else
        {
            // todo change the button name to ic_large_white
            mLbutton.setImageResource(R.drawable.ic_large_w);
        }

        updatePriceTag();
    }

    /**
     * Add the chosen extra to the pizza, and update the price respectively.
     * @param pizzaExtra - enum, represents the extra to add
     */
    private void addExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = mPizza.getExtras_price();
        if (pizzaExtra == PizzaExtra.ONION)
        {
            mPizza.setOnion(true);
            mPizza.setExtras_price(extraPrice + Pizza.ONION_PRICE);
            // todo olives??
            mOlivesbutton.setImageResource(R.drawable.ic_onion_g);
            mOnionImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            mPizza.setMushrooms(true);
            mPizza.setExtras_price(extraPrice + Pizza.MUSHROOMS_PRICE);
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms_g);
            mMushroomsImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            mPizza.setPepperoni(true);
            mPizza.setExtras_price(extraPrice + Pizza.PEPPERONI_PRICE);
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_b);
            mPepperoniImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            mPizza.setBasil(true);
            mPizza.setExtras_price(extraPrice + Pizza.BASIL_PRICE);
            mBasilButton.setImageResource(R.drawable.ic_paproni_b);
            mBasilImage.setVisibility(View.VISIBLE);
        }

        updatePriceTag();
    }

    /**
     * Remove the chosen extra from the pizza, and update the price respectively.
     * @param pizzaExtra - enum, represents the extra to remove
     */
    private void removeExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = mPizza.getExtras_price();
        if (pizzaExtra == PizzaExtra.ONION)
        {
            mPizza.setOnion(false);
            mPizza.setExtras_price(extraPrice - Pizza.ONION_PRICE);
            // todo olives or onion??
            mOlivesbutton.setImageResource(R.drawable.ic_onion);
            mOnionImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            mPizza.setMushrooms(false);
            mPizza.setExtras_price(extraPrice - Pizza.MUSHROOMS_PRICE);
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms);
            mMushroomsImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            mPizza.setPepperoni(false);
            mPizza.setExtras_price(extraPrice - Pizza.PEPPERONI_PRICE);
            mPepperoniButton.setImageResource(R.drawable.ic_paproni_w);
            mPepperoniImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            mPizza.setBasil(false);
            mPizza.setExtras_price(extraPrice - Pizza.BASIL_PRICE);
            mBasilButton.setImageResource(R.drawable.ic_paproni_w);
            mBasilImage.setVisibility(View.INVISIBLE);
        }

        updatePriceTag();
    }

    /**
     * todo complete the documentation
     * @param imageViewArrayList
     */
    private void setInvisible(ArrayList<ImageView> imageViewArrayList) {
        for (ImageView imageView : imageViewArrayList) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    // todo the parameter view is unused in all the next functions - does this parameter needed?
    /**
     * Change the pizza size to small and update the price with respect to the new size
     * @param view
     */
    public void ClickSmall(View view)
    {
        changePizzaSize(PizzaSize.SMALL);
    }

    /**
     * Change the pizza size to medium and update the price with respect to the new size
     * @param view
     */
    public void ClickMedium(View view)
    {
        changePizzaSize(PizzaSize.MEDIUM);
    }

    /**
     * Change the pizza size to large and update the price according to the new size
     * @param view
     */
    public void ClickLarge(View view)
    {
        changePizzaSize(PizzaSize.LARGE);
    }

    /**
     * Add the olives extra if it wasn't pressed, else remove the olives. Update the price according
     * to the change.
     * @param view
     */
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

    /**
     * Add the mushrooms extra if it wasn't pressed, else remove the mushrooms. Update the price
     * according to the change.
     * @param view
     */
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

    /**
     * Add the pepperoni extra if it wasn't pressed, else remove the pepperoni. Update the price
     * according to the change.
     * @param view
     */
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

    /**
     * Add the basil extra if it wasn't pressed, else remove the basil. Update the price according
     * to the change.
     * @param view
     */
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

    /**
     * Finish the order and replace the activity to the CheckoutActivity
     * @param view
     */
    public void ClickCheckout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
}
