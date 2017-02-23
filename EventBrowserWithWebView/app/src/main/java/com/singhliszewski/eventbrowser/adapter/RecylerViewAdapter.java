package com.singhliszewski.eventbrowser.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.singhliszewski.eventbrowser.CardActivity;
import com.singhliszewski.eventbrowser.R;
import com.singhliszewski.eventbrowser.app.App;
import com.singhliszewski.eventbrowser.model.Business;
import com.singhliszewski.eventbrowser.volley.VolleySingleton;

import org.w3c.dom.Text;

import java.sql.Types;
import java.util.List;

/**
 * Created by Ryanliszewski on 12/13/16.
 */

public class RecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Business> bussinessList;
    private OnClickListener mListener;


    public RecylerViewAdapter(List<Business> businessList){
        this.bussinessList = businessList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .card_fragment, parent, false);
        return new CardViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Business b = bussinessList.get(position);

        CardViewHolder cardViewHolder = (CardViewHolder) holder;
        cardViewHolder.setName(b.getName());
        cardViewHolder.setPhoneNumber(b.getPhone());
        cardViewHolder.setAddress(b.getAddress());
        cardViewHolder.setImageUrl(b.getImageUrl());
        cardViewHolder.setRating(Float.parseFloat(b.getRating()));
        if(mListener != null){
            cardViewHolder.bindClickListener(mListener, b);
        }
    }

    @Override
    public int getItemCount() {
        return bussinessList.size();
    }

    public void updateDataSet(List<Business> businessList){
        this.bussinessList.clear();
        this.bussinessList.addAll(businessList);
        notifyDataSetChanged();
    }

    public void setListener(OnClickListener listener){
        this.mListener = listener;
    }

    public interface OnClickListener{
        void onCardClick(Business b);
        void onPosterClikc(Business b);
    }
    private class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView mName;
        private TextView mPhoneNumber;
        private TextView mAddress;
        private NetworkImageView mNivImage;
        private RatingBar mRating;



        public CardViewHolder(View view) {
            super(view);
            this.mCardView = (CardView) view.findViewById(R.id.card_view);
            this.mName = (TextView) view.findViewById(R.id.name);
            this.mPhoneNumber = (TextView) view.findViewById(R.id.phoneNumber);
            this.mAddress = (TextView) view.findViewById(R.id.address);
            this.mNivImage = (NetworkImageView) view.findViewById(R.id.nivImage);
            this.mRating = (RatingBar) view.findViewById(R.id.ratingBar);
        }

        void setName(String name) {
            this.mName.setText(name);
        }

        void setPhoneNumber(String number) {
            this.mPhoneNumber.setText(number);
        }

        void setAddress(String address) {
            this.mAddress.setText(address);
        }

        void setImageUrl(String imageUrl) {
            ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
            this.mNivImage.setImageUrl(imageUrl, imageLoader);
        }
        void setRating(Float rating) {
            mRating.setRating(rating);
            //mRating.setEnabled(false);
            //mRating.setMax(5);
            //mRating.setStepSize(0.5f);
        }

        void bindClickListener(final OnClickListener listener, final Business b) {
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardClick(b);
                }
            });
        }
    }

}
