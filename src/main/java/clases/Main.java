package clases;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        char[][] campo = new char[10][10];

        int[] filas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        char[] columnas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        llenarCampo(campo);

        mostrarCampo(campo, filas, columnas);

        colocarBarco(columnas);

    }

    public static void llenarCampo(char[][] campo) {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo.length; j++) {
                campo[i][j] = '~';
            }
        }
    }

    public static void mostrarCampo(char[][] campo, int[] filas, char[] columnas) {
        int columna = 0;
        for (int i = 0; i < campo.length; i++) {
            if(i == 0) {
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

    public static void colocarBarco(char[] columnas) {
        System.out.println("Ingrese cordenadas del barco:");
        System.out.print("> ");
        String barco = sc.nextLine();
        String[] coordenadas = barco.split(" ");
        if(coordenadas.length == 2) {
            String inicio = coordenadas[0].toUpperCase();
            String fin = coordenadas[1].toUpperCase();
            if(sonCoordValidas(inicio, fin)) {
                int longitud = 0;
                if(inicio.charAt(0) == fin.charAt(0)) {
                    int empieza = Integer.parseInt(inicio.substring(1));
                    int termina = Integer.parseInt(fin.substring(1));
                    if(empieza < termina) {
                        for (int i = empieza; i <= termina; i++) {
                            longitud++;
                        }
                        System.out.println("Length: " + longitud);
                        for (int i = empieza; i <= termina; i++) {
                            System.out.print(inicio.charAt(0) + "" + i + " ");
                        }
                    } else {
                        for (int i = empieza; i >= termina; i--) {
                            longitud++;
                        }
                        System.out.println("Length: " + longitud);
                        for (int i = empieza; i >= termina; i--) {
                            System.out.print(inicio.charAt(0) + "" + i + " ");
                        }
                    }
                } else if(inicio.charAt(1) == fin.charAt(1)){
                    char empieza = inicio.charAt(0);
                    char termina = fin.charAt(0);
                    int primer = 0;
                    int ultimo = 0;
                    for (int i = 0; i < columnas.length; i++) {
                        if(empieza == columnas[i]) {
                            primer = i;
                        } else if(termina == columnas[i]) {
                            ultimo = i;
                        }
                    }
                    if(primer < ultimo) {
                        for (int i = primer; i <= ultimo; i++) {
                            longitud++;
                        }
                        System.out.println("Length: " + longitud);
                        for (int i = primer; i <= ultimo; i++) {
                            System.out.print(columnas[i] + inicio.substring(1) + " ");
                        }
                    } else {
                        for (int i = primer; i >= ultimo; i--) {
                            longitud++;
                        }
                        System.out.println("Length: " + longitud);
                        for (int i = primer; i >= ultimo; i--) {
                            System.out.print(columnas[i] + inicio.substring(1) + " ");
                        }
                    }
                }
            } else {
                System.out.println("Error!");
            }
        } else {
            System.out.println("Error!");
        }
    }

    public static boolean sonCoordValidas(String inicio, String fin) { //A1 A4
        if(!esDatoValido(inicio) || !esDatoValido(fin)) {
            return false;
        }
        String inicioFila = inicio.substring(0, 1);
        String inicioColumna = inicio.substring(1);
        String finFila = fin.substring(0, 1);
        String finColumna = fin.substring(1);
        return (inicioFila.equalsIgnoreCase(finFila) || inicioColumna.equalsIgnoreCase(finColumna));
    }

    public static boolean esDatoValido(String coordenada) {
        if(coordenada.length() == 2) {
            char columna = coordenada.charAt(0);
            char fila = coordenada.charAt(1);
            return (columna >= 'A' && columna <= 'J' && fila >= '1' && fila <= '9');
        } else if(coordenada.length() == 3) {
            char columna = coordenada.charAt(0);
            String fila = coordenada.charAt(1) + "" + coordenada.charAt(2);
            return (columna >= 'A' && columna <= 'J' && fila.equalsIgnoreCase("10"));
        }
        return false;
    }

}