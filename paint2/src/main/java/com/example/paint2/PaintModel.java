package com.example.paint2;
import javafx.scene.paint.Color;
public class PaintModel {

    private String herramientaActual = "Lapiz";
    private Color colorActual = Color.BLACK;
    private double tamanoPincel = 5.0;
    private double tamanoBorrador = 20.0;

    public String getHerramientaActual() { return herramientaActual;}
    public Color getColorActual() { return colorActual;}
    public double getTamanoPincel() { return tamanoPincel;}
    public double getTamanoBorrador() { return tamanoBorrador;}

    // setters en herramienta y color para cambiar
    public void setHerramientaActual(String herramienta) { this.herramientaActual = herramienta; }
    public void setColorActual(Color color) { this.colorActual = color; }





}
