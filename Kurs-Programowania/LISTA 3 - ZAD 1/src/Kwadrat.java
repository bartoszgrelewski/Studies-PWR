public class Kwadrat extends Figura{

    private final double bok;

    public Kwadrat(double bok){
        this.bok = bok;
    }

    @Override
    public double pole() {
        return bok*bok;
    }

    @Override
    public double obwod() {
        return 4*bok;
    }
}
