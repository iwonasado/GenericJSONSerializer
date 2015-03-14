package sample;

import com.yarolegovich.genericjsonserializer.JSONSerializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yarolegovich on 15.03.2015.
 */

public class Dude implements JSONSerializable{

    private static final String NAME_TAG = "name";
    private static final String AGE_TAG = "age";

    private String mName;
    private int mAge;

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NAME_TAG, mName);
        jsonObject.put(AGE_TAG, mAge);
        return jsonObject;
    }

    @Override
    public void initWithJSON(JSONObject object) throws JSONException {
        mName = object.getString(NAME_TAG);
        mAge = object.getInt(AGE_TAG);
    }
}
