public class Szesciokat extends Figura{

    private final double bok;
    public Szesciokat(double bok){
        this.bok = bok;
    }

    @Override
    public double pole() {
        return 6*bok*bok*Math.sqrt(3)/4;
    }

    @Override
    public double obwod() {
        return 6*bok;
    }
}
