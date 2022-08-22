package com.exemplolivro.dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
{

    EditText userEmail, userSenha;
    Button btn_alert;
    AlertDialog alertDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEmail = findViewById(R.id.user_email);
        userSenha = findViewById(R.id.user_senha);
        View view = findViewById(R.id.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("Deseja mesmo ver esse texto?"); //mensagem

        //se sim
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
               String email = userEmail.getText().toString();
               String senha = userSenha.getText().toString();

               if(email.isEmpty() || senha.isEmpty())
               {
                   Snackbar snackbar = Snackbar.make(view, "Email ou senha vazios!", Snackbar.LENGTH_LONG);
                   snackbar.setBackgroundTint(Color.rgb(255, 173, 0));
                   snackbar.show();
               }
               else
               {
                   Toast toast = Toast.makeText(getApplicationContext(), "Login feito com sucesso.", Toast.LENGTH_LONG);
                   toast.show();
               }

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