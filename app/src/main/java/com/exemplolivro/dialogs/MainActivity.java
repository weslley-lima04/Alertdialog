package com.exemplolivro.dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    Button btn_alert;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja mesmo apertar esse botão?"); //mensagem

        //se sim
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Você apertou sim.", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        //se não
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Você disse não.", Toast.LENGTH_LONG);
                toast.show();
            }
        });


        alertDialog = builder.create();
        btn_alert = findViewById(R.id.btnAlert);
        btn_alert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                alertDialog.show();
            }
        });

    }
}