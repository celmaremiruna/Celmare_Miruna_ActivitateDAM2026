package ro.ase.dam.laborator4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Masina implements Parcelable, Serializable {
    public String model;
    public boolean este4x4;
    public String combustibil;

    public Masina(String model, boolean este4x4, String combustibil){
        this.model=model;
        this.este4x4=este4x4;
        this.combustibil=combustibil;
    }


    protected Masina(Parcel in) {
        model = in.readString();
        este4x4 = in.readByte() != 0;
        combustibil = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(model);
        dest.writeByte((byte) (este4x4 ? 1 : 0));
        dest.writeString(combustibil);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Masina> CREATOR = new Creator<Masina>() {
        @Override
        public Masina createFromParcel(Parcel in) {
            return new Masina(in);
        }

        @Override
        public Masina[] newArray(int size) {
            return new Masina[size];
        }
    };

    @Override
    public String toString() {
        return "Model: " + model + ", 4x4: " + (este4x4 ? "da" : "nu") + ", combustibil: " + combustibil;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEste4x4() {
        if(este4x4==true)
            return "Este 4x4";
        else
            return "Nu este 4x4";
    }

    public boolean isEste4x4() {
        return este4x4;
    }

    public void setEste4x4(boolean este4x4) {
        this.este4x4 = este4x4;
    }

    public String getCombustibil() {
        return combustibil;
    }

    public void setCombustibil(String combustibil) {
        this.combustibil = combustibil;
    }
}
