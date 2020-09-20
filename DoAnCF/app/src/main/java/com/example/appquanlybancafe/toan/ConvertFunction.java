package com.example.appquanlybancafe.toan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertFunction {

    public ConvertFunction() {
    }

    public static Bitmap convertStringToBitmapFromAcccess(Context context, String filename) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public String dateString(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(dateStr);
        String formattedTime = output.format(d);
        return  formattedTime;
    }

    public String dateStringDMY(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(dateStr);
        String formattedTime = output.format(d);
        return  formattedTime;
    }
}
