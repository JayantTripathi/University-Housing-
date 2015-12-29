package com.example.superuser.universityhousingreal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void LoginBtnClick(View view) {

        EditText Username = (EditText) findViewById(R.id.edtTxtStdID);
                EditText Password = (EditText) findViewById(R.id.edtTxtPass);
        String un = Username.getText().toString();
        String pass = Password.getText().toString();
        String urlPath = "";
        URI uri = null;
        try {
            uri = new URI("http", "38640e55.ngrok.com", "/UTAHousing/NewServlet",
                    "Username=" + un + "&Password=" + pass + "&CallType=Login", null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            urlPath = "http://6879c2a0.ngrok.com/UTAHousing/NewServlet?" +
                    "Username=\"" + un +
                    "\"&Password=\"" + pass + "\"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        GetStringFromUrl url = new GetStringFromUrl(getBaseContext(),un.toString());
        // String URLString=convertURL(urlPath);
        url.execute(uri.toASCIIString());

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

class GetStringFromUrl extends AsyncTask<String, Void, String> {
    String result;
    String UniversityID=null;
    ProgressDialog dialog;
    Context con;

    public GetStringFromUrl(Context baseContext, String StudentID) {
        con = baseContext;
        UniversityID= StudentID;
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

        //\
      //  String lelo=result.toString();
        if (result.equals("true"))
        {

            Intent intent = new Intent(con, SecondActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("UniversityID",UniversityID);
            con.startActivity(intent);

        }
        else
        {
          //  Toast.makeText(con,lelo, Toast.LENGTH_LONG).show();
            Toast.makeText(con, "Login Failed", Toast.LENGTH_LONG).show();
        }


    }
}