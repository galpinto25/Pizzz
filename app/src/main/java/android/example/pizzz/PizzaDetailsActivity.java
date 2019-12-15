package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class responsible on the activity of choosing the pizza size and extras.
 */
public class PizzaDetailsActivity extends AppCompatActivity
{

    // class private variables declaration:
    private Pizza pizza;
    private PizzaFactory pizzaFactory;
    private ImageButton sbutton, mbutton, lbutton, onionbutton, mushroomsbutton, pepperoniButton, basilButton, oliveButton, extraCheeseButton,checkoutButton;
    private ImageView mushroomsImage, onionImage, pepperoniImage, basilImage, oliveImage, extraCheeseImage;
    private TextView totalPrice;
    private static final String currency = " NIS";

    /**
     * Creates a pizza instance, and set all the button in the activity in their default state
     * (visible or invisible).
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        // Pizza setting
        pizzaFactory = PizzaFactory.getPizzaFactory();
        pizza = pizzaFactory.getCurrentPizza();

        // Buttons setting
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

        // TextView setting
        totalPrice = findViewById(R.id.total_price);
        mushroomsImage = findViewById(R.id.mushrooms_image);
        pepperoniImage = findViewById(R.id.pepperoni_image);
        onionImage = findViewById(R.id.onion_image);
        basilImage = findViewById(R.id.basil_image);
        oliveImage = findViewById(R.id.olives_image);
        extraCheeseImage = findViewById(R.id.extra_cheese_image);
        ImageView[] extrasImages = new ImageView[]{mushroomsImage, onionImage, pepperoniImage,
                basilImage, extraCheeseImage, oliveImage};
        Bundle bundle = getIntent().getExtras();
        PizzzUtils.setInvisible(extrasImages);

        if (bundle == null) {
            pizza.reset();
        }

        updatePriceTag();
    }

    /**
     * Handles the previous activity launching.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
        {
            pizzaFactory.reset();
        }
        else if (bundle.getInt("delete_pizza_pressed") == 1) {
            // case of deleting the first pizza when there's only one
            pizzaFactory.reset();
            Intent intent = new Intent(this, OrderTypesActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Handles the resuming activity launching.
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        // if we came from the checkout screen (edit the pizza which was pressed):
        if (bundle != null)
        {
            pizza = pizzaFactory.getPizzaByIndex(bundle.getInt("pizza_number"));
            pizza.setExtrasPrice(0);
            this.changePizzaSize(pizza.getSize());
            List<PizzaExtra> pizzaExtras = pizza.getExtras();
            Map<PizzaExtra, ImageView> extras = new HashMap<>();
            List<ImageView> holders = new ArrayList<>(Arrays.asList(mushroomsImage, pepperoniImage,
                    onionImage, oliveImage, basilImage, extraCheeseImage));
            PizzzUtils.setMap(extras,holders);
            if (pizzaExtras.size() > 0)
            {
                for (PizzaExtra pizzaExtra : pizzaExtras)
                {
                    Objects.requireNonNull(extras.get(pizzaExtra)).setVisibility(View.VISIBLE);
                    addExtras(pizzaExtra);
                }
            }
        } else {
            // if new pizza button pressed:
            pizza = pizzaFactory.getCurrentPizza();
            checkoutButton.setImageResource(R.drawable.ic_continue);
            if (pizza.getSize() == PizzaSize.NONE) {
                checkoutButton.setImageResource(R.drawable.ic_continue_grey);
            }
        }
    }

    /**
     * Updates the price on the total_price_button.
     */
    @SuppressLint("SetTextI18n")
    private void updatePriceTag()
    {
        totalPrice.setText(pizza.getTotalPrice() + currency);
    }

    /**
     * Changes the size of the pizza to the size that the user chose, and update the price with
     * respect to it. The function set the button of pizzaSize pressed and the other button of size
     * not pressed.
     * @param pizzaSize - the size of the pizza after the change
     */
    private void changePizzaSize(PizzaSize pizzaSize)
    {
        // updates the small button
        if (pizzaSize == PizzaSize.SMALL)
        {
            sbutton.setImageResource(R.drawable.ic_small_black);
            pizza.setSize(PizzaSize.SMALL);
            pizza.setSizePrice(Pizza.SMALL_PRICE);
            checkoutButton.setImageResource(R.drawable.ic_continue);
        }
        else
        {
            sbutton.setImageResource(R.drawable.ic_small_white);
        }
        // updates the medium button
        if (pizzaSize == PizzaSize.MEDIUM)
        {
            mbutton.setImageResource(R.drawable.ic_medium_black);
            pizza.setSize(PizzaSize.MEDIUM);
            pizza.setSizePrice(Pizza.MEDIUM_PRICE);
            checkoutButton.setImageResource(R.drawable.ic_continue);

        }
        else
        {
            mbutton.setImageResource(R.drawable.ic_medium_white);
        }
        // updates the large button
        if (pizzaSize == PizzaSize.LARGE)
        {
            lbutton.setImageResource(R.drawable.ic_large_black);
            pizza.setSize(PizzaSize.LARGE);
            pizza.setSizePrice(Pizza.LARGE_PRICE);
            checkoutButton.setImageResource(R.drawable.ic_continue);
        }
        else
        {
            lbutton.setImageResource(R.drawable.ic_large_white);
        }

        updatePriceTag();
    }

    /**
     * Adds the chosen extra to the pizza, and update the price respectively.
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
     * Removes the chosen extra from the pizza, and update the price respectively.
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
     * Changes the pizza size to small and update the price with respect to the new size
     */
    public void ClickSmall(View view)
    {
        changePizzaSize(PizzaSize.SMALL);
    }

    /**
     * Changes the pizza size to medium and update the price with respect to the new size
     */
    public void ClickMedium(View view)
    {
        changePizzaSize(PizzaSize.MEDIUM);
    }

    /**
     * Changes the pizza size to large and update the price according to the new size
     */
    public void ClickLarge(View view)
    {
        changePizzaSize(PizzaSize.LARGE);
    }

    /**
     * Adds the onion extra if it wasn't pressed, else remove the onion. Update the price according
     * to the change.
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
     * Adds the mushrooms extra if it wasn't pressed, else remove the mushrooms. Update the price
     * according to the change.
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
     * Adds the pepperoni extra if it wasn't pressed, else remove the pepperoni. Update the price
     * according to the change.
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
     * Adds the basil extra if it wasn't pressed, else remove the basil. Update the price according
     * to the change.
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

    /**
     * Adds the extra cheese if it wasn't pressed, else remove the extra cheese . Update the price according
     * to the change.
     */
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

    /**
     * Adds the olives  extra if it wasn't pressed, else remove the olives. Update the price according
     * to the change.
     */
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

    /**
     * Makes the size buttons flash as a sign for the user to choose the pizza size.
     * Uses a timer which changes the color of pizza size buttons after a period of time.
     */
    private void flashSML() {
        sbutton.setImageResource(R.drawable.ic_small_black);
        mbutton.setImageResource(R.drawable.ic_medium_black);
        lbutton.setImageResource(R.drawable.ic_large_black);
        Timer timer = new Timer();
        TimerTask task = new Helper();
        timer.schedule(task, 250);
    }
    /**
     * Finishes the order and go the checkout activity.
     */
    public void ClickCheckout(View view) {
        if (pizza.getSize() == PizzaSize.NONE)
        {
            flashSML();
        }
        else
        {
            Intent intent = new Intent(this, CheckoutActivity.class);
            checkoutButton.setImageResource(R.drawable.ic_continue_grey);

            pizzaFactory.setNewPizza(pizza);
            startActivity(intent);
        }
    }

    /**
     * An helper class for the timer (which changes the color of pizza size buttons).
     */
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
