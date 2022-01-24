package com.example.calculatorrate.ui.gallery;

import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculatorrate.Credite;
import com.example.calculatorrate.DatabaseSQLIte;
import com.example.calculatorrate.R;
import com.example.calculatorrate.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {


    private EditText edtSariu;
    private Spinner spnRate;
    private SeekBar skbCredit;
    private Button btnAdauga;
    private ArrayList<String> rate = new ArrayList<>();
    private TextView seekText;
    private DatabaseSQLIte databaseSQLIte;


    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        databaseSQLIte = new DatabaseSQLIte(getActivity());

        seekText = (TextView)root.findViewById(R.id.seekText);
        edtSariu = (EditText) root.findViewById(R.id.edtSalariu);
        spnRate = (Spinner) root.findViewById(R.id.spnNrRate);
        skbCredit = (SeekBar) root.findViewById(R.id.skbCredit);
        btnAdauga = (Button) root.findViewById(R.id.btnAdauga);

        rate.add("1");
        rate.add("3");
        rate.add("6");
        rate.add("9");
        rate.add("12");
        rate.add("24");


        skbCredit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                progress = ((int)Math.round(progress));
                seekText.setText(String.valueOf(progress)+" Lei");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,rate);
        spnRate.setAdapter(arrayAdapter);




        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int salariu = Integer.parseInt(edtSariu.getText().toString());
                    int nrRate = Integer.parseInt(spnRate.getSelectedItem().toString());
                    int valoareCredit = skbCredit.getProgress();

                    databaseSQLIte.adaugareCredit(new Credite(salariu,nrRate,valoareCredit));

            }
        });







        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}