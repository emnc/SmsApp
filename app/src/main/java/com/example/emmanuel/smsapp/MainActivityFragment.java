package com.example.emmanuel.smsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.telephony.SmsManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //setContentView(R.layout.con);

        final Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String message = prefs.getString(getString(R.string.texto), getString(R.string.pref_texto));
                //sms intent
                String aux = prefs.getString(getString(R.string.cantidad),getString(R.string.pref_cantidad));
                int nMensajes;
                try {
                    nMensajes = Integer.parseInt(aux);
                } catch (NumberFormatException e) {
                    Log.e("SMS", aux+": ", e);
                    nMensajes = 0;
                }
                Log.e("SMS", aux + ": ");
                aux = prefs.getString(getString(R.string.numero), getString(R.string.pref_numero));

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getActivity(),"", duration);


                for (int i = 0; i < nMensajes; i++) {
/*
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("smsto:"));  // This ensures only SMS apps respond
                    intent.putExtra("sms_body", message);
                    intent.putExtra(Intent.EXTRA_STREAM, "hola :P");
                    startActivity(intent);
                    */

                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(aux, null, message, null, null);
                        Toast.makeText(getActivity(), "SMS Sent!",
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(),
                                "SMS failed, please try again later!",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    toast.setText(Integer.toString(i+1));
                    toast.show();


/*
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.toString());
                    }*/
                }
            }
        });
        return rootView;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Context context = getActivity();
            Intent pageIntent = new Intent(context,SettingsActivity.class);
            pageIntent.putExtra(Intent.EXTRA_TEXT, "Hola :P");
            startActivity(pageIntent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void enviarMensajes(){
        String message = getString(R.string.texto, R.string.pref_texto);
        //sms intent
        String cant = getString(R.string.texto, R.string.pref_texto);
        //int nMensajes =new Integer(cant);
        //for(int i=0;i<){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:"));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        intent.putExtra(Intent.EXTRA_STREAM, "hola :P");
        startActivity(intent);

    }

}
