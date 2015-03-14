package sample;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by yaroleogich on 15.03.2015.
 */

public class Main extends Activity {

    private Company mCompany;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompany = new Company(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mCompany.save(this);
        } catch (Exception e) { /*This guys won't meet again*/}
    }
}
