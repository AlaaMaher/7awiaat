package com.example.dev.hawaiat.models;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Data {
    private static Data sInstance;
    private Profile mUser;
    private Context mContext;

    private Data(Context context) {
        mContext = context;
        mUser = readObject(context, "user");
        if (null == mUser) {
            clear();
        }
    }

    private static <Y> Y readObject(Context context, String filename) {
        ObjectInputStream objectIn = null;
        Object object = null;
        try {
            FileInputStream fileIn = context.getApplicationContext().openFileInput(filename);
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != objectIn) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            return (Y) object;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Data get(Context context) {
        if (null == sInstance) {
            sInstance = new Data(context);
        }
        return sInstance;
    }

    public Profile getUser() {
        return mUser;
    }

    public void setUser(Profile user) {
        mUser = user;
        save();
    }

    public void clear() {
        mUser = new Profile();
        save();
    }

    public void save() {
        writeObject(mContext, mUser, "user");
    }

    /* Read and write objects. */
    private void writeObject(Context context, Profile object, String filename) {
        ObjectOutputStream objectOut = null;
        try {
            FileOutputStream fileOut = context.openFileOutput(filename, Activity.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != objectOut) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
