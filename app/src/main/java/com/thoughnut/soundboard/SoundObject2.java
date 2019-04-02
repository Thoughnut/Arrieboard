package com.thoughnut.soundboard;

import android.net.Uri;

class SoundObject2 {
    private String itemName;
    private Uri itemID;


    public SoundObject2(String itemName, Uri itemID){

        this.itemName = itemName;
        this.itemID = itemID;
    }

    public String getItemName(){

        return itemName;
    }

    public Uri getItemID(){

        return itemID;
    }

}
