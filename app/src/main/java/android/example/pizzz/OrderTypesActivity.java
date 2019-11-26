package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class OrderTypesActivity extends AppCompatActivity
{
    private ImageButton mNewOrder, mReOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_types);
        mNewOrder = findViewById(R.id.new_order);
        mReOrder = findViewById(R.id.re_order);
        Log.d("debug", "onCreate");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mReOrder.setImageResource(R.drawable.ic_re_order_white);
        mNewOrder.setImageResource(R.drawable.ic_neworder_white);
    }

    public void click_re_order(View view)
    {
        mReOrder.setImageResource(R.drawable.ic_re_order_black);
        mNewOrder.setImageResource(R.drawable.ic_neworder_white);

        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

    public void click_new(View view)
    {
        // todo change name to ic_new_order_black
        mNewOrder.setImageResource(R.drawable.ic_new_order_b);
        mReOrder.setImageResource(R.drawable.ic_re_order_white);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
