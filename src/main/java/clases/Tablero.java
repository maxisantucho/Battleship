package clases;

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
            if(coordenadasBarco != null) {
                int longitudIngresada = coordenadasBarco.length;
                if (longitudIngresada != MisBarcos.valueOf(nombreBarco).getLongitud()) {
                    System.out.println("\nError. La longitud debe ser de " + MisBarcos.valueOf(nombreBarco).getLongitud() + ". Intente denuevo");
                    bandera = false;
                } else if (estaAdyacente(barco1)) {
                    System.out.println("\nError. las reglas del juego establecen que los barcos no pueden estar adyacentes entre sí. Intente denuevo");
                    bandera = false;
                } else {
                    insertarBarco(coordenadasBarco, barco1);
                    bandera = true;
                }
            } else {
                bandera = false;
            }
        } while(!bandera);
    }

    public boolean estaAdyacente(Barco barco) {
        String inicioBarco = barco.getInicioBarco();
        String finBarco = barco.getFinBarco();
        char inicioEmpieza = inicioBarco.charAt(0);
        int finEmpieza = Integer.parseInt(inicioBarco.substring(1));
        char inicioTermina = finBarco.charAt(0);
        int finTermina = Integer.parseInt(finBarco.substring(1));
        finEmpieza--;
        finTermina--;
        boolean bandera = true;
        int i = 0;
        int j = 0;
        while(bandera && i < campo.length) {
            while(bandera && j < campo[i].length) {

                j++;
            }
            i++;
        }
        return true;
    }

    public void insertarBarco(String[] coordenadas, Barco barco) {
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
        finEmpieza--;
        finTermina--;
        if(inicioEmpieza == inicioTermina) {
            insertarPorNum(inicioEmpieza, finEmpieza, finTermina);
        } else if(finEmpieza == finTermina) {
            insertarPorLetra(inicioEmpieza, finEmpieza, inicioTermina);
        }
    }

    public void insertarPorNum(char inicioEmpieza, int finEmpieza, int finTermina){
        int coorA = 0;
        for (int i = 0; i < columnas.length; i++) {
            if (inicioEmpieza == columnas[i]) {
                coorA = i;
            }
        }
        if (finEmpieza < finTermina) {
            for (int i = finEmpieza; i <= finTermina; i++) {
                campo[coorA][i] = '0';
            }
        } else if (finEmpieza > finTermina) {
            for (int i = finTermina; i <= finEmpieza; i++) {
                campo[coorA][i] = '0';
            }
        }
    }

    public void insertarPorLetra(char inicioEmpieza, int finEmpieza, char inicioTermina) {
        int coorA = 0;
        int coorB = 0;
        for (int i = 0; i < columnas.length; i++) {
            if (inicioEmpieza == columnas[i]) {
                coorA = i;
            } else if (inicioTermina == columnas[i]) {
                coorB = i;
            }
        }
        if (coorA < coorB) {
            for (int i = coorA; i <= coorB; i++) {
                campo[i][finEmpieza] = '0';
            }
        } else if (coorA > coorB) {
            for (int i = coorB; i <= coorA; i++) {
                campo[i][coorB] = '0';
            }
        }
    }

}
