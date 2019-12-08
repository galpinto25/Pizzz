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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class PizzaDetailsActivity extends AppCompatActivity
{
    private Pizza pizza;
    private PizzaFactory pizzaFactory;
    private Button checkoutButton;
    private ImageButton sbutton, mbutton, lbutton, onionbutton, mushroomsbutton, pepperoniButton, basilButton, oliveButton, extraCheeseButton;
    private ImageView mushroomsImage, onionImage, pepperoniImage, basilImage, oliveImage, extraCheeseImage;
    private TextView pizzaCount, totalPrice;
    ArrayList<ImageView> extrasImages = new ArrayList<>();
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
        setContentView(R.layout.activity_pizza_details);

        //pizza setting
        pizzaFactory = PizzaFactory.getPizzaFactory();
        pizza = pizzaFactory.getCurrentPizza();

        //buttons setting
        sbutton = findViewById(R.id.small_button);
        mbutton = findViewById(R.id.medium_button);
        lbutton = findViewById(R.id.large_button);
        onionbutton = findViewById(R.id.button_onion);
        mushroomsbutton = findViewById(R.id.button_mushrooms);
        pepperoniButton = findViewById(R.id.button_pepperoni);
        oliveButton = findViewById(R.id.button_olives);
        extraCheeseButton = findViewById(R.id.button_extra_cheese);
        basilButton = findViewById(R.id.button_basil);
        checkoutButton = findViewById(R.id.checkout_button);

        //textView setting
        totalPrice = findViewById(R.id.total_price);
        pizzaCount = findViewById(R.id.pizza_count);
        mushroomsImage = findViewById(R.id.mushrooms_image);
        pepperoniImage = findViewById(R.id.pepperoni_image);
        onionImage = findViewById(R.id.onion_image);
        basilImage = findViewById(R.id.basil_image);
        oliveImage = findViewById(R.id.olives_image);
        extraCheeseImage = findViewById(R.id.extra_cheese_image);
        extrasImages = new ArrayList<>(Arrays.asList(mushroomsImage, onionImage, pepperoniImage, basilImage, extraCheeseImage, oliveImage));
        Bundle bundle = getIntent().getExtras();
        setInvisible(extrasImages);
        pizzaCount.setText(Integer.toString(count));

        if (bundle == null) {
            pizza.reset();
//            changePizzaSize(PizzaSize.NONE);
        }
        updatePriceTag();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
        {
            pizzaFactory.getPizzas().remove(pizzaFactory.getCurrentPizzaIndex());
            pizzaFactory.setCurrentPizzaIndex(pizzaFactory.getCurrentPizzaIndex() - 1);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            pizza = pizzaFactory.getPizzaByIndex(bundle.getInt("pizza_number"));
            pizza.setExtrasPrice(0);
            this.changePizzaSize(pizza.getSize());
            pizzaCount.setText(Integer.toString(pizza.getQuantity()));
            List<PizzaExtra> pizzaExtras = pizza.getExtras();
            Map<PizzaExtra, ImageView> extras = new HashMap<>();
            extras.put(PizzaExtra.MUSHROOMS, mushroomsImage);
            extras.put(PizzaExtra.PEPPERONI, pepperoniImage);
            extras.put(PizzaExtra.ONION, onionImage);
            extras.put(PizzaExtra.OLIVES, oliveImage);
            extras.put(PizzaExtra.BASIL, basilImage);
            extras.put(PizzaExtra.EXTRA_CHEESE, extraCheeseImage);
            if (pizzaExtras.size() > 0)
            {
                for (PizzaExtra pizzaExtra : pizzaExtras)
                {
                    extras.get(pizzaExtra).setVisibility(View.VISIBLE);
                    addExtras(pizzaExtra);
                }
            }
//            Toast toast = Toast.makeText(this, pizza.getTitle(), Toast.LENGTH_SHORT);
//            toast.show();
        } else {
            pizza = pizzaFactory.getCurrentPizza();
            checkoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
            checkoutButton.setTextColor(Color.WHITE);
            if (pizza.getSize() == PizzaSize.NONE) {
                checkoutButton.setBackgroundResource(R.drawable.ic_price_button_white);
                checkoutButton.setTextColor(Color.BLACK);
            }
        }
    }

    /**
     * Updates the price on the total_price_button.
     */
    private void updatePriceTag()
    {
        // sets the price string as the total price of the pizza
        SpannableString priceString = new SpannableString(pizza.getTotalPrice() + currency);
        // doubles the price text size
        int priceLen = Integer.toString(pizza.getTotalPrice()).length();
        priceString.setSpan(new RelativeSizeSpan(2f), 0, priceLen, 0);
        totalPrice.setText(priceString);
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
            sbutton.setImageResource(R.drawable.ic_small_black);
            pizza.setSize(PizzaSize.SMALL);
            pizza.setSizePrice(Pizza.SMALL_PRICE);
            checkoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
            checkoutButton.setTextColor(Color.WHITE);
        }
        else
        {
            sbutton.setImageResource(R.drawable.ic_small_white);
        }
        // update the medium button
        if (pizzaSize == PizzaSize.MEDIUM)
        {
            mbutton.setImageResource(R.drawable.ic_medium_black);
            pizza.setSize(PizzaSize.MEDIUM);
            pizza.setSizePrice(Pizza.MEDIUM_PRICE);
            checkoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
            checkoutButton.setTextColor(Color.WHITE);
        }
        else
        {
            mbutton.setImageResource(R.drawable.ic_medium_white);
        }
        // update the large button
        if (pizzaSize == PizzaSize.LARGE)
        {
            lbutton.setImageResource(R.drawable.ic_large_black);
            pizza.setSize(PizzaSize.LARGE);
            pizza.setSizePrice(Pizza.LARGE_PRICE);
            checkoutButton.setBackgroundResource(R.drawable.ic_price_button_black);
            checkoutButton.setTextColor(Color.WHITE);
        }
        else
        {
            lbutton.setImageResource(R.drawable.ic_large_white);
        }

        updatePriceTag();
    }

    /**
     * Add the chosen extra to the pizza, and update the price respectively.
     * @param pizzaExtra - enum, represents the extra to add
     */
    private void addExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = pizza.getExtrasPrice();
        if (pizza.getSize() == PizzaSize.NONE)
        {
            flashSML();
            return;
        }
        if (pizzaExtra == PizzaExtra.ONION)
        {
            pizza.setOnion(true);
            pizza.setExtrasPrice(extraPrice + Pizza.ONION_PRICE);
            onionbutton.setImageResource(R.drawable.ic_onion_black);
            onionImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            pizza.setMushrooms(true);
            pizza.setExtrasPrice(extraPrice + Pizza.MUSHROOMS_PRICE);
            mushroomsbutton.setImageResource(R.drawable.ic_mushrooms_black);
            mushroomsImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            pizza.setPepperoni(true);
            pizza.setExtrasPrice(extraPrice + Pizza.PEPPERONI_PRICE);
            pepperoniButton.setImageResource(R.drawable.ic_pepperoni_black);
            pepperoniImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            pizza.setBasil(true);
            pizza.setExtrasPrice(extraPrice + Pizza.BASIL_PRICE);
            basilButton.setImageResource(R.drawable.ic_basil_black);
            basilImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.OLIVES)
        {
            pizza.setOlives(true);
            pizza.setExtrasPrice(extraPrice + Pizza.OLIVES_PRICE);
            oliveButton.setImageResource(R.drawable.ic_olives_black);
            oliveImage.setVisibility(View.VISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.EXTRA_CHEESE)
        {
            pizza.setExtraCheese(true);
            pizza.setExtrasPrice(extraPrice + Pizza.EXTRA_CHEESE_PRICE);
            extraCheeseButton.setImageResource(R.drawable.ic_extra_cheese_black);
            extraCheeseImage.setVisibility(View.VISIBLE);
        }

        updatePriceTag();
    }

    /**
     * Remove the chosen extra from the pizza, and update the price respectively.
     * @param pizzaExtra - enum, represents the extra to remove
     */
    private void removeExtras(PizzaExtra pizzaExtra)
    {
        int extraPrice = pizza.getExtrasPrice();
        if (pizzaExtra == PizzaExtra.ONION)
        {
            pizza.setOnion(false);
            pizza.setExtrasPrice(extraPrice - Pizza.ONION_PRICE);
            onionbutton.setImageResource(R.drawable.ic_onion_white);
            onionImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.MUSHROOMS)
        {
            pizza.setMushrooms(false);
            pizza.setExtrasPrice(extraPrice - Pizza.MUSHROOMS_PRICE);
            mushroomsbutton.setImageResource(R.drawable.ic_mushrooms_white);
            mushroomsImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.PEPPERONI)
        {
            pizza.setPepperoni(false);
            pizza.setExtrasPrice(extraPrice - Pizza.PEPPERONI_PRICE);
            pepperoniButton.setImageResource(R.drawable.ic_pepperoni_white);
            pepperoniImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.BASIL)
        {
            pizza.setBasil(false);
            pizza.setExtrasPrice(extraPrice - Pizza.BASIL_PRICE);
            basilButton.setImageResource(R.drawable.ic_basil_white);
            basilImage.setVisibility(View.INVISIBLE);
        }

        else if (pizzaExtra == PizzaExtra.OLIVES)
        {
            pizza.setOlives(false);
            pizza.setExtrasPrice(extraPrice - Pizza.OLIVES_PRICE);
            oliveButton.setImageResource(R.drawable.ic_olives_white);
            oliveImage.setVisibility(View.INVISIBLE);
        }
        else if (pizzaExtra == PizzaExtra.EXTRA_CHEESE)
        {
            pizza.setExtraCheese(false);
            pizza.setExtrasPrice(extraPrice - Pizza.EXTRA_CHEESE_PRICE);
            extraCheeseButton.setImageResource(R.drawable.ic_extra_cheese_white);
            extraCheeseImage.setVisibility(View.INVISIBLE);
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
        if (!pizza.isOnion())
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
        if (!pizza.isMushrooms())
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
        if (!pizza.isPepperoni())
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
        if (!pizza.isBasil())
        {
            addExtras(PizzaExtra.BASIL);
        } else
        {
            removeExtras(PizzaExtra.BASIL);
        }
    }


    public void ClickExtraCheese(View view)
    {
        if (!pizza.isExtraCheese())
        {
            addExtras(PizzaExtra.EXTRA_CHEESE);
        } else
        {
            removeExtras(PizzaExtra.EXTRA_CHEESE);
        }
    }

    public void ClickOlives(View view)
    {
        if (!pizza.isOlives())
        {
            addExtras(PizzaExtra.OLIVES);
        } else
        {
            removeExtras(PizzaExtra.OLIVES);
        }
    }

    public void flashSML() {
        sbutton.setImageResource(R.drawable.ic_small_black);
        mbutton.setImageResource(R.drawable.ic_medium_black);
        lbutton.setImageResource(R.drawable.ic_large_black);
        Timer timer = new Timer();
        TimerTask task = new Helper();
        timer.schedule(task, 250);
    }
    /**
     * Finish the order and replace the activity to the CheckoutActivity
     * @param view
     */
    public void ClickCheckout(View view) throws InterruptedException {
        if (pizza.getSize() == PizzaSize.NONE)
        {
            flashSML();
        }
        else
        {
            Intent intent = new Intent(this, CheckoutActivity.class);
            checkoutButton.setBackgroundResource(R.drawable.ic_price_button_white);
            checkoutButton.setTextColor(Color.BLACK);
            pizzaFactory.setNewPizza(pizza);
            startActivity(intent);
        }
    }


    //TODO: this two buutons below need to be deletd?
    @SuppressLint("SetTextI18n")
    public void clickMinusCount(View view) {
        if (pizza.getSize() != PizzaSize.NONE) {
            if (pizza.getQuantity() > 1) {
                pizza.decCount();
                pizzaCount.setText(Integer.toString(pizza.getQuantity()));
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
        if (pizza.getSize() != PizzaSize.NONE)
        {
            if (pizza.getQuantity() < 3) {
                pizza.incCount();
                pizzaCount.setText(Integer.toString(pizza.getQuantity()));
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
            sbutton.setImageResource(R.drawable.ic_small_white);
            mbutton.setImageResource(R.drawable.ic_medium_white);
            lbutton.setImageResource(R.drawable.ic_large_white);
        }
    }

}
