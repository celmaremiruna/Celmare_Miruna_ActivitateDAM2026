package ro.ase.dam.extra;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Carte implements Serializable, Parcelable {
    private String titlu;
    private String autor;
    private boolean esteTradusa;

    public Carte(String titlu, String autor, boolean esteTradusa) {
        this.titlu = titlu;
        this.autor = autor;
        this.esteTradusa = esteTradusa;
    }

    protected Carte(Parcel in) {
        titlu = in.readString();
        autor = in.readString();
        esteTradusa = in.readByte() != 0;
    }

    public static final Creator<Carte> CREATOR = new Creator<Carte>() {
        @Override
        public Carte createFromParcel(Parcel in) {
            return new Carte(in);
        }

        @Override
        public Carte[] newArray(int size) {
            return new Carte[size];
        }
    };

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isEsteTradusa() {
        return esteTradusa;
    }

    public void setEsteTradusa(boolean esteTradusa) {
        this.esteTradusa = esteTradusa;
    }

    @NonNull
    @Override
    public String toString() {
        return "Carte: "+titlu+", scrisa de "+autor+", este tradusa in romana: "+ (esteTradusa ? "da":"nu");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(titlu);
        dest.writeString(autor);
        dest.writeByte((byte) (esteTradusa ? 1 : 0));
    }
}
