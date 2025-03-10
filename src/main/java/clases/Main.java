package clases;

public class Main {

    public static void main(String[] args) {

        Tablero tablero1 = new Tablero();

        tablero1.mostrarCampo();

        for (MisBarcos barco : MisBarcos.values()) {
            tablero1.agregarBarco(barco.name());
            tablero1.mostrarCampo();
        }

    }

}