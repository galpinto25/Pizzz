package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmedOrderActivity extends AppCompatActivity {

    private Map<PizzaExtra, ImageView> extras = new HashMap<>();

    List<PizzaExtra> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_order);
        setExtrasImages();
        setExtras();
    }

    private void setInvisible(ImageView[] imageViewArrayList) {
        for (ImageView imageView : imageViewArrayList) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }


    private void setExtrasImages() {
        ImageView mMushroomsImage = findViewById(R.id.mushrooms_image_c);
        ImageView mPepperoniImage = findViewById(R.id.pepperoni_image_c);
        ImageView mOnionImage = findViewById(R.id.onion_image_c);
        ImageView mBasilImage = findViewById(R.id.basil_image_c);
        ImageView mOliveImage = findViewById(R.id.olives_image_c);
        ImageView mExtraCheeseImage = findViewById(R.id.extra_cheese_image_c);
        extras.put(PizzaExtra.MUSHROOMS, mMushroomsImage);
        extras.put(PizzaExtra.PEPPERONI, mPepperoniImage);
        extras.put(PizzaExtra.ONION, mOnionImage);
        extras.put(PizzaExtra.OLIVES, mOliveImage);
        extras.put(PizzaExtra.BASIL, mBasilImage);
        extras.put(PizzaExtra.EXTRA_CHEESE, mExtraCheeseImage);
        setInvisible(new ImageView[]{mMushroomsImage, mOnionImage, mPepperoniImage, mBasilImage, mExtraCheeseImage, mOliveImage});

    }


    private void setExtras() {
        list = Pizza.getInstance().getExtras();
        if (list.size() > 0) {
            for (PizzaExtra pizzaExtra : list) {
                extras.get(pizzaExtra).setVisibility(View.VISIBLE);
            }
        }
    }
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

//    private void showPizza() {
//        Pizza pizza = Pizza.getInstance();
////      if there is no pizza instance, choose default pizza
//        if (pizza.getSize() == PizzaSize.NONE) {
//            pizza.setSize(PizzaSize.SMALL);
//            pizza.setPrice(Pizza.SMALL_PRICE);
//        }


}
