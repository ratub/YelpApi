package com.ratus.yelpapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ratus.yelpapi.models.BusinessArrayAdapter;
import com.ratus.yelpapi.models.Businesses;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class YelpActivity extends ActionBarActivity {

    private ArrayList<Businesses> businesseses;
    private BusinessArrayAdapter aBusinesseses;
    private ListView lvBusinesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp);

        // find the lsit view
        lvBusinesses = (ListView) findViewById(R.id.lvBusinesses);

        // Create the arrayList
        businesseses = new ArrayList<>();

        // Construct the adapter
        aBusinesseses = new BusinessArrayAdapter(this, businesseses);

        // Connect listview to adapter
        lvBusinesses.setAdapter(aBusinesseses);

        populateBusinesses();

    }


    // Send
    private void populateBusinesses(){
        new ReadYelpJSONFeedTask().execute(
                "http://api.yelp.com/v2/search");

    }


    public static String readJSONFeed(String URL) {
        String response=null;
        try {
            YelpConfig api_keys = new YelpConfig();

            Yelp yelp = new Yelp(api_keys.getYelpConsumerKey(), api_keys.getYelpConsumerSecret(),
                    api_keys.getYelpToken(), api_keys.getYelpTokenSecret());
            response = yelp.search("burritos", 30.361471, -87.164326);
            Log.v("response ", response);
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return response;
    }

    private class ReadYelpJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            Log.v("Result ", result);
            JSONArray businesses=null;

            YelpParser yParser = new YelpParser();
            yParser.setResponse(result);
            try {
                businesses= yParser.parseBusiness();


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("onPostExecute", e.getLocalizedMessage());
                //Do whatever you want with the error, like throw a Toast error report
            }
            int i = 0;
            String mobile_url = yParser.getBusinessMobileURL(i);
            String rating_url = yParser.getRatingURL(i);
            String b_name = yParser.getBusinessName(i);
            Bundle tmpBundle = yParser.getYelpBundle();
            ArrayList<String> tmpKeys = yParser.getBundleKeys();

            aBusinesseses.addAll(Businesses.fromJSONArray(businesses));
            Log.d("Debug :", aBusinesseses.toString());
        }


    }    // Async Task


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yelp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
