package android.example.pizzz;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class which includes useful functions.
 */
class PizzzUtils {


    static List<PizzaExtra> pizzaExtras = new ArrayList<>(Arrays.asList(PizzaExtra.MUSHROOMS,PizzaExtra.PEPPERONI,PizzaExtra.ONION, PizzaExtra.OLIVES,PizzaExtra.BASIL,PizzaExtra.EXTRA_CHEESE));

    /**
     * Sets the visibility of the given list of ImageView objects (which
     * represents the extras of a pizza) to invisible.
     * @param imageViewArrayList a given list of ImageView objects.
     */
    static void setInvisible(ImageView[] imageViewArrayList) {
        for (ImageView imageView : imageViewArrayList) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Sets the visibility of the ImageView objects which represents the included extras of the
     * first pizza, to visible.
     */
    static void setExtras(Map<PizzaExtra, ImageView> extras, Pizza pizza) {
        List<PizzaExtra> list = pizza.getExtras();
        if (list.size() > 0) {
            for (PizzaExtra pizzaExtra : list) {
                Objects.requireNonNull(extras.get(pizzaExtra)).setVisibility(View.VISIBLE);
            }
        }
    }

    static void setNASAffk(Map<PizzaExtra, ImageView> extras, List<ImageView> list2){
        for (int i = 0; i < pizzaExtras.size(); i++) {
            extras.put(pizzaExtras.get(i),list2.get(i));
        }
    }
}


