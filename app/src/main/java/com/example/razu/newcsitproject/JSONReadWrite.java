package com.example.razu.newcsitproject;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Project Name: NayaShakti
 * Copyright: @Kulchan Pvt. Ldt.
 * Author : syangden
 * Date Created: 6/8/16
 * Class Description: {Replace this by your class description}
 */
public class JSONReadWrite  {

    private Context context;
    public JSONReadWrite(Context context)
    {
        this.context = context;
    }

    public void writeFile(String filename, JSONObject object)
    {
        try
        {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(object.toString().getBytes());
            fos.close();
            Log.v("Read", "Writing the file successful");
        }catch (FileNotFoundException e )
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }catch (Exception e)
        {
           e.printStackTrace();
        }


    }

    public JSONObject readFile(String filename)
    {
        try {
            FileInputStream fis = context.openFileInput(filename);


            StringBuilder builder = new StringBuilder();
            int ch;
            while((ch = fis.read()) != -1){
                builder.append((char)ch);
            }


            String result = builder.toString();
            fis.close();
            return  new JSONObject(result);


        }catch (FileNotFoundException e )
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
