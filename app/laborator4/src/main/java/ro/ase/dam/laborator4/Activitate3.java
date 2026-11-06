package ro.ase.dam.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Activitate3 extends AppCompatActivity {

    private List<Masina> listaMasini=new ArrayList<>();
    private int idModificat=0;
    private MasinaAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }
        );
        ListView listView=findViewById(R.id.list_view);
        listaMasini=(List<Masina>) getIntent().getSerializableExtra("lista");
        //adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaMasini);
        //listView.setAdapter(adapter);
        adapter=new MasinaAdapter(listaMasini,getApplicationContext(),R.layout.special_layout);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentModifica=new Intent(getApplicationContext(),ActivitateNoua.class);
                intentModifica.putExtra("masina", (Parcelable) listaMasini.get(position));
                idModificat=position;
                startActivityForResult(intentModifica,209);
                Toast.makeText(getApplicationContext(),listaMasini.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener((parent, view, position, id)->{
            listaMasini.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==209){
                listaMasini.set(idModificat,data.getParcelableExtra("masina"));
                adapter.notifyDataSetChanged();
            }
        }
    }
}