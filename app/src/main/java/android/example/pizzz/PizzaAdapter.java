package android.example.pizzz;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    private List<Pizza> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    PizzaAdapter(Context context, List<Pizza> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pizza_view, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pizza pizza = mData.get(position);
        holder.pizzaNumber.setText(pizza.getTitle());
        holder.pizzaSize.setText(pizza.getSizeDescription());
        holder.pizzaExtras.setText(pizza.getExtrasDescription());
        holder.pizzaQuantity.setText(pizza.getQuantityDescription());
        SpannableString pizzaPriceString = new SpannableString(pizza.getTotalPriceDescription());
        pizzaPriceString.setSpan(new RelativeSizeSpan(0.8f), pizzaPriceString.length() - 3, pizzaPriceString.length(), 0);
        holder.pizzaPrice.setText(pizzaPriceString);
        Map<PizzaExtra, ImageView> extras = new HashMap<>();
        extras.put(PizzaExtra.MUSHROOMS, holder.mushroomsImage);
        extras.put(PizzaExtra.PEPPERONI, holder.pepperoniImage);
        extras.put(PizzaExtra.ONION, holder.onionImage);
        extras.put(PizzaExtra.OLIVES, holder.oliveImage);
        extras.put(PizzaExtra.BASIL, holder.basilImage);
        extras.put(PizzaExtra.EXTRA_CHEESE, holder.extraCheeseImage);
        setInvisible(new ImageView[]{holder.mushroomsImage, holder.pepperoniImage,
                holder.onionImage, holder.oliveImage, holder.basilImage, holder.extraCheeseImage});
        setExtras(extras, pizza);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pizzaNumber, pizzaSize, pizzaExtras, pizzaQuantity, pizzaPrice;
        ImageView mushroomsImage, pepperoniImage, onionImage, basilImage, oliveImage, extraCheeseImage;

        ViewHolder(View itemView) {
            super(itemView);
            pizzaNumber = itemView.findViewById(R.id.pizza_num);
            pizzaSize = itemView.findViewById(R.id.pizza_size);
            pizzaExtras = itemView.findViewById(R.id.pizza_extras);
            pizzaQuantity = itemView.findViewById(R.id.pizza_quantity);
            pizzaPrice = itemView.findViewById(R.id.pizza_price);

            mushroomsImage = itemView.findViewById(R.id.mushrooms_image);
            pepperoniImage = itemView.findViewById(R.id.pepperoni_image);
            onionImage = itemView.findViewById(R.id.onion_image);
            basilImage = itemView.findViewById(R.id.basil_image);
            oliveImage = itemView.findViewById(R.id.olives_image);
            extraCheeseImage = itemView.findViewById(R.id.extra_cheese_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Context context = view.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("pizza_number", getAdapterPosition());
            context.startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    Pizza getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    private void setInvisible(ImageView[] imageViewArrayList) {
        for (ImageView imageView : imageViewArrayList) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    private void setExtras(Map<PizzaExtra, ImageView> extras, Pizza pizza) {
        List<PizzaExtra> list = pizza.getExtras();
        if (list.size() > 0) {
            for (PizzaExtra pizzaExtra : list) {
                extras.get(pizzaExtra).setVisibility(View.VISIBLE);
            }
        }
    }
}
