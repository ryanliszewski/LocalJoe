package com.singhliszewski.eventbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.singhliszewski.eventbrowser.adapter.RecylerViewAdapter;
import com.singhliszewski.eventbrowser.controller.JsonController;
import com.singhliszewski.eventbrowser.model.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ryanliszewski on 11/30/16.
 */

public class CardListFragment extends Fragment implements SearchView
        .OnQueryTextListener, RecylerViewAdapter.OnClickListener {

    private RecyclerView mCardRecyclerView;
    private RecylerViewAdapter mAdapter;

    JsonController mController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card_list,container,
                false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mCardRecyclerView = (RecyclerView) view.findViewById(R.id
                .recycler_view);
        mCardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new RecylerViewAdapter(new ArrayList<Business>());
        mAdapter.setListener(this);

        mController = new JsonController(
            new JsonController.OnResponseListener(){

                @Override
                public void onSuccess(List<Business> b) {

                    mCardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mAdapter.updateDataSet(b);
                    mCardRecyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onFailure(String errorMessage) {
                }
            });

        setHasOptionsMenu(true);
        onQueryTextChange("");
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    private void updateUI() {
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_activity_main, menu);
        super.onCreateOptionsMenu(menu,inflater);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Search");
        searchView.setSubmitButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.location_search:
                Intent intent = new Intent(this.getActivity(), LocationService.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mController.cancelAllRequests();
        try {
            mController.sendRequest(query);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mController.cancelAllRequests();
        try{
            mController.sendRequest(newText);
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onCardClick(Business b) {
        Intent intent = CardActivity.newIntent(getActivity(), b);
        startActivity(intent);
    }

    @Override
    public void onPosterClikc(Business b) {

    }
}
