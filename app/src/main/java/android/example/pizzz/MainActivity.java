package android.example.pizzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private int mPrice = 0;
    private Size mSize = Size.SMALL;
    private Button mTotalPrice;
    private ImageButton mSbutton;
    private ImageButton mMbutton;
    private ImageButton mLbutton;
    private Boolean mSclicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSbutton = (ImageButton) findViewById(R.id.s_button);
        mMbutton = (ImageButton) findViewById(R.id.m_button);
        mLbutton = (ImageButton) findViewById(R.id.l_button);
        mSclicked = false;
        mSize = Size.MEDIUM;
        mMbutton.setImageResource(R.mipmap.medium_clicked_foreground);
        mPrice = 40;
        mTotalPrice = (Button) findViewById(R.id.total_price_button);
        mTotalPrice.setText(mPrice + " NIS");
    }

    public void ClickS(View view) {
        mSclicked = !mSclicked;
        if (mSize != Size.SMALL) {
            mSbutton.setImageResource(R.mipmap.small_clicked_foreground);
            mMbutton.setImageResource(R.mipmap.medium_not_clicked_foreground);
            mLbutton.setImageResource(R.mipmap.large_not_clicked_foreground);
            mPrice = 20;
            mSize = Size.SMALL;
            mTotalPrice = (Button) findViewById(R.id.total_price_button);
            mTotalPrice.setText(mPrice + " NIS");
        }
    }

    public void ClickM(View view) {
//        mSclicked = !mSclicked;
        if (mSize != Size.MEDIUM) {
            mSbutton.setImageResource(R.mipmap.small_not_clicked_foreground);
            mMbutton.setImageResource(R.mipmap.medium_clicked_foreground);
            mLbutton.setImageResource(R.mipmap.large_not_clicked_foreground);

            mPrice = 40;
            mSize = Size.MEDIUM;
            mTotalPrice = (Button) findViewById(R.id.total_price_button);
            mTotalPrice.setText(mPrice + " NIS");
        }
    }

    public void ClickL(View view) {
        if (mSize != Size.LARGE) {
            mSbutton.setImageResource(R.mipmap.small_not_clicked_foreground);
            mMbutton.setImageResource(R.mipmap.medium_not_clicked_foreground);
            mLbutton.setImageResource(R.mipmap.large_clicked_foreground);

            mPrice = 60;
            mSize = Size.LARGE;
            mTotalPrice = (Button) findViewById(R.id.total_price_button);
            mTotalPrice.setText(mPrice + " NIS");
        }
    }
}

