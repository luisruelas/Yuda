package com.example.ruelas.yuda;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Ruelas on 20/02/2017.
 */
public class CustomAdapterlvmimagia extends ArrayAdapter {
    ArrayList<RowCatalogo>mimagia;
    LayoutInflater inflater;
    DatabaseHelper db;
    Context context;
    private final String urlimagenes;
    private final String extension;
    public CustomAdapterlvmimagia(Context context) {
        super(context,0);
        urlimagenes=context.getResources().getString(R.string.urlimagenes);
        extension=context.getResources().getString(R.string.extensionimgs);
        inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        db=new DatabaseHelper(context);
        setMimagia();
        this.context=context;
    }
    public void setMimagia(){

        mimagia=db.getMimagia();
    }

    @Override
    public Object getItem(int position) {
        return mimagia.get(position);
    }

    @Override
    public int getCount() {
        return mimagia.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("PRUEBA2",mimagia.get(position).getNombre());
        ViewHolder vh=new ViewHolder();;
        RowCatalogo row=mimagia.get(position);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.rowmimagia,parent,false);
            vh.ivproducto= (ImageView) convertView.findViewById(R.id.ivmimagiaimagen);
            vh.nombre= (TextView) convertView.findViewById(R.id.tvmimagianombre);
            vh.bttutorial= (Button) convertView.findViewById(R.id.btmimagiarevelacion);
            vh.btvideo= (Button) convertView.findViewById(R.id.btmimagiapresentacion);
            convertView.setTag(vh);
        }
        else{
            vh= (ViewHolder) convertView.getTag();
        }
        //clicktutorial
        vh.bttutorial.setTag(row);
        vh.bttutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,PopupActivity.class);
                RowCatalogo tutorow=(RowCatalogo)view.getTag();
                tutorow.setDescripcion("tutorial");
                intent.putExtra("row",tutorow);
                context.startActivity(intent);
            }
        });
        //clickpresentacion
        vh.btvideo.setTag(row);
        vh.btvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,PopupActivity.class);
                intent.putExtra("row",(RowCatalogo)view.getTag());
                context.startActivity(intent);
            }
        });
        vh.nombre.setText(row.getNombre());
        ImageLoader.getInstance().displayImage(urlimagenes+row.getCodigo()+extension,vh.ivproducto);
        return convertView;
    }
    private class ViewHolder{
        ImageView ivproducto;
        TextView nombre;
        Button btvideo, bttutorial;
    }
    public void addMagia(RowCatalogo rc){
        Boolean magia=db.addMagia(rc);
        if(magia){
            setMimagia();
            Toast.makeText(context,"ARTICULO AÑADIDO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"YA POSEES EL ARTICULO EN TU LISTA",Toast.LENGTH_SHORT).show();
        }

        notifyDataSetChanged();
    }
}
