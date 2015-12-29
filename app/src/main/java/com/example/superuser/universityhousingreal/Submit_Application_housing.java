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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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


public class Submit_Application_housing extends Activity implements AdapterView.OnItemSelectedListener {
String UniversityID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            UniversityID=extras.getString("UniversityID");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__application_housing);
        Spinner type_housing= (Spinner) findViewById(R.id.type_housing);
        Spinner type_Apartments=(Spinner)findViewById(R.id.type_Apartments);
        Spinner type_Residence_Hall=(Spinner)findViewById(R.id.type_residence_hall);
        ArrayAdapter Adapter_type_housing= ArrayAdapter.createFromResource(this, R.array.type_housing_options,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter Adapter_type_housing1= ArrayAdapter.createFromResource(this, R.array.type_Apartments_options,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter Adapter_type_housing2= ArrayAdapter.createFromResource(this, R.array.type_residence_hall_options,android.R.layout.simple_spinner_dropdown_item);
        type_housing.setAdapter(Adapter_type_housing);
        type_Apartments.setAdapter(Adapter_type_housing1);
        type_Residence_Hall.setAdapter(Adapter_type_housing2);
        type_housing.setOnItemSelectedListener(this);
        type_Apartments.setOnItemSelectedListener(this);
        type_Residence_Hall.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getSelectedItem().toString();

        if(text.equals("Residence Halls"))
        {
            TextView txt_view_type_hall=(TextView) findViewById(R.id.txt_view_Residence_halls);
            Spinner optn_residence_hall=(Spinner) findViewById(R.id.type_residence_hall);
            if(txt_view_type_hall.getVisibility()==View.GONE)
            {
                findViewById(R.id.type_Apartments).setVisibility(View.GONE);
                findViewById(R.id.txt_view_Apartments).setVisibility(View.GONE);
                txt_view_type_hall.setVisibility(View.VISIBLE);
                optn_residence_hall.setVisibility(View.VISIBLE);
            }




         //   Button btn_get_info=(Button) findViewById(R.id.btn_load_housing_info);
         //   btn_get_info.setVisibility(View.VISIBLE);
        }
        else if ("Apartments".equals(text)) {
            TextView txt_view_type_Apartment=(TextView) findViewById(R.id.txt_view_Apartments);
            Spinner optn_Apartment=(Spinner) findViewById(R.id.type_Apartments);
            if(txt_view_type_Apartment.getVisibility()==View.GONE)
            {
                findViewById(R.id.txt_view_Residence_halls).setVisibility(View.GONE);
                findViewById(R.id.type_residence_hall).setVisibility(View.GONE);
                txt_view_type_Apartment.setVisibility(View.VISIBLE);
                optn_Apartment.setVisibility(View.VISIBLE);
            }
          //  Button btn_get_info=(Button) findViewById(R.id.btn_load_housing_info);
         //   btn_get_info.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Arlington Hall"))
        {
            Toast.makeText(this, "Arlington Hall", Toast.LENGTH_LONG).show();
        }
        else if(text.equals("KC Hall"))
        {
            Toast.makeText(this,"Arlington Hall",Toast.LENGTH_LONG).show();
        }
        else if(text.equals("Meadow Run"))
        {
            Toast.makeText(this,"Arlington Hall",Toast.LENGTH_LONG).show();
        }
        else if(text.equals("Timber Brook"))
        {
            Toast.makeText(this,"Arlington Hall",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Submit_Application_Btn(View V)
    {
        Spinner type_housing_Spinner=(Spinner)findViewById(R.id.type_housing);
        String Selected_housing_type=type_housing_Spinner.getSelectedItem().toString();
        String Selected_value=null;
        if(Selected_housing_type.equals("Residence Halls"))
        {
            Spinner type_Residence_Hall_spinner=(Spinner)findViewById(R.id.type_residence_hall);
             Selected_value =type_Residence_Hall_spinner.getSelectedItem().toString();
        }
        else if(Selected_housing_type.equals("Apartments"))
        {
            Spinner type_Apartments_Spinner=(Spinner)findViewById(R.id.type_Apartments);
             Selected_value=type_Apartments_Spinner.getSelectedItem().toString();
        }


        String urlPath = "";
        URI uri = null;
        try {
            uri = new URI("http", "38640e55.ngrok.com", "/UTAHousing/NewServlet",
                    "UniversityID=" + UniversityID + "&TypeHousing=" + Selected_housing_type +  "&SettlementName="+ Selected_value + "&CallType=submit" , null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            urlPath = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        GetStringFromUrl url = new GetStringFromUrl(getBaseContext());
        // String URLString=convertURL(urlPath);
        url.execute(uri.toASCIIString());

    }
    class GetStringFromUrl extends AsyncTask<String, Void, String> {
        String result;
        String UniversityID=null;
        ProgressDialog dialog;
        Context con;

        public GetStringFromUrl(Context baseContext) {
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

            Toast.makeText(getBaseContext(),"Application Submitted, ApplicationID is "+ result +"",Toast.LENGTH_LONG).show();
        }
    }
}
