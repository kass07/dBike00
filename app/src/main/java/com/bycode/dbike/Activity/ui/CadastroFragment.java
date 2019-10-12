package com.bycode.dbike.Activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bycode.dbike.Activity.MainActivity;
import com.bycode.dbike.Config.ConfigFirebase;
import com.bycode.dbike.Model.CadastroViewModel;
import com.bycode.dbike.Model.Entidades.Usuario;
import com.bycode.dbike.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroFragment extends Fragment {

    private CadastroViewModel cadastroViewModel;
    private Usuario usuario;
    private FirebaseAuth configFirebase;

    private EditText etNome, etMail, etPhone, etPassword;
    private Button btCadastrar;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        cadastroViewModel = ViewModelProviders.of(this).get(CadastroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cadastro, container, false);

        etNome = root.findViewById(R.id.editTextNome);
        etMail = root.findViewById(R.id.editTextLoginEmail);
        etPhone = root.findViewById(R.id.editTextTelepone);
        etPassword = root.findViewById(R.id.editTextLoginSenha);
        btCadastrar = root.findViewById(R.id.buttonCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = etNome.getText().toString();
                String email = etMail.getText().toString();
                String fone = etPhone.getText().toString();
                String senha = etPassword.getText().toString();

                if (!nome.isEmpty()){
                    if (!email.isEmpty()){
                        if (!fone.isEmpty()){
                            if (!senha.isEmpty()){
                                usuario = new Usuario();
                                usuario.setNome(nome);
                                usuario.setEmail(email);
                                usuario.setPhone(fone);
                                usuario.setSenha(senha);
                                cadastrarUsuario(usuario);

                            }else{
                                Toast.makeText(getActivity(),"preencha a senha", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(),"preencha o telefone", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(),"preencha o Email", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"preencha o Nome", Toast.LENGTH_SHORT).show();
                }
            }
        });




        return root;
    }

    public void cadastrarUsuario(Usuario usuario){
        configFirebase = ConfigFirebase.getFirebaseAutentification();
        configFirebase.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"Conta Cadastrada com sucesso: "  , Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getActivity(), MainActivity.class));

                        }else{
                            String erroExcessao = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                erroExcessao = "Digite uma senha mais forte";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erroExcessao = "Digite um email valido";
                            } catch (FirebaseAuthUserCollisionException e) {
                                erroExcessao = "Esta conta ja foi cadastrada";
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
