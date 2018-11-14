package com.example.ruelas.yuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ruelas on 06/02/2017.
 */
public class Fragment_mimagia extends cFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    String urlgetbycodigo="http://10.0.2.2:80/juda/getbycodigo.php";
    ArrayList<RowCatalogo>mimagia;
    View contentView;
    ListView lvmimagia;
    private EditText etcodigo;
    private Button btcheck;
    DatabaseHelper db;
    CustomAdapterlvmimagia ca;
    public Fragment_mimagia() {
        //titulo
        setTitle("MI MAGIA");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fragment_mimagia,container,false);
        setContent(contentView);
        mimagia=new ArrayList<>();
        ca=new CustomAdapterlvmimagia(getContext());
        lvmimagia= (ListView) contentView.findViewById(R.id.lvmimagia);
        lvmimagia.setAdapter(ca);
        lvmimagia.setOnItemClickListener(this);
        this.etcodigo = (EditText) getContent().findViewById(R.id.etcodigo);
        this.btcheck=(Button)getContent().findViewById(R.id.btingresarcodigo);
        btcheck.setOnClickListener(this);
        db=new DatabaseHelper(getContext());
        return getContent();

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btingresarcodigo:
                checkCodigo();
                break;
        }
    }

    public void checkCodigo(){
        if(etcodigo.getText().toString().length()==0){
            Toast.makeText(getContext(),"el codigo NO es correcto",Toast.LENGTH_SHORT).show();
            return;
        }
        RowCatalogo temp=db.getProductByCode(etcodigo.getText().toString());
        if(temp!=null){
            ca.addMagia(temp);
        }
        else{
            Toast.makeText(getContext(),"el codigo NO es correcto",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CustomAdapterlvmimagia clv= (CustomAdapterlvmimagia) lvmimagia.getAdapter();
        RowCatalogo row= (RowCatalogo) clv.getItem(i);
        Intent intent=new Intent(getActivity(),PopupActivity.class);
        intent.putExtra("row",row);
        startActivity(intent);
    }
}
