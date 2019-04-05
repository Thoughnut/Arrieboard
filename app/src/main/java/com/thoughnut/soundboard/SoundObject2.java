package com.thoughnut.soundboard;

import android.net.Uri;

class SoundObject2 {
    private String itemName;
    private Uri itemID;
    private String itemPath;


    public SoundObject2(String itemName, Uri itemID, String itemPath){

        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPath = itemPath;
    }

    public String getItemName(){

        return itemName;
    }

    public Uri getItemID(){

        return itemID;
    }

    public String getFilePath(){
        return itemPath;
    }

}
