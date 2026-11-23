package ro.ase.dam.laborator4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateImagini extends AppCompatActivity {

    List<Bitmap> imagini=new ArrayList<>();
    List<ImaginiMasini> lista=new ArrayList<>();
    private ImagineAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        List<String> linkuriImagini=new ArrayList<>();
        linkuriImagini.add("https://parkers-images.bauersecure.com/wp-images/22101/cut-out/040-bmw-x5-hybrid-grey-front-driving.jpg");
        linkuriImagini.add("https://www.autocritica.ro/wp-content/uploads/2021/02/audi-q3-sportback-top-1536x1024.jpg");
        linkuriImagini.add("https://stimg.cardekho.com/images/carexteriorimages/930x620/Volvo/XC60/12772/1754630721419/front-left-side-47.jpg");
        linkuriImagini.add("https://img.youtube.com/vi/zGT82MNcggo/maxresdefault.jpg");
        linkuriImagini.add("https://vehicle-images.carscommerce.inc/e73e-110009372/4JGFB6BB5TB588469/c31865629824d36a97eb1fa4345fb77d.jpg");

        Executor executor= Executors.newSingleThreadExecutor();
        Handler handler=new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link: linkuriImagini){
                    HttpURLConnection con=null;
                    try {
                        URL url=new URL(link);
                        con=(HttpURLConnection) url.openConnection();
                        InputStream is=con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista.add(new ImaginiMasini("BMW X5",imagini.get(0),"https://www.bmw.ro/ro/all-models/seria-x/suv/bmw-x5.html"));
                        lista.add(new ImaginiMasini("Audi Q3",imagini.get(1),"https://www.audi.ro/q3?models=Q3"));
                        lista.add(new ImaginiMasini("Volvo XC60",imagini.get(2),"https://www.volvocars.com/ro/cars/xc60/"));
                        lista.add(new ImaginiMasini("Mazda CX5",imagini.get(3),"https://www.mazda.ro/modele/"));
                        lista.add(new ImaginiMasini("Mercedes GLE",imagini.get(4),"https://www.mercedes-benz.ro/passengercars/models/suv/gle/overview.html"));

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });


        ListView lv=findViewById(R.id.imagini);
        adapter=new ImagineAdapter(lista,getApplicationContext(),R.layout.imagine_layout);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", lista.get(position).getLink());
                startActivity(it);
            }
        });
    }
}