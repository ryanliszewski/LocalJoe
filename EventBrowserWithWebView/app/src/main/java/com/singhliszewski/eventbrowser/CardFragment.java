package com.singhliszewski.eventbrowser;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.singhliszewski.eventbrowser.app.App;
import com.singhliszewski.eventbrowser.model.Business;
import com.singhliszewski.eventbrowser.volley.VolleySingleton;
import com.singhliszewski.eventbrowser.webViewActivity.WebViewActivity;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by Ryanliszewski on 11/30/16.
 */

public class CardFragment extends Fragment implements OnMapReadyCallback{

    private static final String ARG_BUSINESS_ID = "business_id";

    private Business mBusiness;
    private TextView mNameField;
    private NetworkImageView mNetworkImageView;
    private ImageLoader  mImageLoader;
    private Button mURLButton;

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);

    private Marker mBusinessLocation;
    private GoogleMap mMap;

    public static CardFragment newInstance(Business business){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_BUSINESS_ID,  business);

        CardFragment fragment = new CardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        mBusiness = (Business) getArguments().getSerializable(ARG_BUSINESS_ID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_description_fragment,
                container, false);

//        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
//                .findFragmentById(R.id.map);

       // mapFragment.getMapAsync(this);


        mNameField = (TextView) v.findViewById(R.id.name);
        mNameField.setText(mBusiness.getName());


        mURLButton = (Button) v.findViewById(R.id.websit_button);
        mURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("urlString", mBusiness.getUrl());
                Intent intent = new Intent(getActivity(), WebViewActivity
                        .class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        mNetworkImageView= (NetworkImageView) v.findViewById(R.id
                .nivImage_description);
        mImageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
        mNetworkImageView.setImageUrl(mBusiness.getImageUrl(),mImageLoader);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            //map.setMyLocationEnabled(true);
        mMap = googleMap;

        mBusinessLocation = mMap.addMarker(new MarkerOptions().position
                (PERTH).title(mBusiness.getName()));
        mBusinessLocation.setTag(0);

    }
}
