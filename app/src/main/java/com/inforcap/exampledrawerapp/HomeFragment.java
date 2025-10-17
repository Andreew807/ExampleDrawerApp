package com.inforcap.exampledrawerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inforcap.exampledrawerapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment  {

    private FragmentHomeBinding binding;
    private TextView textViewHomeTitle;
    private EditText editTextNombreHome;
    private Button buttonSaludarHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        textViewHomeTitle = binding.textViewHomeTitle;
        editTextNombreHome = binding.editTextNombreHome;
        buttonSaludarHome = binding.buttonSaludarHome;

        textViewHomeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewHomeTitle.setText("Bienvenidos a mi App!!!");
            }
        });


        editTextNombreHome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textoIngresado = editTextNombreHome.getText().toString().trim();
                if(textoIngresado.length() >= 5){
                    buttonSaludarHome.setEnabled(true);
                }else {
                    buttonSaludarHome.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonSaludarHome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Hola " + editTextNombreHome.getText().toString().trim(),Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}