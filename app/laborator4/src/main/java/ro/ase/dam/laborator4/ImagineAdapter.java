package ro.ase.dam.laborator4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {

    private List<ImaginiMasini> imagini = null;
    private Context ctx;
    private int resursaLayout;

    public ImagineAdapter(List<ImaginiMasini> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout, parent, false);
        TextView descriereTV=v.findViewById(R.id.textDescriptiv);
        ImageView iv=v.findViewById(R.id.imagineMasina);

        ImaginiMasini im=(ImaginiMasini) getItem(position);

        descriereTV.setText(im.getText());
        iv.setImageBitmap(im.getImagine());

        return v;
    }
}
