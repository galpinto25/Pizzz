package android.example.pizzz;
/*******************imports*******************************/
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*******************pizza adapter*******************************/


/**
 * A class which represents an adapter of Pizza objects, which extends
 * the RecyclerView.Adapter<PizzaAdapter.ViewHolder> class.
 */
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    // class private variables declaration:
    private List<Pizza> data;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    // data is passed into the constructor
    PizzaAdapter(Context context, List<Pizza> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    /**
     * inflates the row layout from xml when needed
     * @return a new single pizza view
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pizza_view, parent, false);
        return new ViewHolder(view);
    }

    /**
     *  binds the data to the View objects in each row
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // sets the pizza attributes of each pizza
        Pizza pizza = data.get(position);
        holder.pizzaNumber.setText("Pizza" + (position + 1));
        // todo check if needed with Efrat
//        holder.pizzaSize.setText(pizza.getSizeDescription().substring(0, 1));
        holder.pizzaExtras.setText(pizza.getExtrasDescription());
        holder.pizzaQuantity.setText(pizza.getQuantityDescription());
        SpannableString pizzaPriceString = new SpannableString(pizza.getTotalPriceDescription());
        pizzaPriceString.setSpan(new RelativeSizeSpan(0.8f),
                pizzaPriceString.length() - 3, pizzaPriceString.length(), 0);
        holder.pizzaPrice.setText(pizzaPriceString);
        holder.pizzaExtras.setMovementMethod(new ScrollingMovementMethod());
        // sets the included extras of each pizza
        Map<PizzaExtra, ImageView> extras = new HashMap<>();

        List<ImageView> holders = new ArrayList<>(Arrays.asList(holder.mushroomsImage,holder.pepperoniImage,holder.onionImage, holder.oliveImage,holder.basilImage, holder.extraCheeseImage));
        PizzzUtils.setMap(extras,holders);
        PizzzUtils.setInvisible(new ImageView[]{holder.mushroomsImage, holder.pepperoniImage,
                holder.onionImage, holder.oliveImage, holder.basilImage, holder.extraCheeseImage});
        PizzzUtils.setExtras(extras, pizza);
    }

    /**
     * @return the number of rows.
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Stores and recycles views as they are scrolled off screen.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // todo check if 'pizzaSize' is needed with Efrat
        TextView pizzaNumber, pizzaSize, pizzaExtras, pizzaQuantity, pizzaPrice;
        ImageView mushroomsImage, pepperoniImage, onionImage, basilImage, oliveImage,
                extraCheeseImage;
        ImageButton deleteButton, plusButton, minusButton;

        ViewHolder(View itemView) {
            super(itemView);

            // finds ids of TextView objects
            pizzaNumber = itemView.findViewById(R.id.pizza_num);
            // todo check if 'pizzaSize' is needed with Efrat
//            pizzaSize = itemView.findViewById(R.id.pizza_size);
            pizzaExtras = itemView.findViewById(R.id.pizza_extras);
            pizzaQuantity = itemView.findViewById(R.id.pizza_quantity);
            pizzaPrice = itemView.findViewById(R.id.pizza_price);

            // finds ids of ImageButton objects
            deleteButton = itemView.findViewById(R.id.delete_button);
            plusButton = itemView.findViewById(R.id.plus_count);
            minusButton = itemView.findViewById(R.id.minus_count);

            // finds ids of ImageView objects
            mushroomsImage = itemView.findViewById(R.id.mushrooms_image);
            pepperoniImage = itemView.findViewById(R.id.pepperoni_image);
            onionImage = itemView.findViewById(R.id.onion_image);
            basilImage = itemView.findViewById(R.id.basil_image);
            oliveImage = itemView.findViewById(R.id.olives_image);
            extraCheeseImage = itemView.findViewById(R.id.extra_cheese_image);

            // sets onClickListeners
            itemView.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
            plusButton.setOnClickListener(this);
            minusButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == itemView.getId()) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PizzaDetailsActivity.class);
                intent.putExtra("pizza_number", getAdapterPosition());
                context.startActivity(intent);
            }
            else if (view.getId() == deleteButton.getId()){
                if (clickListener != null) {
                    clickListener.onPizzaDeleteClick(view, getAdapterPosition());
                }
            }
            else if (view.getId() == plusButton.getId()){
                if (clickListener != null) {
                    clickListener.incPizzaQuantity(view, getAdapterPosition());
                }
            }
            else if (view.getId() == minusButton.getId()){
                if (clickListener != null) {
                    clickListener.decPizzaQuantity(view, getAdapterPosition());
                }
            }
        }
    }


    /**
     * allows clicks events to be caught
     */
    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /**
     * parent activity will implement this method to respond to click events
      */
    public interface ItemClickListener {
        void onPizzaDeleteClick(View view, int position);
        void incPizzaQuantity(View view, int position);
        void decPizzaQuantity(View view, int position);
    }

}
