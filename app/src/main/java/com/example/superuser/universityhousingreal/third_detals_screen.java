package com.example.superuser.universityhousingreal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class third_detals_screen extends Activity implements AdapterView.OnItemSelectedListener{
    String housing_type_selection;
    Spinner type_housing;
    Spinner type_Apartments;
    Spinner type_Residence_Hall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_detals_screen);

        type_housing= (Spinner) findViewById(R.id.type_housing);
        type_Apartments=(Spinner)findViewById(R.id.type_Apartments);
        type_Residence_Hall=(Spinner)findViewById(R.id.type_residence_hall);
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
            txt_view_type_hall.setVisibility(View.VISIBLE);
            Spinner optn_residence_hall=(Spinner) findViewById(R.id.type_residence_hall);
            optn_residence_hall.setVisibility(View.VISIBLE);
            Button btn_get_info=(Button) findViewById(R.id.btn_load_housing_info);
            btn_get_info.setVisibility(View.VISIBLE);
            housing_type_selection = text;
        }
        else if ("Apartments".equals(text)) {
            TextView txt_view_type_Apartment=(TextView) findViewById(R.id.txt_view_Apartments);
            txt_view_type_Apartment.setVisibility(View.VISIBLE);
            TextView txt_view_type_hall=(TextView) findViewById(R.id.txt_view_Residence_halls);
            txt_view_type_hall.setVisibility(View.GONE);
            Spinner optn_residence_hall=(Spinner) findViewById(R.id.type_residence_hall);
            optn_residence_hall.setVisibility(View.GONE);
            Spinner optn_Apartment=(Spinner) findViewById(R.id.type_Apartments);
            optn_Apartment.setVisibility(View.VISIBLE);
            Button btn_get_info=(Button) findViewById(R.id.btn_load_housing_info);
            btn_get_info.setVisibility(View.VISIBLE);
            housing_type_selection = text;
        }
        else if(text.equals("Arlington Hall"))
        {
            housing_type_selection = text;

        }
        else if(text.equals("KC Hall"))
        {
            housing_type_selection = text;

        }
        else if(text.equals("Meadow Run"))
        {
            housing_type_selection = text;

        }
        else if(text.equals("Timber Brook"))
        {
            housing_type_selection = text;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void HousingInfoActivity(View V)
    {

        Spinner Selected_Type_value=(Spinner) findViewById(R.id.type_housing);
        String Selected_Housing_Type=Selected_Type_value.getSelectedItem().toString();

            Intent intent_housing=new Intent(this,last_information_housing.class);
            intent_housing.putExtra("ServiceName",housing_type_selection);
            intent_housing.putExtra("HousingTypeSelected",Selected_Housing_Type);
            startActivity(intent_housing);




    }
}
