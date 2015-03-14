package sample;

import android.content.Context;

import com.yarolegovich.genericjsonserializer.GenericJSONSerializer;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yarolegovich on 15.03.2015.
 */
 
public class Company {

    private static final String PATH = "dudes.json";

    private ArrayList<Dude> mDudes;

    public Company(Context context) {
        try {
            GenericJSONSerializer<Dude> serializer = new GenericJSONSerializer<Dude>(context, Dude.class);
            serializer.setPath(PATH);
            mDudes = serializer.loadData();
        }
        catch (Exception e) {
            mDudes = new ArrayList<>();
        }
    }

    public void save(Context context) throws IOException, JSONException {
        GenericJSONSerializer<Dude> serializer = new GenericJSONSerializer<Dude>(context, PATH, Dude.class);
        serializer.saveData(mDudes);
    }
}
