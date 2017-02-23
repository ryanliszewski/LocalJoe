package com.singhliszewski.eventbrowser;

import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by singh on 11/27/16.
 */

public class Story {

    private UUID mUUID;
    private String mName;
    private String mDescription;
    private ImageView mImage;

    public Story(String mName, String mDescription) {
        mUUID = UUID.randomUUID();
        this.mName = mName;
        this.mDescription = mDescription;
    }


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setImage(ImageView cover) {
        mImage = cover;
    }

    public ImageView getImage() {return  mImage;}
}
