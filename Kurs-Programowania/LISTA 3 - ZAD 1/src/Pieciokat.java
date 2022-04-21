public class Pieciokat extends Figura {
    private final double bok;
    public Pieciokat(double bok){
        this.bok = bok;
    }

    @Override
    public double pole() {
        return Math.sqrt((10*Math.sqrt(5)+25))*bok*bok/4;
    }

    @Override
    public double obwod() {
        return 5*bok;
    }
}


