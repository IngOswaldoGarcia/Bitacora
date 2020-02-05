package com.randomgames.shino.bitacora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalScreen extends AppCompatActivity {

    Button toDispIntermerdios;
    Button toDispFinales;
    Button toConexiones;
    Button toPersonal;
    Button toReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_screen);

        ConexionSQLiteHelper connDipsInter=new ConexionSQLiteHelper(this,"bd_dispositivosIntermedios",null,1);

        toDispIntermerdios = (Button) findViewById(R.id.dispIntermediosButton);
        toDispFinales = (Button) findViewById(R.id.dispFinalesButton);
        toConexiones = (Button) findViewById(R.id.conexionesButton);
        toPersonal = (Button) findViewById(R.id.personalButton);
        toReportes = (Button) findViewById(R.id.reportesButton);

        toDispIntermerdios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toDispositivosIntermediosView = new Intent(PrincipalScreen.this, DispositivosIntermedios.class);
                    startActivity(toDispositivosIntermediosView);
                }
        });
        toDispFinales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDispositivosFinalesView = new Intent(PrincipalScreen.this, DispositivosFinales.class);
                startActivity(toDispositivosFinalesView);
            }
        });

        toConexiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toConexionesView = new Intent(PrincipalScreen.this, Conexiones.class);
                startActivity(toConexionesView);
            }
        });

        toPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPersonalView = new Intent(PrincipalScreen.this, Personal.class);
                startActivity(toPersonalView);
            }
        });

        toReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toReportesView = new Intent(PrincipalScreen.this, Reportes.class);
                startActivity(toReportesView);
            }
        });

    }

}
