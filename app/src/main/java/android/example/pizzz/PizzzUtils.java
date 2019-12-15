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


    private static List<PizzaExtra> pizzaExtras = new ArrayList<>(Arrays.asList(
            PizzaExtra.MUSHROOMS, PizzaExtra.PEPPERONI,PizzaExtra.ONION,
            PizzaExtra.OLIVES,PizzaExtra.BASIL,PizzaExtra.EXTRA_CHEESE));

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

    /**
     * Sets the given map with keys from pizzaExtras, and values from given list
     * @param map the given map
     * @param values given list
     */
    static void setMap(Map<PizzaExtra, ImageView> map, List<ImageView> values){
        for (int i = 0; i < pizzaExtras.size(); i++) {
            map.put(pizzaExtras.get(i),values.get(i));
        }
    }
}


