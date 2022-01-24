package com.example.calculatorrate.ui.slideshow;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculatorrate.DatabaseSQLIte;
import com.example.calculatorrate.R;
import com.example.calculatorrate.databinding.FragmentSlideshowBinding;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private EditText edtSariu;
    private Spinner spnRate;
    private SeekBar skbCredit;
    private Button btnCalculeaza;
    private ArrayList<Integer> rate = new ArrayList<>();
    private TextView seekText;
    private DatabaseSQLIte databaseSQLIte;



    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        seekText = (TextView)root.findViewById(R.id.seekText);
        edtSariu = (EditText) root.findViewById(R.id.edtSalariu);
        spnRate = (Spinner) root.findViewById(R.id.spnNrRate);
        skbCredit = (SeekBar) root.findViewById(R.id.skbCredit);
        btnCalculeaza = (Button) root.findViewById(R.id.btnCalculeaza);

        databaseSQLIte = new DatabaseSQLIte(getContext());

        ArrayList<Integer> nrRate = new ArrayList<>();
        nrRate.clear();
        ArrayAdapter<Integer> arrayAdapter = null;



        int  salariu = databaseSQLIte.getSalariu();
        nrRate = databaseSQLIte.getNrRate();
        int credit = databaseSQLIte.getCredit();


        edtSariu.setText(String.valueOf(salariu)+" Lei");
        seekText.setText(String.valueOf(credit));
        skbCredit.setProgress(credit);
        seekText.setText(String.valueOf(credit)+" Lei");


        //rate.add(nrRate);

        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,nrRate);
        arrayAdapter.notifyDataSetChanged();
        spnRate.setAdapter(arrayAdapter);






        btnCalculeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               int  nrRate = databaseSQLIte.getNrRate2();
              int valoare = credit / nrRate;

               Toast.makeText(getActivity(),String.valueOf(valoare),Toast.LENGTH_LONG).show();

//              int valSalariu = salariu / 2;
//              if(valoare > valSalariu){
//                  Toast.makeText(getActivity(),"Nu puteti obtine acest credit",Toast.LENGTH_LONG);
//              }else{
//                  Toast.makeText(getActivity(),"Puteti obtine acest credit !", Toast.LENGTH_LONG).show();
//              }

            }
        });



        //valoarea creditului / nr luni si sa nu depaseasca prin validare in spate in cod jumatate de salariu

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}