package ro.ase.dam.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateNoua extends AppCompatActivity {

    private EditText etNume;
    private CheckBox cb4x4;
    private Spinner spinnerCombustibil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_noua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etNume = findViewById(R.id.ET_nume);
        cb4x4 = findViewById(R.id.CB_4x4);
        spinnerCombustibil = findViewById(R.id.spinner_combustibil);
    }

    public void salveazaMasina(View view) {
        Masina masina = new Masina(
                etNume.getText().toString(),
                cb4x4.isChecked(),
                spinnerCombustibil.getSelectedItem().toString());

        Intent intent = new Intent();
        intent.putExtra("masina", masina);
        setResult(RESULT_OK, intent);
        finish();
    }
}