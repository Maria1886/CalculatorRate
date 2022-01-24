package com.example.calculatorrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Inregistrare extends AppCompatActivity {


    private Button btnInregistrare;
    private EditText edtAdresaEmail,edtParola,edtNume;
    private CheckBox chk18;
    private DatabaseSQLIte databaseSQLIte;
    private static byte[] image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        btnInregistrare = (Button)findViewById(R.id.btnInregistrare);
        edtAdresaEmail = (EditText)findViewById(R.id.edtAdresaEmail);
        edtParola = (EditText)findViewById(R.id.edtParola);
        edtNume = (EditText)findViewById(R.id.edtNume);
        chk18 = (CheckBox)findViewById(R.id.chk18);

        databaseSQLIte = new DatabaseSQLIte(this);

        btnInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chk18.isChecked() == true) {



                    String adresaEmail = edtAdresaEmail.getText().toString();
                    String parola = edtParola.getText().toString();
                    String nume = edtNume.getText().toString();

                    FileInputStream fileInputStream = null;
                    try{
                        fileInputStream = new FileInputStream("/sdcard/Pictures/bd.jpg");
                        image = new byte[fileInputStream.available()];
                        fileInputStream.read(image);

                    }catch (FileNotFoundException fileNotFoundException){
                        fileNotFoundException.printStackTrace();
                    }
                    catch (IOException ioException){
                        ioException.printStackTrace();
                    }



                    databaseSQLIte.adaugareUtilizator(new Utilizator(adresaEmail,parola,nume,image));

                    Toast.makeText(getApplicationContext(),"Inregistrare completa",Toast.LENGTH_LONG).show();




//                    String adresaEmail = edtAdresaEmail.getText().toString();
//                    String parola = edtParola.getText().toString();
//                    String nume = edtNume.getText().toString();
//
//
//                    UtilizatoriDB utilizatoriDB = UtilizatoriDB.getInstance(getApplicationContext());
//                    utilizatoriDB.getUtilizatorDao().insertUtilizator(new Utilizator(adresaEmail, parola, nume));
//                    Toast.makeText(getApplicationContext(), "Inregistrare cu succces!", Toast.LENGTH_LONG).show();


                }


            }


        });

    }



}