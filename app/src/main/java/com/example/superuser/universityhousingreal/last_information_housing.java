package com.example.superuser.universityhousingreal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class last_information_housing extends Activity {
    String UniversityID;
    String Housing_type;
    String Housinginfo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_information_housing);


        Bundle extras = getIntent().getExtras();
        if(extras!=null){
          //  String _Str = extras.getString(ProjectManager.ID);
            UniversityID=extras.getString("UniversityID");
            String housing_title= extras.getString("ServiceName");
            Housing_type=extras.getString("HousingTypeSelected");
            Toast.makeText(this,housing_title,Toast.LENGTH_LONG).show();
        }

        if (Housing_type.equals("Residence Halls"))
        {
            Housinginfo="Room Type:\n"
                    + "* Three single bedrooms and a shared living room and bathroom area.\n"
                    + "* Double Room, which is one bedroom and bathroom that two residents share.\n\n"
                    + "Room Features:\n"
                    + "* All bedrooms in KC Hall include a twin extra-long bed, a desk and chair, a chest of drawers and a closet.\n"
                    + "* The common spaces in the Private Suites include a couch, a chair, a coffee table, and one end table.\n"
                    + "* Air Conditioning\n"
                    + "* Bicycle Racks\n"
                    + "* Big Screen TV with Cable\n";
        }
        else if(Housing_type.equals("Apartments"))
        {
            Housinginfo="Room Type:\n"
                    + "* 2 Bedroom Hall Kitchen and 2 bathroom areas.\n"
                    + "\n"
                    + "Room Features:\n"
                    + "* All bedrooms  include a twin extra-long bed, a desk and chair, a chest of drawers and a closet.\n"
                    + "* The common spaces in the Private Suites include a couch, a chair, a coffee table, and one end table.\n"
                    + "* Air Conditioning\n"
                    + "* Bicycle Racks\n"
                    + "* Big Screen TV with Cable\n";
        }

        TextView txt_housing_info=(TextView) findViewById(R.id.txt_vw_housing_info);
        txt_housing_info.setText(Housinginfo);
        txt_housing_info.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_last_information_housing, menu);
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
