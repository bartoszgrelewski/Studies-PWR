public class Romb extends Czworokat{

    private final double bok;
    private final double kat;
    private final double rad;
    public Romb(double bok, double kat){
        this.bok = bok;
        this.kat = kat;
        this.rad = Math.toRadians(kat);
    }

    @Override
    public double pole() {
        return bok*bok*Math.sin(rad);
    }

    @Override
    public double obwod() {
        return 4*bok;
    }
}
