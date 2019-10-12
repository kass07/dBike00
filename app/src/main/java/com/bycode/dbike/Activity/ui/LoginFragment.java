package com.bycode.dbike.Activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bycode.dbike.Activity.MainActivity;
import com.bycode.dbike.Activity.MainMenuActivity;
import com.bycode.dbike.Config.ConfigFirebase;
import com.bycode.dbike.Model.Entidades.Usuario;
import com.bycode.dbike.Model.LoginViewModel;
import com.bycode.dbike.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private Usuario usuario;
    private FirebaseAuth autentification;

    private EditText etLoginEmail,etloginSenha;
    private Button btLoginEntrar;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        loginViewModel =ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        etLoginEmail = root.findViewById(R.id.editTextLoginEmail);
        etloginSenha = root.findViewById(R.id.editTextLoginSenha);
        btLoginEntrar = root.findViewById(R.id.buttonLoginEntrar);


        btLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etLoginEmail.getText().toString();
                String senha = etloginSenha.getText().toString();

                if(!email.isEmpty()){
                    if (!senha.isEmpty()){
                        usuario = new Usuario();
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        validarLogin(usuario);

                    }else {
                        Toast.makeText(getActivity(),"Digite a senha",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Digite o email",Toast.LENGTH_SHORT).show();
                }
            }
        });



        return root;
    }

    private void validarLogin(Usuario usuario) {
        autentification = ConfigFirebase.getFirebaseAutentification();
        autentification.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getActivity(), MainMenuActivity.class));
                        }else{
                            String erroExcessao = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erroExcessao = "Digite o email valido";
                            } catch (Exception e) {
                                erroExcessao = "ao cadastrar conta" +e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(getActivity(),"Erro: " + erroExcessao , Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
