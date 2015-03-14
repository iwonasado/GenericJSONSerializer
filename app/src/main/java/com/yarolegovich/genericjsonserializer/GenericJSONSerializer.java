package com.yarolegovich.genericjsonserializer;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Ярик on 14.03.2015.
 */

public class GenericJSONSerializer<T extends JSONSerializable> {
    private Context mContext;
    private String mPath;

    private JSONSerializableFactory<T> mObjectFactory;

    public GenericJSONSerializer(Context context, Class classInstance) {
        this(context, "default.json", classInstance);
    }

    public GenericJSONSerializer(Context context, String path, Class classInstance) {
        mContext = context;
        mPath = path;
        mObjectFactory = new JSONSerializableFactory<>(classInstance);
    }

    public ArrayList<T> loadData() throws IOException, JSONException {
        JSONArray jsonArray = getJSONArray();
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            T newItem = mObjectFactory.createInstance();
            newItem.initWithJSON(jsonArray.getJSONObject(i));
            list.add(newItem);
        }
        return list;
    }

    private JSONArray getJSONArray() throws IOException, JSONException {
        JSONArray jsonArray;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mContext.openFileInput(mPath)));
            String line;
            StringBuilder jsonString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
        return jsonArray;
    }

    public void saveData(ArrayList<T> data) throws IOException, JSONException {
        JSONArray array = new JSONArray();
        for (JSONSerializable o : data) {
            array.put(o.toJSON());
        }
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(mContext.openFileOutput(mPath, Context.MODE_PRIVATE));
            writer.write(array.toString());
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public void setPath(String path) {
        mPath = path;
    }

    private static class JSONSerializableFactory<T extends JSONSerializable> {

        public JSONSerializableFactory(Class classInstance) {
            mClassInstance = classInstance;
        }

        private Class mClassInstance;

        public T createInstance() {
            try {
                return (T) mClassInstance.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}