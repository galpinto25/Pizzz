package android.example.pizzz;

import android.view.View;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

public class PizzzUtils {

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
                extras.get(pizzaExtra).setVisibility(View.VISIBLE);
            }
        }
    }


}


