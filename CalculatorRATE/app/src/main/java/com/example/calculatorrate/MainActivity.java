package com.example.calculatorrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnInregistrare, btnAutentificare;
    private EditText edtParola, edtAdresaEmail;
    private DatabaseSQLIte databaseSQLIte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAutentificare = (Button)findViewById(R.id.btnAutentificare);
        btnInregistrare = (Button)findViewById(R.id.btnInregistrare);
        edtAdresaEmail = (EditText) findViewById(R.id.edtAdresaEmail);
        edtParola = (EditText)findViewById(R.id.edtParola);
        databaseSQLIte = new DatabaseSQLIte(this);



        btnInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Inregistrare.class);
                startActivity(i);

            }
        });


        btnAutentificare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtAdresaEmail.getText().toString();
                String parola = edtParola.getText().toString();


              Utilizator utilizator =  databaseSQLIte.autentificareUtilizator(new Utilizator(email,parola));


                if(utilizator != null){
                    Intent i = new Intent(MainActivity.this,MenuCalculatorRate.class);
                    i.putExtra("Email",email);
                    startActivity(i);
                }











//                UtilizatoriDB utilizatoriDB = UtilizatoriDB.getInstance(getApplicationContext());
//
//                List<Utilizator> utilizatoriLista = utilizatoriDB.getUtilizatorDao().autentificare(email,parola);
//                if(utilizatoriLista.isEmpty() == false){
//
//                    Intent i = new Intent(MainActivity.this,MenuCalculatorRate.class);
//                    i.putExtra("Email",email);
//                    startActivity(i);
//
//                    Toast.makeText(getApplicationContext(),"Autentificare cu succes!",Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(getApplicationContext(),"Autentificare fara succes!",Toast.LENGTH_LONG).show();
//
//                }
            }
        });





    }
}