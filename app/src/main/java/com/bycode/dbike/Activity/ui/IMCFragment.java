package com.bycode.dbike.Activity.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bycode.dbike.Model.IMCViewModel;
import com.bycode.dbike.Model.LoginViewModel;
import com.bycode.dbike.R;


public class IMCFragment extends Fragment {

private IMCViewModel imcViewModel;


private EditText campoAltura, campoPeso;
private Button btCalcular;
private TextView tvResultado;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        imcViewModel = ViewModelProviders.of(this).get(IMCViewModel.class);
        View root = inflater.inflate(R.layout.fragment_imc, container, false);

        campoPeso = root.findViewById(R.id.editTextPeso);
        campoAltura = root.findViewById(R.id.editTextAltura);
        btCalcular = root.findViewById(R.id.buttonCalacular);
        tvResultado = root.findViewById(R.id.textViewResultado);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Double altura,peso;
                final String texto;

                String textoAltura = campoAltura.getText().toString();
                String textopeso = campoPeso.getText().toString();

                if(!textoAltura.isEmpty()){
                    if(!textopeso.isEmpty()){

                        altura = Double.parseDouble(campoAltura.getText().toString());
                        peso = Double.parseDouble(campoPeso.getText().toString());
                        texto =String.valueOf(imcViewModel.calcularIMC(altura,peso));
                        tvResultado.setText(texto);

                    }else{
                        Toast.makeText(getActivity(),"preencha peso", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"preencha a Altura", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return root;
    }
}
