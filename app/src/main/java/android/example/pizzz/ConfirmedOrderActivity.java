package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class which represents the confirmation activity.
 */
public class ConfirmedOrderActivity extends AppCompatActivity {

    // class private variables declaration:
    private Map<PizzaExtra, ImageView> extras = new HashMap<>();

    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_order);
        setExtrasImages();
        setExtras();
    }

    /**
     * Sets the visibility of the given list of ImageView objects (which
     * represents the extras of a pizza) to invisible.
     * @param imageViewArrayList a given list of ImageView objects.
     */
    private void setInvisible(ImageView[] imageViewArrayList) {
        for (ImageView imageView : imageViewArrayList) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Sets the list of the ImageView objects (which represents the extras of a pizza), and sets
     * their visibility to invisible.
     */
    private void setExtrasImages() {
        ImageView mushroomsImage = findViewById(R.id.mushrooms_image_c);
        ImageView pepperoniImage = findViewById(R.id.pepperoni_image_c);
        ImageView onionImage = findViewById(R.id.onion_image_c);
        ImageView basilImage = findViewById(R.id.basil_image_c);
        ImageView oliveImage = findViewById(R.id.olives_image_c);
        ImageView extraCheeseImage = findViewById(R.id.extra_cheese_image_c);
        extras.put(PizzaExtra.MUSHROOMS, mushroomsImage);
        extras.put(PizzaExtra.PEPPERONI, pepperoniImage);
        extras.put(PizzaExtra.ONION, onionImage);
        extras.put(PizzaExtra.OLIVES, oliveImage);
        extras.put(PizzaExtra.BASIL, basilImage);
        extras.put(PizzaExtra.EXTRA_CHEESE, extraCheeseImage);
        setInvisible(new ImageView[]{mushroomsImage, onionImage, pepperoniImage, basilImage,
                extraCheeseImage, oliveImage});
    }

    /**
     * Sets the visibility of the ImageView objects which represents the included extras of the
     * first pizza, to visible.
     */
    private void setExtras() {
        List<PizzaExtra> list = PizzaFactory.getPizzaFactory().getPizzaByIndex(0).getExtras();
        if (list.size() > 0) {
            for (PizzaExtra pizzaExtra : list) {
                Objects.requireNonNull(extras.get(pizzaExtra)).setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * When the user back pressed, toasts the 'confirm_screen_onback' message to the screen.
     */
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, R.string.confirm_screen_onback_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

}
