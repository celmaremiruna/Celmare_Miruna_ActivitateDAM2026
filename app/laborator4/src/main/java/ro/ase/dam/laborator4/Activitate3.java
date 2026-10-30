package ro.ase.dam.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activitate3 extends AppCompatActivity {

    private ArrayList<Masina> listaMasini;
    private ArrayAdapter<Masina> adapter;

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
        listaMasini=(ArrayList<Masina>) getIntent().getSerializableExtra("lista");
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaMasini);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, listaMasini.get(position).toString(),Toast.LENGTH_SHORT).show());

        listView.setOnItemLongClickListener((parent, view, position, id)->{
            listaMasini.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}