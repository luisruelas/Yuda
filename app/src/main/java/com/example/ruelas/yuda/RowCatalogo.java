package com.example.ruelas.yuda;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Ruelas on 07/02/2017.
 */
public class RowCatalogo implements Serializable {
    private Integer resourceImagen;
    private String nombre, codigo;
    private String descripcion,descripcionc;
    private String precio;
    private Integer lengthdesc=30;
    private Bitmap imagen;
    private String codigodescarga;
    public RowCatalogo(String nombre, String descripcion, String precio, String codigo, String codigodescarga) {
        this.resourceImagen = resourceImagen;
        this.nombre = nombre;
        if(descripcion.length()>lengthdesc){
            this.descripcionc = descripcion.substring(0,lengthdesc+1)+"...";
        }
        this.descripcion=descripcion;
        this.precio = precio;
        this.codigo=codigo;
        this.codigodescarga=codigodescarga;
    }

    public String getCodigodescarga() {
        return codigodescarga;
    }

    public void setCodigodescarga(String codigodescarga) {
        this.codigodescarga = codigodescarga;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getResourceImagen() {
        return resourceImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcionc() {

        return descripcionc;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setResourceImagen(Integer resourceImagen) {
        this.resourceImagen = resourceImagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
