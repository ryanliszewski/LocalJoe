package com.singhliszewski.eventbrowser;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.singhliszewski.eventbrowser.model.Business;

import java.util.UUID;

public class CardActivity extends SingleFragmentActivity {

    public static final String EXTRA_BUSINESS_ID =
            "com.singhliszewski.eventbrowser.business_id";

    public static Intent newIntent(Context packageContext, Business business){
        Intent intent = new Intent(packageContext, CardActivity.class);
        intent.putExtra(EXTRA_BUSINESS_ID, business);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Business business = (Business) getIntent().getSerializableExtra
                (EXTRA_BUSINESS_ID);
        return CardFragment.newInstance(business);
    }
}
