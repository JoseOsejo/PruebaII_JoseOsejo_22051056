/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaprograii_joseosejo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author jcoq2
 */
public class ITunes {

    //atributos de randomaccessfile para los archivos
    RandomAccessFile archivoSong;
    RandomAccessFile archivoCodigo;
    RandomAccessFile archivoDownloads;

    public ITunes() {
        try {
            File folder = new File("archivosItunes");
            folder.mkdir();

            archivoSong = new RandomAccessFile("archivosItunes/songs.itn", "rw");
            archivoCodigo = new RandomAccessFile("archivosItunes/codigos.itn", "rw");
            archivoDownloads = new RandomAccessFile("archivosItunes/downloads/itn", "rw");

            setCodes();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private int getCodigo(long offset) throws IOException {
        archivoSong.seek(0);
        int cancion = archivoSong.readInt();
        int descarga = archivoSong.readInt();
        if (offset == 0) {
            archivoSong.seek(0);
            archivoSong.writeInt(cancion + 1);
            return cancion;
        } else if (offset == 4) {
            archivoSong.seek(0);
            archivoSong.skipBytes(4);
            archivoSong.writeInt(descarga + 1);
            return descarga;
        } else {
            return 0;
        }
    }

    private void setCodes() throws IOException {
        if (archivoSong.length() == 0) {
            archivoSong.writeInt(1);
            archivoSong.writeInt(1);
        }
    }

    public void reviewSong(int code, int stars) throws IOException {
        archivoSong.seek(0);
        while (archivoSong.getFilePointer() < archivoSong.length()) {
            long pos = archivoSong.getFilePointer();
            if (archivoSong.readInt() == code) {
                archivoSong.readUTF();
                archivoSong.readUTF();
                archivoSong.skipBytes(8);
                int estrellas = archivoSong.readInt();
                int reviews = archivoSong.readInt();

                if (estrellas >= 0 && estrellas <= 5) {
                    archivoSong.seek(pos);
                    archivoSong.readUTF();
                    archivoSong.readUTF();
                    archivoSong.skipBytes(8);
                    archivoSong.writeInt(stars + estrellas);
                    archivoSong.writeInt(reviews + 1);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public void addSong(String nombre, String cantante, double precio) throws IOException {
        archivoSong.seek(archivoSong.length());
        int codigo = getCodigo(0);
        archivoSong.writeInt(codigo);
        archivoSong.writeUTF(nombre);
        archivoSong.writeUTF(cantante);
        archivoSong.writeDouble(precio);
        archivoSong.writeInt(0);
        archivoSong.writeInt(0);
    }

    public void downloadSong(int codigoSong, String cliente) throws IOException {

    }

    public void songs(String txtFile) throws IOException {
        String contenidoArchivo
                = "";
        File txt = new File(txtFile + ".txt");
        FileWriter fw = new FileWriter(txt, false);

        archivoSong.seek(0);
        while (archivoSong.getFilePointer() < archivoSong.length()) {
            int codigo = archivoSong.readInt();
            String nombre = archivoSong.readUTF();
            String cantante = archivoSong.readUTF();
            double precio = archivoSong.readDouble();
            int estrellas = archivoSong.readInt();
            int reviews = archivoSong.readInt();
            archivoSong.skipBytes(4);
            double total = estrellas * reviews;
            contenidoArchivo += " Codigo: " + codigo + " Nombre: " + nombre + " Cantante: " + cantante + " Rating: " + total + " Precio: " + precio + "\n";
            fw.write(contenidoArchivo);
        }
        fw.flush();
    }

    public void infoSong(int codeSong) throws IOException {
        archivoSong.seek(0);
        while (archivoSong.getFilePointer() < archivoSong.length()) {
            if (archivoSong.readInt() == codeSong) {
                String nombre = archivoSong.readUTF();
                String cantante = archivoSong.readUTF();
                double precio = archivoSong.readDouble();
                int estrellas = archivoSong.readInt();
                int reviews = archivoSong.readInt();
                int totalDeEstrellas = estrellas * reviews;
                int descargas = archivoSong.readInt();

                System.out.println("Nombre: " + nombre + " Cantante: " + cantante + " Precio: " + precio + " Estrellas: " + totalDeEstrellas + " Reviews: " + reviews + " Downloads: " + descargas);
            }
        }
    }

}
