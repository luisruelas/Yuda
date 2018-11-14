package com.example.ruelas.yuda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Ruelas on 06/02/2017.
 */
public class Fragment_catalogo extends cFragment{
    private ListView lvcatalogo;
    private CustomAdapterlvmain ca;
    private LayoutInflater inflater;
    public Fragment_catalogo() {
        //titulo
        setTitle("CATÁLOGO");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inicializar
        View contenido=inflater.inflate(R.layout.fragment_catalogo,container,false);
        setContent(contenido);
        this.inflater=inflater;
        //inicializar
        lvcatalogo= (ListView) contenido.findViewById(R.id.lvcatalogo);
        lvcatalogo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomAdapterlvmain adapter= (CustomAdapterlvmain) lvcatalogo.getAdapter();
                adapter.getFocused();

            }
        });
        //GETROWS
        AllProducts.getInstanceAndSetLV(getContext()).setListView(lvcatalogo,this);
        //return content
        return getContent();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
