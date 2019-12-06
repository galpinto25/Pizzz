package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity
{
    private Pizza mPizza;
    private PizzaFactory mPizzaFactory;
    private Button mCheckoutButton;
    private ImageButton mSbutton, mMbutton, mLbutton, mOnionbutton, mMushroomsbutton, mPepperoniButton, mBasilButton,mOliveButton,mExtraCheeseButton;
    private ImageView mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage,mOliveImage,mExtraCheeseImage;
    private TextView mPizzaCount, mTotalPrice;
    private static final String currency = " NIS";
    private static int count = 1;

    /**
     * Create a pizza instance, and set all the button in the activity in their default state
     * (visible or invisible).
     * @param savedInstanceState
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPizzaFactory = PizzaFactory.getPizzaFactory();
        mPizza = mPizzaFactory.getCurrentPizza();
        mSbutton = findViewById(R.id.small_button);
        mMbutton = findViewById(R.id.medium_button);
        mLbutton = findViewById(R.id.large_button);
        mOnionbutton = findViewById(R.id.button_onion);
        mMushroomsbutton = findViewById(R.id.button_mushrooms);
        mPepperoniButton = findViewById(R.id.button_pepperoni);
        mOliveButton = findViewById(R.id.button_olives);
        mExtraCheeseButton = findViewById(R.id.button_extra_cheese);
        mBasilButton = findViewById(R.id.button_basil);
        mTotalPrice = findViewById(R.id.total_price);
        mCheckoutButton = findViewById(R.id.checkout_button);
        mMushroomsImage = findViewById(R.id.mushrooms_image);
        mPepperoniImage = findViewById(R.id.pepperoni_image);
        mOnionImage = findViewById(R.id.onion_image);
        mBasilImage = findViewById(R.id.basil_image);
        mOliveImage= findViewById(R.id.olives_image);
        mExtraCheeseImage= findViewById(R.id.extra_cheese_image);
        mPizzaCount = findViewById(R.id.pizza_count);
        ArrayList<ImageView> extrasImages = new ArrayList<>(Arrays.asList(mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage,mExtraCheeseImage,mOliveImage));
        setInvisible(extrasImages);
//        changePizzaSize(PizzaSize.NONE);
        mPizzaCount.setText(Integer.toString(count));
        mPizza.reset();
        updatePriceTag();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPizza = mPizzaFactory.getCurrentPizza();
        mPizza.reset();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPizza = mPizzaFactory.getCurrentPizza();
        mCheckoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
        mCheckoutButton.setTextColor(Color.WHITE);
        if (mPizza.getSize() == PizzaSize.NONE) {
            mCheckoutButton.setBackgroundResource(R.drawable.ic_price_button_white);
            mCheckoutButton.setTextColor(Color.BLACK);
        }
    }

    /**
     * Updates the price on the total_price_button.
     */
    private void updatePriceTag()
    {
        // sets the price string as the total price of the pizza
        SpannableString priceString = new SpannableString(mPizza.getTotalPrice() + currency);
        // doubles the price text size
        int priceLen = Integer.toString(mPizza.getTotalPrice()).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0);
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
            mSbutton.setImageResource(R.drawable.ic_small_black);
            mPizza.setSize(PizzaSize.SMALL);
            mPizza.setSizePrice(Pizza.SMALL_PRICE);
            mCheckoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
            mCheckoutButton.setTextColor(Color.WHITE);
        }
        else
        {
            mSbutton.setImageResource(R.drawable.ic_small_white);
        }
        // update the medium button
        if (pizzaSize == PizzaSize.MEDIUM)
        {
            mMbutton.setImageResource(R.drawable.ic_medium_black);
            mPizza.setSize(PizzaSize.MEDIUM);
            mPizza.setSizePrice(Pizza.MEDIUM_PRICE);
            mCheckoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
            mCheckoutButton.setTextColor(Color.WHITE);
        }
        else
        {
            mMbutton.setImageResource(R.drawable.ic_medium_white);
        }
        // update the large button
        if (pizzaSize == PizzaSize.LARGE)
        {
            mLbutton.setImageResource(R.drawable.ic_large_black);
            mPizza.setSize(PizzaSize.LARGE);
            mPizza.setSizePrice(Pizza.LARGE_PRICE);
            mCheckoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
            mCheckoutButton.setTextColor(Color.WHITE);
        }
        else
        {
            mLbutton.setImageResource(R.drawable.ic_large_white);
        }

        updatePriceTag();
    }

    /**
     * Add the chosen extra to the pizza, and update the price respectively.
     * @param pizzaExtra - enum, represents the extra to add
     */
    private void addExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = mPizza.getExtrasPrice();
        if (mPizza.getSize() == PizzaSize.NONE)
        {
            flashSML();
            return;
        }
        if (pizzaExtra == PizzaExtra.ONION)
        {
            mPizza.setOnion(true);
            mPizza.setExtrasPrice(extraPrice + Pizza.ONION_PRICE);
            mOnionbutton.setImageResource(R.drawable.ic_onion_black);
            mOnionImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            mPizza.setMushrooms(true);
            mPizza.setExtrasPrice(extraPrice + Pizza.MUSHROOMS_PRICE);
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms_black);
            mMushroomsImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            mPizza.setPepperoni(true);
            mPizza.setExtrasPrice(extraPrice + Pizza.PEPPERONI_PRICE);
            mPepperoniButton.setImageResource(R.drawable.ic_pepperoni_black);
            mPepperoniImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            mPizza.setBasil(true);
            mPizza.setExtrasPrice(extraPrice + Pizza.BASIL_PRICE);
            mBasilButton.setImageResource(R.drawable.ic_basil_black);
            mBasilImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.OLIVES)
        {
            mPizza.setOlives(true);
            mPizza.setExtrasPrice(extraPrice + Pizza.OLIVES_PRICE);
            mOliveButton.setImageResource(R.drawable.ic_olives_black);
            mOliveImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.EXTRA_CHEESE)
        {
            mPizza.setExtraCheese(true);
            mPizza.setExtrasPrice(extraPrice + Pizza.EXTRA_CHEESE_PRICE);
            mExtraCheeseButton.setImageResource(R.drawable.ic_extra_cheese_black);
            mExtraCheeseImage.setVisibility(View.VISIBLE);
        }

        updatePriceTag();
    }

    /**
     * Remove the chosen extra from the pizza, and update the price respectively.
     * @param pizzaExtra - enum, represents the extra to remove
     */
    private void removeExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = mPizza.getExtrasPrice();
        if (pizzaExtra == PizzaExtra.ONION)
        {
            mPizza.setOnion(false);
            mPizza.setExtrasPrice(extraPrice - Pizza.ONION_PRICE);
            mOnionbutton.setImageResource(R.drawable.ic_onion_white);
            mOnionImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            mPizza.setMushrooms(false);
            mPizza.setExtrasPrice(extraPrice - Pizza.MUSHROOMS_PRICE);
            mMushroomsbutton.setImageResource(R.drawable.ic_mushrooms_white);
            mMushroomsImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            mPizza.setPepperoni(false);
            mPizza.setExtrasPrice(extraPrice - Pizza.PEPPERONI_PRICE);
            mPepperoniButton.setImageResource(R.drawable.ic_pepperoni_white);
            mPepperoniImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            mPizza.setBasil(false);
            mPizza.setExtrasPrice(extraPrice - Pizza.BASIL_PRICE);
            mBasilButton.setImageResource(R.drawable.ic_basil_white);
            mBasilImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.OLIVES)
        {
            mPizza.setOlives(false);
            mPizza.setExtrasPrice(extraPrice - Pizza.OLIVES_PRICE);
            mOliveButton.setImageResource(R.drawable.ic_olives_white);
            mOliveImage.setVisibility(View.INVISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.EXTRA_CHEESE)
        {
            mPizza.setExtraCheese(false);
            mPizza.setExtrasPrice(extraPrice - Pizza.EXTRA_CHEESE_PRICE);
            mExtraCheeseButton.setImageResource(R.drawable.ic_extra_cheese_white);
            mExtraCheeseImage.setVisibility(View.INVISIBLE);
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
     * Add the onion extra if it wasn't pressed, else remove the onion. Update the price according
     * to the change.
     * @param view
     */
    public void ClickOnion(View view)
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


    public void ClickExtraCheese(View view)
    {
        if (!mPizza.isExtraCheese())
        {
            addExtras(PizzaExtra.EXTRA_CHEESE);
        } else
        {
            removeExtras(PizzaExtra.EXTRA_CHEESE);
        }
    }

    public void ClickOlives(View view)
    {
        if (!mPizza.isOlives())
        {
            addExtras(PizzaExtra.OLIVES);
        } else
        {
            removeExtras(PizzaExtra.OLIVES);
        }
    }

    public void flashSML() {
        mSbutton.setImageResource(R.drawable.ic_small_black);
        mMbutton.setImageResource(R.drawable.ic_medium_black);
        mLbutton.setImageResource(R.drawable.ic_large_black);
        Timer timer = new Timer();
        TimerTask task = new Helper();
        timer.schedule(task, 250);
    }
    /**
     * Finish the order and replace the activity to the CheckoutActivity
     * @param view
     */
    public void ClickCheckout(View view) throws InterruptedException {
        if (mPizza.getSize() == PizzaSize.NONE) {
            flashSML();
        } else {
            Intent intent = new Intent(this, CheckoutActivity.class);
            mCheckoutButton.setBackgroundResource(R.drawable.ic_price_button_white);
            mCheckoutButton.setTextColor(Color.BLACK);
            startActivity(intent);
        }
    }


    //TODO: this two buutons below need to be deletd?
    @SuppressLint("SetTextI18n")
    public void clickMinusCount(View view) {
        if (mPizza.getSize() != PizzaSize.NONE) {
            if (mPizza.getQuantity() > 1) {
                mPizza.decCount();
                mPizzaCount.setText(Integer.toString(mPizza.getQuantity()));
            }
            updatePriceTag();
        }
        else
        {
            flashSML();
        }


    }

    @SuppressLint("SetTextI18n")
    public void clickPlusCount(View view) throws InterruptedException {
        if (mPizza.getSize() != PizzaSize.NONE)
        {
            if (mPizza.getQuantity() < 3) {
                mPizza.incCount();
                mPizzaCount.setText(Integer.toString(mPizza.getQuantity()));
            }
            updatePriceTag();
        }
        else
        {
            flashSML();
        }
    }

    class Helper extends TimerTask
    {
        public void run()
        {
            mSbutton.setImageResource(R.drawable.ic_small_white);
            mMbutton.setImageResource(R.drawable.ic_medium_white);
            mLbutton.setImageResource(R.drawable.ic_large_white);
        }
    }

}
