package org.joaco.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class AlumnoEntity {

    protected int id;
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected String email;
    protected ArrayList<DireccionEntity> direccionEntities;

    public AlumnoEntity() {
        direccionEntities = new ArrayList<>();
    }

    public AlumnoEntity(int id, String nombre, String apellido, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        direccionEntities = new ArrayList<>();

    }
    public ArrayList<DireccionEntity> getDireccions() {
        return direccionEntities;
    }

    public void setDireccions(ArrayList<DireccionEntity> direccionEntities) {
        this.direccionEntities = direccionEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AlumnoEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", email='" + email + '\'' +
                ", direccionEntities=" + direccionEntities +
                '}';
    }


}
