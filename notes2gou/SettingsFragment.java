package com.ivanfrescas.notes2gou;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

ImageButton btnT,btnF,btnI;
    public SettingsFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,


                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btnF=view.findViewById(R.id.btn_facebook);
        btnI=view.findViewById(R.id.btn_instagram);
        btnT=view.findViewById(R.id.btn_twitter);

        // Inflate the layout for this fragment


        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri3 = Uri.parse("https://www.instagram.com/rogelio_s_/");
                Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(intent3);

            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://www.facebook.com/sebastian.rodriguezalarcon");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri2 = Uri.parse("https://twitter.com/IvanFrescas");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(intent2);
            }
        });

        return view;


    }

   

}
