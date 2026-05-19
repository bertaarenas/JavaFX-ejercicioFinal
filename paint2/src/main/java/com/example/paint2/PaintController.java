package com.example.paint2;
import com.example.paint2.PaintModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PaintController {

    // Lienzo
    @FXML
    private Canvas lienzo;

    @FXML
    private ColorPicker selectorColor;

    @FXML
    private Button botonLapiz;

    @FXML
    private Button botonBorrador;

    // Pincel
    private GraphicsContext contextoGrafico;

    // guarda e estado actual del color, herramineta..
    private PaintModel modelo;


    @FXML
    public void initialize() {

        modelo = new PaintModel();

        contextoGrafico = lienzo.getGraphicsContext2D();

        // pinta el lienzo de blanco
        contextoGrafico.setFill(Color.WHITE);
        contextoGrafico.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());

        // Pincel
        contextoGrafico.setStroke(modelo.getColorActual());
        contextoGrafico.setLineWidth(modelo.getTamanoPincel());

        lienzo.setOnMousePressed( evento -> {

            contextoGrafico.beginPath();

            contextoGrafico.moveTo(evento.getX(), evento.getY());

            contextoGrafico.stroke();

        });

        lienzo.setOnMouseDragged( evento -> {

              if (modelo.getHerramientaActual().equals("Lapiz")) {

                  contextoGrafico.setStroke(modelo.getColorActual());
                  contextoGrafico.setLineWidth(modelo.getTamanoPincel());

                  contextoGrafico.lineTo(evento.getX(), evento.getY());

                  contextoGrafico.stroke();

                  contextoGrafico.moveTo(evento.getX(), evento.getY());
              }
              else if (modelo.getHerramientaActual().equals("Borrador")) {

                  contextoGrafico.setFill(Color.WHITE);

                  double tamanoBorrador = modelo.getTamanoBorrador();

                  contextoGrafico.fillRect(evento.getX() - tamanoBorrador / 2, evento.getY() - tamanoBorrador / 2, tamanoBorrador, tamanoBorrador);
              }
        });
    }

    @FXML
    private void seleccionarLapiz() {

        modelo.setHerramientaActual("Lapiz");

        botonLapiz.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; " + "-fx-font-size: 13px; -fx-effect: dropshadow(gaussian, #aaffaa, 8, 0.5, 0, 0);");
        botonBorrador.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; -fx-font-size: 13px;");
    }

    @FXML
    private void seleccionarBorrador() {
        modelo.setHerramientaActual("Borrador");

        botonBorrador.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; " + "-fx-font-size: 13px; -fx-effect: dropshadow(gaussian, #aaaaff, 8, 0.5, 0, 0);");
        botonLapiz.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; -fx-font-size: 13px;");
    }

    @FXML
    private void cambiaColor() {
        Color colorSeleccionado = selectorColor.getValue();

        modelo.setColorActual(colorSeleccionado);

        contextoGrafico.setStroke(colorSeleccionado);
    }

    @FXML
    private void limpiarLienzo() {
        contextoGrafico.setFill(Color.WHITE);
        contextoGrafico.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
    }

    @FXML
    private void guardarImagen() throws IOException {

        FileChooser selectorArchivo = new FileChooser();
        selectorArchivo.setTitle("Guardar imagen");
        selectorArchivo.setInitialFileName("berta_paint");

        // disponible en png jpg
        selectorArchivo.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"));

        File archivo = selectorArchivo.showSaveDialog(lienzo.getScene().getWindow());

        if (archivo == null) return;

        // captura
        WritableImage imagenCapturada = new WritableImage((int) lienzo.getWidth(), (int) lienzo.getHeight());
        lienzo.snapshot(null, imagenCapturada);

        java.awt.image.BufferedImage imagenFinal = SwingFXUtils.fromFXImage(imagenCapturada, null);
        String nombreArchivo = archivo.getName().toLowerCase();
        String formato = nombreArchivo.endsWith(".jpg") ? "jpg" : "png";

        if (formato.equals("jpg")) {
            java.awt.image.BufferedImage imagenSinAlpha = new java.awt.image.BufferedImage(imagenFinal.getWidth(), imagenFinal.getHeight(), java.awt.image.BufferedImage.TYPE_INT_RGB);
            imagenSinAlpha.createGraphics().drawImage(imagenFinal, 0, 0, java.awt.Color.WHITE, null);
            imagenFinal = imagenSinAlpha;
        }

        ImageIO.write(imagenFinal, formato, archivo);

        System.out.println("Imagen guardada en: " + archivo.getAbsolutePath());



    }
}


