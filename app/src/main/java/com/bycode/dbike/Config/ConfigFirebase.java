package com.bycode.dbike.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

private static FirebaseAuth autentificacao;
private static DatabaseReference referencia;

public static FirebaseAuth getFirebaseAutentification(){
    if(autentificacao == null){
        autentificacao = FirebaseAuth.getInstance();
    }
    return autentificacao;
}

public static DatabaseReference getDatabaseRefence(){
    if(referencia == null){
        referencia = FirebaseDatabase.getInstance().getReference();
    }
return referencia;
}

}
