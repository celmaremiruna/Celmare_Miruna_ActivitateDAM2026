package ro.ase.dam.extra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class ActivitateAdaugare extends AppCompatActivity {
    EditText editTextTitlu;
    EditText editTextAutor;
    CheckBox checkBoxTradusa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_adaugare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextTitlu=findViewById(R.id.etTitlu);
        editTextAutor=findViewById(R.id.etAutor);
        checkBoxTradusa=findViewById(R.id.cbTradusa);
        Intent it=getIntent();
        if(it.hasExtra("carte")){
            Carte carte=it.getParcelableExtra("carte");
            editTextTitlu.setText(carte.getTitlu());
            editTextAutor.setText(carte.getAutor());
            checkBoxTradusa.setChecked(carte.isEsteTradusa());
        }
        Button butonSalvare=findViewById(R.id.btnSalveaza);
        butonSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carte c=new Carte(
                        editTextTitlu.getText().toString(),
                        editTextAutor.getText().toString(),
                        checkBoxTradusa.isChecked()
                );
                Intent intent=new Intent();
                intent.putExtra("carte",(Serializable) c);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}