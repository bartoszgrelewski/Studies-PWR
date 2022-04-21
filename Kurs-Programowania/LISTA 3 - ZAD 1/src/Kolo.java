public class Kolo extends Figura {

    private final double promien;

    public Kolo(double promien){
        this.promien = promien;
    }

    @Override
    public double pole() {
        return Math.PI*promien*promien;
    }

    @Override
    public double obwod() {
        return Math.PI*2*promien;
    }
}

