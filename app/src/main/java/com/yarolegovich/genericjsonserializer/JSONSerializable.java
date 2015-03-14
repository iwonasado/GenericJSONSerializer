package com.yarolegovich.genericjsonserializer;

import org.json.JSONObject;

/**
 * Created by Ярик on 14.03.2015.
 */
public interface JSONSerializable {
    JSONObject toJSON();
    void initWithJSON(JSONObject object);
}
