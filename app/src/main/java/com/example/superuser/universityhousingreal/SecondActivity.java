package com.example.superuser.universityhousingreal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


public class SecondActivity extends Activity {
    String UniversityID=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            UniversityID = extras.getString("UniversityID");
        }
        Bundle anotherActivity= new Bundle();
        anotherActivity.putString("UniversityID",UniversityID);


        PlaceholderFragment fragment1=new PlaceholderFragment();
        fragment1.setArguments(anotherActivity);

        if (savedInstanceState == null) {
           FragmentManager FM= getFragmentManager();

            FragmentTransaction FT=  FM.beginTransaction();
                   FT .add(R.id.Container_One,fragment1);
                       //    FT.add(R.id.Container_Two, fragmenttwo);
                   FT.commit();

        }

    }




    public static class PlaceholderFragment extends Fragment {

        //public static Button b;
        String UniversityID = null;


        public PlaceholderFragment() {
            try {


            }
            catch(Exception E)
            {
                E.printStackTrace();
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View rootView = inflater.inflate(R.layout.university_housing_info, container, false);
            TextView textView = (TextView)rootView.findViewById(R.id.txt_housing_home);
            Bundle entities=getActivity().getIntent().getExtras();
            UniversityID = entities.getString("UniversityID");

            String information_housing="Welcome to Life Well Lived!\n\n"+
                    "Living on campus is a great way for you to get connected, meet friends, and be involved. To that end, University Housing offers a wide array of housing options designed to meet your needs and provides an environment that supports academic growth and community respect by offering opportunities for leadership, involvement, and connections for residents that live it up on campus.\n\n"+
                    "We offer two styles of community living: residence halls and apartment communities.\n\n"+
                    "Residence halls provide an opportunity for you to meet people and get involved in a close-knit community that combines all the comforts of home with all the excitement of the traditional college experience.\n\n"+
                    "Apartments provide an opportunity for you to live a more autonomous lifestyle with the opportunity to still be involved in campus life, stay connected to campus resources and the apartment community while living only minutes from your classes. Our apartment month-to-month leases offer you the flexibility to fit your housing needs while completing your academic career.\n\n";
            textView.setText(information_housing);



           TextView txt_details =(TextView)rootView.findViewById(R.id.details_click);
            txt_details.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(rootView.getContext(), third_detals_screen.class);
                    intent.putExtra("UniversityID",UniversityID);
                    startActivity(intent);
                }
            });
            Button b2 = (Button) rootView.findViewById(R.id.Submit_Application);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(rootView.getContext(),application_status_activity.class);
                    intent.putExtra("UniversityID",UniversityID);
                    startActivity(intent);
                }


            });

            Button b3 = (Button) rootView.findViewById(R.id.Btn_App_status);
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(rootView.getContext(), Submit_Application_housing.class);
                    intent.putExtra("UniversityID",UniversityID);
                    startActivity(intent);
                }


            });
            return rootView;
        }


    }

}
