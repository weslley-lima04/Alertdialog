package com.exemplolivro.dialogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    EditText userEmail, userSenha;
    Button btn_alert;
    AlertDialog alertDialog;
    View view;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEmail = findViewById(R.id.user_email);
        userSenha = findViewById(R.id.user_senha);
        view = findViewById(R.id.activity_main);
        mAuth = FirebaseAuth.getInstance();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("Deseja mesmo se cadastrar?"); //mensagem

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
                   Snackbar snackbar = Snackbar.make(view, "E-mail ou senha vazios!", Snackbar.LENGTH_LONG);
                   snackbar.setBackgroundTint(Color.rgb(202, 103, 102));
                   snackbar.show();
               }
               else
               {
                    cadastrar(email, senha);
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
    private void cadastrar(String emailUser, String senhaUser)
    {
        mAuth.createUserWithEmailAndPassword(emailUser, senhaUser).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Snackbar snackbar = Snackbar.make(view, "Cadastro realizado com sucesso!", Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.rgb(255, 173, 0));
                    snackbar.show();
                    userEmail.setText("");
                    userSenha.setText("");
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Cadastro falhou...", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}