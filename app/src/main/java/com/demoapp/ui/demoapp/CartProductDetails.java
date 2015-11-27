package com.demoapp.ui.demoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.demoapp.fragments.OmniPayFragment;


public class CartProductDetails extends ActionBarActivity {

    private Button mOmniPayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_product_details);

        initComponents();
        addListeners();
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            switch (viewId){
                case R.id.btnOmniPay:
                    showOmniPayDialog();
                    break;
                default:
                    break;
            }
        }
    };
    private void showOmniPayDialog() {
        OmniPayFragment omnipayFragment = new OmniPayFragment();
        omnipayFragment.show(getSupportFragmentManager(),"fragment_tag");
    }
    private void addListeners() {
        mOmniPayButton.setOnClickListener(mClickListener);
    }

    private void initComponents() {
        mOmniPayButton = (Button) findViewById(R.id.btnOmniPay);
    }

}
