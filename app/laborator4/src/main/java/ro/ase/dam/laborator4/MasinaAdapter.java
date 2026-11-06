package ro.ase.dam.laborator4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MasinaAdapter extends BaseAdapter {
    private List<Masina> masini=new ArrayList<>();
    private Context ctx;
    private int resursaLayout;

    public MasinaAdapter(List<Masina> masini, Context ctx, int resursaLayout) {
        this.masini = masini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return masini.size();
    }

    @Override
    public Object getItem(int position) {
        return masini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,parent, false);
        TextView modelMasina=v.findViewById(R.id.modelMasina);
        TextView este4x4=v.findViewById(R.id.este4x4);
        TextView combusibil=v.findViewById(R.id.combustibil);

        Masina m=(Masina)getItem(position);

        modelMasina.setText(m.getModel());
        este4x4.setText(m.getEste4x4());
        combusibil.setText(m.getCombustibil());

        return v;
    }
}
