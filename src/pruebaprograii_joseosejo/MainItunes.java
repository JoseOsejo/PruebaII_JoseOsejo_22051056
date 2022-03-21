/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaprograii_joseosejo;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jcoq2
 */
public class MainItunes {
    public static void main(String[] args) throws IOException {
        int opcion = 0;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        ITunes itune = new ITunes();
        System.out.println("***Itunes***\n");
        do {
            System.out.print("1-Agregar Song\n2-Review Song\n3-Download Song\n4-Songs\n5-Info Song\n6-Salir\nIngrese una opcion: ");
            opcion = sc.nextInt();
            if (opcion == 1) {
                System.out.println("Ingrese el nombre de la cancion: ");
                String nombreCancion = sc.next();
                System.out.println("Nombre del cantante: ");
                String nombreCantante = sc.next();
                System.out.println("Precio de la cancion");
                double precioCancion = sc.nextDouble();
                itune.addSong(nombreCancion, nombreCantante, precioCancion);
            } else if (opcion == 2) {
                System.out.println("Ingrese el codigo de la cancion : ");
                int codigoCancion = sc.nextInt();
                System.out.println("Ingrese cantidad de Estrellas: ");
                int reviewEstrellas = sc.nextInt();
                itune.reviewSong(codigoCancion, reviewEstrellas);
            } else if (opcion == 3) {
                 System.out.println("Ingrese el codigo de la cancion: ");
                    int codi = sc.nextInt();
                    System.out.println("Nombre del cliente: ");
                    String cliente = sc.next();
                    itune.downloadSong(codi, cliente);
            } else if (opcion == 4) {
                System.out.println("Direccion para el reporte: ");
                String direccion = sc.next();
                itune.songs(direccion);
            } else if (opcion == 5) {
                 System.out.println("Codigo de la cancion: ");
                    int code = sc.nextInt();
                    itune.infoSong(code);
            }
        } while (opcion != 6);
    }
}
