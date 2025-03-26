package org.joaco.model;

public class DireccionEntity {

    protected int id;
    protected String calle;
    protected int altura;

    public DireccionEntity(int id, String calle, int altura) {
        this.id = id;
        this.calle = calle;
        this.altura = altura;
    }

    public DireccionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
}
