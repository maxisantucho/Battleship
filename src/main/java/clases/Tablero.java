package clases;

import java.util.Scanner;

public class Tablero {

    private char[][] campo = new char[10][10];
    private int[] filas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private char[] columnas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public Tablero() {
        llenarCampo();
    }

    private void llenarCampo() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo.length; j++) {
                campo[i][j] = '~';
            }
        }
    }

    public void mostrarCampo() {
        System.out.println("\n");
        int columna = 0;
        for (int i = 0; i < campo.length; i++) {
            if (i == 0) {
                System.out.print(" ");
                for (int j = 0; j < filas.length; j++) {
                    System.out.print(" " + filas[j] + " ");
                }
                System.out.println();
            }
            System.out.print(columnas[columna] + " ");
            for (int j = 0; j < campo[i].length; j++) {
                System.out.print(" " + campo[i][j] + " ");
            }
            columna++;
            System.out.println();
        }
    }

    public void agregarBarco(String nombreBarco) {
        boolean bandera = true;
        do {
            Barco barco1 = new Barco(nombreBarco);
            barco1.crearBarco(columnas, nombreBarco);
            String[] coordenadasBarco = barco1.getCoordenadasBarco();
            int longitudIngresada = coordenadasBarco.length;
            if (longitudIngresada != MisBarcos.valueOf(nombreBarco).getLongitud()) {
                System.out.println("\nError. La longitud debe ser de " + MisBarcos.valueOf(nombreBarco).getLongitud() + ". Intente denuevo");
                bandera = false;
            } else {
                insertarBarco(coordenadasBarco, barco1);
            }
        } while(!bandera);
    }

    public void insertarBarco(String[] coordenadas, Barco barco) {
        Scanner sc = new Scanner(System.in);
        String inicio = barco.getInicioBarco();
        String fin = barco.getFinBarco();
        int longitud = coordenadas.length;
        insertarBarcoEnCampo(inicio, fin, longitud);
    }

    public void insertarBarcoEnCampo(String inicio, String fin, int longitud) {
        char inicioEmpieza = inicio.charAt(0);
        int finEmpieza = Integer.parseInt(inicio.substring(1));
        char inicioTermina = fin.charAt(0);
        int finTermina = Integer.parseInt(fin.substring(1));
        int coorA = 0;
        int coorB = 0;
        finEmpieza--;
        finTermina--;
        for (int i = 0; i < columnas.length; i++) {
            if (inicioEmpieza == columnas[i]) {
                coorA = i;
            } else if (inicioTermina == columnas[i]) {
                coorB = i;
            }
        }
        if (finEmpieza < finTermina) {
            for (int i = 0; i < longitud; i++) {
                campo[coorA][finEmpieza] = '0';
            }
        } else if (finEmpieza > finTermina) {
            for (int i = 0; i < longitud; i++) {
                campo[coorA][finTermina] = '0';
            }
        }
    }

}
