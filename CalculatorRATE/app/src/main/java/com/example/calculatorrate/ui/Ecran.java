package com.example.calculatorrate.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculatorrate.CustomListAdapter;
import com.example.calculatorrate.HttpRequestJson;
import com.example.calculatorrate.Program;
import com.example.calculatorrate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ecran  extends Fragment {

    private ListView lista;
    private CustomListAdapter customListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View  root =  inflater.inflate(R.layout.fragment_ecran,container,false);


        lista = (ListView) root.findViewById(R.id.lista);


        ArrayList<Program> arrayList = new ArrayList<>();

        HttpRequestJson httpRequestJson = new HttpRequestJson(){
            @Override
            protected void onPostExecute(String s) {

                //Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();

                try{

                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("Program");


                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);

                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Program", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        Program p = new Program();
                        p.setManager(obj.getString("manager"));
                        p.setOra(obj.getString("ora"));
                        p.setSucursala(obj.getString("sucursala"));


                        editor.putString("manager",obj.getString("manager"));
                        editor.putString("ora",obj.getString("ora"));
                        editor.putString("sucursala",obj.getString("sucursala"));

                        editor.commit();



                        arrayList.add(p);
                        customListAdapter = new CustomListAdapter(arrayList,getActivity());
                        lista.setAdapter(customListAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                super.onPostExecute(s);
            }
        };
        httpRequestJson.execute("https://pastebin.com/raw/YRPif2jY");

        SharedPreferences prefs = getActivity().getSharedPreferences("Program",Context.MODE_PRIVATE);
        String ora = prefs.getString("ora",null);
        String manager = prefs.getString("manager",null);
        String sucursala = prefs.getString("sucursala",null);

        Toast.makeText(getActivity(),"Citire din fisierul SharedPreferences: ---> "+ora+" \n "+manager+" \n "+sucursala,Toast.LENGTH_LONG).show();

        return root;



    }
}
