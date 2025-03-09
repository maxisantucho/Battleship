package clases;

public class Main {

    public static void main(String[] args) {

        Tablero tablero1 = new Tablero();

        for (MisBarcos barco : MisBarcos.values()) {
            tablero1.mostrarCampo();
            tablero1.agregarBarco(barco.name());
        }

    }

}