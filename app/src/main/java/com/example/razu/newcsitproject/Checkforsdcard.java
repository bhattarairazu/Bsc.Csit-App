package com.example.razu.newcsitproject;

import android.os.Environment;

/**
 * Created by Razu on 12/5/2017.
 */

public class Checkforsdcard {
public boolean sdcardispresent(){
    if(Environment.getExternalStorageState().equals(
            Environment.MEDIA_MOUNTED
    )){
        return true;
    }
    return false;

}

}
