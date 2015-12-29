package com.example.superuser.universityhousingreal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class application_status_activity extends Activity {
String UniversityID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_status_activity);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            UniversityID = extras.getString("UniversityID");
        }

    }

    public void ApplicationStatusBtnClick(View view)
    {
        EditText ApplicationIDedttxt = (EditText) findViewById(R.id.edtTxtAppID);
        String ApplicationID = ApplicationIDedttxt.getText().toString();
        String un="Status";
        String urlPath = "";
        URI uri = null;
        try {
            uri = new URI("http", "38640e55.ngrok.com", "/UTAHousing/NewServlet",
                    "CallType=" + un + "&ApplicationID=" + ApplicationID , null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            urlPath = "http://6879c2a0.ngrok.com/UTAHousing/NewServlet?" +
                    "CallType=\"" + un +
                    "\"&ApplicationID=\"" + ApplicationID + "\"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        GetStringFromUrl2 url = new GetStringFromUrl2(getBaseContext());
        // String URLString=convertURL(urlPath);
        url.execute(uri.toASCIIString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_application_status_activity, menu);
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
    class GetStringFromUrl2 extends AsyncTask<String, Void, String> {
        String result;
        String UniversityID;
        ProgressDialog dialog;
        Context con;

        public GetStringFromUrl2(Context baseContext) {
            con = baseContext;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog when downloading
            // dialog = ProgressDialog.show(con, null, "Downloading...");
        }

        @Override
        protected String doInBackground(String... params) {


            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(params[0]);

                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();

                BufferedHttpEntity buf = new BufferedHttpEntity(entity);

                InputStream is = buf.getContent();

                BufferedReader r = new BufferedReader(new InputStreamReader(is));

                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                result = total.toString();

                //Log.i("Get URL", "Downloaded string: " + result);

            }
            catch (Exception e) {
                Log.e("Get Url", "Error in downloading: " + e.toString());
                result="null";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            TextView txtvw_status=(TextView)findViewById(R.id.txt_view_Status_info);
            txtvw_status.setText(result);



        }
    }
}
