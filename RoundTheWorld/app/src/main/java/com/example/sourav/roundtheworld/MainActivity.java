// How to get the current GPS location programmatically in Android

package com.example.sourav.roundtheworld;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends Activity {



    private static final int REQUEST_CODE=200;
    //final Context context = MainActivity.this;


    boolean result;

    




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main1);





       // Button button = (Button)findViewById(R.id.mapview);
       // button.setVisibility(View.GONE);




        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,REQUEST_CODE);


        //Toast.makeText(getBaseContext(), "result is "+result, Toast.LENGTH_LONG).show();


    }





    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);


            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            }


        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), activity2.class);
            startActivity(intent);
        }





    }




    public void onRequestPermissionsResult(int request_Code, String permissions[], int[] grantResults) {

        switch (request_Code){

            case REQUEST_CODE:
                if(grantResults.length>0)
                {
                    result=true;
                    //Toast.makeText(getBaseContext(), " inside handling result is "+result, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), activity2.class);
                    startActivity(intent);
                }
                break;
        }
    }






}