package clases;

import java.util.Scanner;

public class Barco {

    private String nombre;
    private String[] coordenadasBarco;
    private String inicioBarco;
    private String finBarco;

    public Barco(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getCoordenadasBarco() {
        return coordenadasBarco;
    }

    public String getInicioBarco() {
        return inicioBarco;
    }

    public String getFinBarco() {
        return finBarco;
    }

    public void crearBarco(char[] columnas, String nombre) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngrese coordenadas del barco " + nombre + ":");
        System.out.print("> ");
        String barco = sc.nextLine();
        String[] coordenadas = barco.split(" ");
        if(coordenadas.length == 2) {
            colocarCoordenadas(columnas, coordenadas);
        } else {
            System.out.println("Error!");
        }
    }

    public void colocarCoordenadas(char[] columnas, String[] coordenadas) {
        String inicio = coordenadas[0].toUpperCase();
        String fin = coordenadas[1].toUpperCase();
        if(sonCoordValidas(inicio, fin)) {
            int longitud = 0;
            if(inicio.charAt(0) == fin.charAt(0)) {
                int empieza = Integer.parseInt(inicio.substring(1));
                int termina = Integer.parseInt(fin.substring(1));
                colocarBarcoPorNum(empieza, termina, longitud, inicio);
            } else if(inicio.charAt(1) == fin.charAt(1)){
                char empieza = inicio.charAt(0);
                char termina = fin.charAt(0);
                colocarBarcoPorLetra(columnas, empieza, termina, longitud, inicio);
            }
        } else {
            System.out.println("Error!");
        }
    }

    public void colocarBarcoPorNum(int empieza, int termina, int longitud, String inicio) {
        if(empieza < termina) {
            for (int i = empieza; i <= termina; i++) {
                longitud++;
            }
            coordenadasBarco = cargarBarco(longitud);
            System.out.println("Length: " + longitud);
            for (int i = empieza; i <= termina; i++) {
                System.out.print(inicio.charAt(0) + "" + i + " ");
            }
        } else {
            for (int i = empieza; i >= termina; i--) {
                longitud++;
            }
            coordenadasBarco = cargarBarco(longitud);

            System.out.println("Length: " + longitud);
        }
        inicioBarco = inicio + "";
        finBarco = inicio.substring(0, 1) + "" + termina;
    }

    public String[] cargarBarco(int longitud) {
        String [] barcoCoordenadas = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            barcoCoordenadas[i] = "0";
        }
        return barcoCoordenadas;
    }

    public void colocarBarcoPorLetra(char[] columnas, char empieza, char termina, int longitud, String inicio) {
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
            coordenadasBarco = cargarBarco(longitud);
            System.out.println("Length: " + longitud);
            for (int i = primer; i <= ultimo; i++) {
                System.out.print(columnas[i] + inicio.substring(1) + " ");
            }
        } else {
            for (int i = primer; i >= ultimo; i--) {
                longitud++;
            }
            coordenadasBarco = cargarBarco(longitud);
            System.out.println("Length: " + longitud);
            for (int i = primer; i >= ultimo; i--) {
                System.out.print(columnas[i] + inicio.substring(1) + " ");
            }
        }
        inicioBarco = empieza + "" + inicio.substring(1);
        finBarco = termina + "" + inicio.substring(1);
    }

    public boolean sonCoordValidas(String inicio, String fin) {
        if(!esDatoValido(inicio) || !esDatoValido(fin)) {
            return false;
        }
        String inicioFila = inicio.substring(0, 1);
        String inicioColumna = inicio.substring(1);
        String finFila = fin.substring(0, 1);
        String finColumna = fin.substring(1);
        return (inicioFila.equalsIgnoreCase(finFila) || inicioColumna.equalsIgnoreCase(finColumna));
    }

    public boolean esDatoValido(String coordenada) {
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
