/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaprograii_joseosejo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author jcoq2
 */
public class ITunes 
{

    //atributos de randomaccessfile para los archivos
RandomAccessFile archivoSongs;
RandomAccessFile archivoCodigo;
RandomAccessFile archivoDownloads;

public ITunes(){
    try{
    File folder = new File("archivosItunes");
    folder.mkdir();
    
    archivoSongs = new RandomAccessFile("archivosItunes/songs.itn","rw");
    archivoCodigo = new RandomAccessFile("archivosItunes/codigos.itn","rw");
    archivoDownloads = new RandomAccessFile("archivosItunes/downloads/itn","rw");
    
    setCodes();
    }catch(IOException e){
        JOptionPane.showMessageDialog(null, e);
    }
    
}

 

    private int getCodigo(long offset) throws IOException {
        archivoSongs.seek(0);
        int cancion = archivoSongs.readInt();
        int descarga = archivoSongs.readInt();
        if (offset == 0) {
            archivoSongs.seek(0);
            archivoSongs.writeInt(cancion + 1);
            return cancion;
        } else if (offset == 4) {
            archivoSongs.seek(0);
            archivoSongs.skipBytes(4);
            archivoSongs.writeInt(descarga + 1);
            return descarga;
        } else {
            return 0;
        }
    }

    private void setCodes() throws IOException {
        if (archivoSongs.length() == 0) {
            archivoSongs.writeInt(1);
            archivoSongs.writeInt(1);
        }
    }
    

    
    
    public void downloadSong(int codeSong, String cliente){
        
    }
    
    public void songs(String txtFile){
        
    }
    
    public void infoSong(int codeSong){
        
    }




    
}
