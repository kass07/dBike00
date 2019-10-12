package com.bycode.dbike.Model;

import androidx.lifecycle.ViewModel;

public class IMCViewModel extends ViewModel {


    public double calcularIMC(double alt, double peso){
        return (peso/(alt*alt));
    }


}
