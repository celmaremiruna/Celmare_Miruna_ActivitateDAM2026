package ro.ase.dam.laborator4;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Masina implements Serializable {
    public String model;
    public boolean este4x4;
    public String combustibil;

    public Masina(String model, boolean este4x4, String combustibil){
        this.model=model;
        this.este4x4=este4x4;
        this.combustibil=combustibil;
    }


    @Override
    public String toString() {
        return "Model: " + model + ", 4x4: " + (este4x4 ? "da" : "nu") + ", combustibil: " + combustibil;
    }
}
