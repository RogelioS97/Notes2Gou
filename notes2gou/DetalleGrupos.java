package com.ivanfrescas.notes2gou;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ivanfrescas.notes2gou.entidades.Grupos;

public class DetalleGrupos extends AppCompatActivity {

    TextView nombre,profesor,correo,pagina;
    ImageView ivweb,ivmail,ivnombre,ivprofesor;

    String page,mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_grupos);

        nombre = findViewById(R.id.tvnombre);
        profesor = findViewById(R.id.tvprofesor);
        correo = findViewById(R.id.tvcorreo);
        pagina = findViewById(R.id.tvpagina);

        ivweb = findViewById(R.id.ivpagina);
        ivmail = findViewById(R.id.ivcorreo);
        ivnombre = findViewById(R.id.ivnombre);
        ivprofesor = findViewById(R.id.ivprofesor);

        ivweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (page.equals("http://Sin pagina web")){
                    Toast.makeText(DetalleGrupos.this, "No existe pagina web para esta materia",Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = Uri.parse(page);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

            }
        });

        ivmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.equals("Sin correo electronico")){
                    Toast.makeText(DetalleGrupos.this, "No existe correo para esta materia",Toast.LENGTH_SHORT).show();
                }else {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto: " + mail));
                    startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                }

            }
        });

        ivnombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalleGrupos.this, "Este icono no tiene funcion =)",Toast.LENGTH_SHORT).show();
            }
        });

        ivprofesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalleGrupos.this, ":D",Toast.LENGTH_SHORT).show();
            }
        });



        Bundle objetoEnviado = getIntent().getExtras();
        Grupos group = null;

        if(objetoEnviado != null) {
            group = (Grupos) objetoEnviado.getSerializable("grupo");
            nombre.setText(group.getNombre().toString());
            profesor.setText(group.getProfesor().toString());
            correo.setText(group.getCorreo().toString());
            pagina.setText(group.getPaginaweb().toString());

            page = "http://" + group.getCorreo().toString();                    //Error en base de datos correo = pagina web
            mail = group.getPaginaweb().toString();
        }




    }



}
