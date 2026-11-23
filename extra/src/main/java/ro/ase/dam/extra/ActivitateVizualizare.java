package ro.ase.dam.extra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivitateVizualizare extends AppCompatActivity {

    private List<Carte> listaCarti=new ArrayList<>();
    private ArrayAdapter<Carte> adapter=null;

    int idModificat=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_vizualizare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView lv=findViewById(R.id.lvAfisare);
        listaCarti= (List<Carte>) getIntent().getSerializableExtra("carti");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCarti);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentmodifica=new Intent(getApplicationContext(),ActivitateAdaugare.class);
                intentmodifica.putExtra("carte", (Parcelable) listaCarti.get(position));
                idModificat=position;
                startActivityForResult(intentmodifica,101);
                Toast.makeText(getApplicationContext(),listaCarti.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaCarti.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==101){
                listaCarti.set(idModificat,data.getParcelableExtra("carte"));
                adapter.notifyDataSetChanged();
            }
        }
    }
}