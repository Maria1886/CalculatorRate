package com.example.calculatorrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomListAdapter  extends ArrayAdapter<Program> {



    private ArrayList<Program> programArrayList;
    private Context context;

    public static class Holder{
        TextView txtOra;
        TextView txtSucursala;
        TextView txtManager;

    }



    public CustomListAdapter(ArrayList<Program> programArrayList, Context context){
        super(context,R.layout.customlist,programArrayList);

        this.programArrayList = programArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       Program program = getItem(position);
       Holder  holder;


       if(convertView == null){

           holder = new Holder();
           LayoutInflater layoutInflater = LayoutInflater.from(getContext());
           convertView = layoutInflater.inflate(R.layout.customlist,parent,false);
           holder.txtOra = (TextView)  convertView.findViewById(R.id.txtOra);
           holder.txtManager = (TextView)convertView.findViewById(R.id.textManager);
           holder.txtSucursala = (TextView) convertView.findViewById(R.id.txtSucursala);

           convertView.setTag(holder);




       }else{
           holder = (Holder)convertView.getTag();
       }

       holder.txtOra.setText(program.getOra());
       holder.txtManager.setText(program.getManager());
       holder.txtSucursala.setText(program.getSucursala());


        return convertView;
    }
}
