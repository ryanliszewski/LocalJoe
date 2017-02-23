package com.singhliszewski.eventbrowser;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Ryanliszewski on 11/30/16.
 */

public class CardListActivity extends SingleFragmentActivity  {


    @Override
    protected Fragment createFragment() {

        return new CardListFragment();
    }
}
