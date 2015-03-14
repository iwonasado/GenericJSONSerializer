package com.yarolegovich.genericjsonserializer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yarolegovich on 14.03.2015.
 */

public interface JSONSerializable {
    JSONObject toJSON() throws JSONException;
    void initWithJSON(JSONObject object) throws JSONException;
}
