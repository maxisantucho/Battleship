package clases;

public enum MisBarcos {

    AIRCRAFT(5),
    BATTLESHIP(4),
    SUBMARINE(3),
    CRUISER(3),
    DESTROYER(2);

    private int longitud;

    private MisBarcos(int longitud) {
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }

}
