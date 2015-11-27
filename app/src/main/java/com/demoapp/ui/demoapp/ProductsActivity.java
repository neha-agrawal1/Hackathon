package com.demoapp.ui.demoapp;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demoapp.fragments.OmniPayFragment;


public class ProductsActivity extends ActionBarActivity {

    private Button mOmniPayButton;
    private CheckBox mCbProduct1, mCbProduct2;
    private TextView mProductName1, mProdcutName2;
    private TextView mPrice1, mPrice2;
    private TextView mTotalPrice1, mTotalPrice2;
    private TextView mTotalPrice;
    private Button mPlus1, mMinus1, mPlus2, mMinus2;
    private TextView mQty1, mQty2;
    private int qty1, qty2, price1, price2, totalPrice1, totalPrice2, totalPrice;
    private LinearLayout mTotalPriceProduct1LinearLayout, mTotalPriceProduct2LinearLayout, mTotalPriceProductLinearLayout;
    private String toastText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        initComponents();
        addListeners();
        addTextWatcher();
        qty1 = Integer.parseInt(mQty1.getText().toString());
        qty2 = Integer.parseInt(mQty2.getText().toString());
        price1 = Integer.parseInt(mPrice1.getText().toString());
        price2 = Integer.parseInt(mPrice2.getText().toString());
        enableMinusButtons();
    }

    private void addTextWatcher() {

        mQty1.addTextChangedListener(mTextWatcher);
        mQty2.addTextChangedListener(mTextWatcher);
    }

    private void showOmniPayDialog() {
        OmniPayFragment omnipayFragment = new OmniPayFragment();
        omnipayFragment.show(getSupportFragmentManager(), "fragment_tag");
    }

    private void addListeners() {

        mOmniPayButton.setOnClickListener(mClickListener);
        mPlus1.setOnClickListener(mClickListener);
        mPlus2.setOnClickListener(mClickListener);
        mMinus1.setOnClickListener(mClickListener);
        mMinus2.setOnClickListener(mClickListener);
        mCbProduct1.setOnClickListener(mClickListener);
        mCbProduct2.setOnClickListener(mClickListener);

    }

    private void initComponents() {
        mOmniPayButton = (Button) findViewById(R.id.btnOmniPay);
        mCbProduct1 = (CheckBox) findViewById(R.id.cbProduct1);
        mCbProduct2 = (CheckBox) findViewById(R.id.cbProduct2);

        mProductName1 = (TextView) findViewById(R.id.tvProductName1);
        mProdcutName2 = (TextView) findViewById(R.id.tvProductName2);

        mPrice1 = (TextView) findViewById(R.id.tvPrice1);
        mPrice2 = (TextView) findViewById(R.id.tvPrice2);

        mTotalPrice1 = (TextView) findViewById(R.id.tvProduct1TotalPrice);
        mTotalPrice2 = (TextView) findViewById(R.id.tvProduct2TotalPrice);

        mTotalPrice = (TextView) findViewById(R.id.tvProductTotalPrice);

        mPlus1 = (Button) findViewById(R.id.btnPlus1);
        mPlus2 = (Button) findViewById(R.id.btnPlus2);
        mMinus1 = (Button) findViewById(R.id.btnMinus1);
        mMinus2 = (Button) findViewById(R.id.btnMinus2);

        mQty1 = (TextView) findViewById(R.id.tvNumOfQty1);
        mQty2 = (TextView) findViewById(R.id.tvNumOfQty2);

        mTotalPriceProduct1LinearLayout = (LinearLayout) findViewById(R.id.llProduct1Total);
        mTotalPriceProduct2LinearLayout = (LinearLayout) findViewById(R.id.llProduct2Total);
        mTotalPriceProductLinearLayout = (LinearLayout) findViewById(R.id.llProductTotal);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnOmniPay:
                    showOmniPayDialog();
                    break;
                case R.id.btnPlus1:
                    incrementQtyOfProduct1();
                    break;
                case R.id.btnPlus2:
                    incrementQtyOfProduct2();
                    break;
                case R.id.btnMinus1:
                    decrementQtyOfProduct1();
                    break;
                case R.id.btnMinus2:
                    decrementQtyOfProduct2();
                    break;
                case R.id.cbProduct1:
                    selectedProduct1PriceDetails();
                    break;
                case R.id.cbProduct2:
                    selectedProduct2PriceDetails();
                    break;
                default:
                    break;
            }

        }
    };

    private void selectedProduct1PriceDetails() {

        if (mCbProduct1.isChecked()) {
            toastText = qty1 + " " + mProductName1.getText().toString() + " has been added to cart.";
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
            totalOfProduct1Price(qty1, price1);
            mTotalPriceProduct1LinearLayout.setVisibility(View.VISIBLE);

        } else {
            toastText = qty1 + " " + mProductName1.getText().toString() + " has been removed from cart.";
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
            mTotalPriceProduct1LinearLayout.setVisibility(View.GONE);
            totalPrice1 = 0;
        }
        totalOfProductsPrice(totalPrice1, totalPrice2);

    }

    private void selectedProduct2PriceDetails() {
        if (mCbProduct2.isChecked()) {
            toastText = qty2 + " " + mProdcutName2.getText().toString() + " has been added to cart.";
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
            totalOfProduct2Price(qty2, price2);
            mTotalPriceProduct2LinearLayout.setVisibility(View.VISIBLE);


        } else {
            toastText = qty2 + " " + mProdcutName2.getText().toString() + " has been removed from cart.";
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
            mTotalPriceProduct2LinearLayout.setVisibility(View.GONE);
            totalPrice2 = 0;
        }
        totalOfProductsPrice(totalPrice1, totalPrice2);
    }

    private void enableMinusButtons() {
        if (qty1 == 1)
            mMinus1.setEnabled(false);
        else
            mMinus1.setEnabled(true);
        if (qty2 == 1)
            mMinus2.setEnabled(false);
        else
            mMinus2.setEnabled(true);
    }

    private void incrementQtyOfProduct1() {
        Log.e("TAG", "inside incrementQtyOfProduct1");
        qty1 = qty1 + 1;
        Log.e("TAG", "qty1" + qty1);
        mQty1.setText(String.valueOf(qty1));
        enableMinusButtons();
    }

    private void incrementQtyOfProduct2() {
        qty2 = qty2 + 1;
        mQty2.setText(String.valueOf(qty2));
        enableMinusButtons();
    }

    private void decrementQtyOfProduct1() {
        qty1 = qty1 - 1;
        mQty1.setText(String.valueOf(qty1));
        enableMinusButtons();
    }

    private void decrementQtyOfProduct2() {
        qty2 = qty2 - 1;
        mQty2.setText(String.valueOf(qty2));
        enableMinusButtons();
    }


    private void totalOfProduct1Price(int qty1, int price1) {
        totalPrice1 = qty1 * price1;
        mTotalPrice1.setText(String.valueOf(totalPrice1));
    }

    private void totalOfProduct2Price(int qty2, int price2) {
        totalPrice2 = qty2 * price2;
        mTotalPrice2.setText(String.valueOf(totalPrice2));
    }

    private void totalOfProductsPrice(int totalPrice1, int totalPrice2) {

        totalPrice = totalPrice1 + totalPrice2;
        mTotalPrice.setText(String.valueOf(totalPrice));

    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (mCbProduct1.isChecked())
                totalOfProduct1Price(qty1, price1);
            if (mCbProduct2.isChecked())
                totalOfProduct2Price(qty2, price2);
            totalOfProductsPrice(totalPrice1, totalPrice2);

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mCbProduct1.isChecked() && !mCbProduct2.isChecked()) {
                toastText = qty1 + " " + mProductName1.getText().toString() + "has been added in your cart";
                Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
            } else if (mCbProduct2.isChecked() && !mCbProduct1.isChecked()) {
                toastText = qty2 + " " + mProdcutName2.getText().toString() + "has been added in your cart";
                Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
            } else if (mCbProduct2.isChecked() && mCbProduct1.isChecked()) {
                toastText = qty1 + qty2 + "products has been added in your cart";
                Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
            }

        }
    };


}
