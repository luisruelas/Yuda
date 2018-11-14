package com.example.ruelas.yuda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ruelas on 08/02/2017.
 */
public class CustomAdapterlvmain extends ArrayAdapter {
    ArrayList<RowCatalogo> rows;
    private final String urlimagenes;
    private final String extension;
    RowCatalogo row;
    ArrayList<Boolean>collapsedlist;
    Context context;
    LayoutInflater inflater;
    Integer focusedview;
    Intent i;
    cFragment fragmentparent;
    ImageLoader il;

    public CustomAdapterlvmain(Context context, ArrayList<RowCatalogo> rows, cFragment parent) {
        super(context,0);
        this.rows=rows;
        urlimagenes=context.getResources().getString(R.string.urlimagenes);
        extension=context.getResources().getString(R.string.extensionimgs);
        Log.d("CODIGOLV",rows.get(0).getCodigo());
        Log.d("CODIGOLV",rows.get(1).getCodigo());
        focusedview=rows.size();
        this.context=context;
        this.fragmentparent=parent;
        il=ImageLoader.getInstance();
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return setgetNumofrows();
    }

    @Override
    public Object getItem(int position) {
        return rows.get(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder vh;
        row=rows.get(position*2);
        if (convertView==null){
            convertView=inflater.inflate(R.layout.rowcatalogo,parent,false);
            vh=new ViewHolder();
            vh.llleft= (LinearLayout) convertView.findViewById(R.id.llleftp);
            vh.llleft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(focusedview!=position*2){
                        setFocused(position*2);
                        row=rows.get(position*2);
                        i=new Intent(fragmentparent.getActivity(),PopupActivity.class);
                        i.putExtra("row",row);
                        fragmentparent.getActivity().startActivity(i);
                        notifyDataSetChanged();
                    }else{
                        focusedview=rows.size();
                        notifyDataSetChanged();
                    }
                }
            });
            vh.llright= (LinearLayout) convertView.findViewById(R.id.llrightp);
            vh.llright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(focusedview!=position*2+1){
                        setFocused(position*2+1);
                        row=rows.get(position*2+1);
                        i=new Intent(fragmentparent.getActivity(),PopupActivity.class);
                        i.putExtra("row",row);
                        fragmentparent.getActivity().startActivity(i);
                        notifyDataSetChanged();
                    }else{
                        focusedview=rows.size();
                        notifyDataSetChanged();
                    }
                }
            });
            //inicializar elementos
            vh.llbottom1= (LinearLayout) vh.llleft.findViewById(R.id.llbottom);
            vh.lltop1= (LinearLayout) vh.llleft.findViewById(R.id.lltop);
            vh.tvnombreprod1= (TextView) vh.llleft.findViewById(R.id.tvnombreproducto);
            vh.tvprecio1= (TextView) vh.llleft.findViewById(R.id.tvprecio);
            vh.ivproducto1= (ImageView) vh.llleft.findViewById(R.id.ivproducto);
            vh.tvdescripcion1= (TextView) vh.llleft.findViewById(R.id.tvdescripcion);
            vh.ivvideo1=(ImageView)vh.llleft.findViewById(R.id.ivplayvideo);
            
            vh.llbottom2= (LinearLayout) vh.llright.findViewById(R.id.llbottom);
            vh.lltop2= (LinearLayout) vh.llright.findViewById(R.id.lltop);
            vh.tvnombreprod2= (TextView) vh.llright.findViewById(R.id.tvnombreproducto);
            vh.tvprecio2= (TextView) vh.llright.findViewById(R.id.tvprecio);
            vh.tvdescripcion2= (TextView) vh.llright.findViewById(R.id.tvdescripcion);
            vh.ivvideo2=(ImageView)vh.llleft.findViewById(R.id.ivplayvideo);
            convertView.setTag(vh);

            vh.ivproducto2= (ImageView) vh.llright.findViewById(R.id.ivproducto);
        }
        else{
            vh= (ViewHolder) convertView.getTag();
        }

        vh.lltop1.setVisibility(View.GONE);
        vh.lltop2.setVisibility(View.GONE);
        //settextstop
        vh.tvnombreprod1.setText(row.getNombre());
        vh.tvprecio1.setText(row.getPrecio());
        vh.tvdescripcion1.setText(row.getDescripcion());
        //set image top
        vh.ivproducto1.setScaleType(ImageView.ScaleType.FIT_XY);
        //il.displayImage(urlimagenes+row.getCodigo()+extension,vh.ivproducto1);
        new setImageAsync(urlimagenes+row.getCodigo()+extension,vh.ivproducto1).execute();
        //vh.ivproducto1.setImageBitmap(images.get(row.getCodigo()));

        //cambiarrow

        //settexbot si no es el último
        if(position*2+1!=rows.size()){
            row=rows.get(position*2+1);
            //textos
            vh.tvnombreprod2.setText(row.getNombre());
            vh.tvprecio2.setText(row.getPrecio());
            vh.tvdescripcion2.setText(row.getDescripcion());
            //imagen
            vh.ivproducto2.setScaleType(ImageView.ScaleType.FIT_XY);
            //il.displayImage(urlimagenes+row.getCodigo()+extension, vh.ivproducto2);;
            new setImageAsync(urlimagenes+row.getCodigo()+extension, vh.ivproducto2).execute();
        }
        //desaparecerlo is es
        if(position*2+1==rows.size())
        {
            vh.llright.setVisibility(View.INVISIBLE);
        }


        if(focusedview==position*2){
            //vh.lltop1.setVisibility(View.VISIBLE);
        }
        if(focusedview==position*2+1){
            //vh.lltop2.setVisibility(View.VISIBLE);
        }

        //imagenes

        return convertView;
        }
    public Integer getFocused(){
        return focusedview;
    }

    private int getItemcount() {
        return rows.size();
    }

    public void setFocused(int i) {
        focusedview=i;
    }

    public void setItems(ArrayList<RowCatalogo> catalogoArray) {
        rows=catalogoArray;
    }

    public Integer setgetNumofrows() {
        if(rows.size()%2!=0)
            return rows.size()/2+1;
        return rows.size()/2;
    }

    private class ViewHolder{
        LinearLayout lltop2;
        LinearLayout lltop1;
        LinearLayout llbottom1;
        LinearLayout llbottom2;
        LinearLayout llright,llleft;
        TextView tvnombreprod1, tvprecio1, tvnombreprod2,tvprecio2,tvdescripcion1,tvdescripcion2;
        ImageView ivproducto1,ivproducto2;
        ImageView ivvideo1;
        ImageView ivvideo2;
    }
}