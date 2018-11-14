package com.example.ruelas.yuda;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ruelas on 07/02/2017.
 */
public class AllProducts{
    //private static final String url="http://google.com";
    //private static final String url="http://192.168.0.101/juda/returnallproducts.php";
    private static final String url="http://10.0.2.2:80/juda/returnallproducts.php";
    private static final String urlvideos="http://10.0.2.2:80/juda/videos/";
    private static final String urlimagenes="http://10.0.2.2:80/juda/imagenes/";
    private static final String extensionvideos=".mp4";
    private static DatabaseHelper dbobject;
    private static ImageLoader il;
    private static AllProducts aps;
    private static ArrayList<RowCatalogo> allproducts=new ArrayList<>();;
    private static String nombre, precio, codigo,descripcion;
    private static Context appcontext;

    public static String getExtensionvideos() {
        return extensionvideos;
    }

    public static String getUrlvideos() {
        return urlvideos;
    }

    private AllProducts(Context ctx){
        dbobject=new DatabaseHelper(ctx);
        appcontext=ctx.getApplicationContext();
    }
    public static AllProducts getInstanceAndSetLV(Context context){
        if(aps==null){
            //aps=new AllProducts(context, lv);
            aps=new AllProducts(context);
        }
        return aps;
    }

    public static String getDescripcion() {
        return descripcion;
    }

    public static void setDescripcion(String descripcion) {
        AllProducts.descripcion = descripcion;
    }

    public static String getCodigo() {
        return codigo;
    }

    public static void setCodigo(String codigo) {
        AllProducts.codigo = codigo;
    }

    public static String getPrecio() {
        return precio;
    }

    public static void setPrecio(String precio) {
        AllProducts.precio = precio;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        AllProducts.nombre = nombre;
    }



    public static void setAllproducts(ArrayList<RowCatalogo> allproducts) {
        AllProducts.allproducts = allproducts;
    }

    public static ArrayList<RowCatalogo> getAllproducts() {
        if(allproducts==null){
            allproducts=new ArrayList<>();
        }
        return allproducts;
    }

    public static String getUrlimagenes() {
        return urlimagenes;
    }

    public void setListView(final ListView listView, final cFragment parent) {
        StringRequest sr= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray myarray=new JSONArray();
                JSONObject jsonObject=new JSONObject();
                try {
                    myarray=new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(myarray.length()!=0){
                    for(int i=0;i<myarray.length();i++){
                        try {
                            jsonObject=myarray.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String s=Integer.toString(i);

                        try {
                            RowCatalogo object=new RowCatalogo(jsonObject.getString("nombre"),
                                    jsonObject.getString("descripcion"),
                                    jsonObject.getString("precio"),
                                    jsonObject.getString("codigo"),
                                    jsonObject.getString("codigodescarga"));
                            if(!dbobject.codeisAlready(object.getCodigo())){
                                dbobject.addRegister(object);
                                Log.d("codigodelobjeto",object.getCodigo());
                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    listView.setAdapter(new CustomAdapterlvmain(appcontext,dbobject.allproducts(),parent));
                    Log.d("maspruebas1",dbobject.allproducts().get(0).getCodigo());
                    Log.d("maspruebas2",dbobject.allproducts().get(0).getCodigo());
                }
                VolleySing.getInstance(appcontext).stopRequests();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(appcontext,"Favor de verificar su conexión a internet",Toast.LENGTH_SHORT).show();

            }
        });
        VolleySing.getInstance(appcontext).addStringRequest(sr);
        }

}
