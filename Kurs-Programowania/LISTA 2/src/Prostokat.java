public class Prostokat extends Czworokat{

    private final double bok1,bok2;

    public Prostokat(double bok1,double bok2){
        this.bok1 = bok1;
        this.bok2 = bok2;
    }

    @Override
    public double pole() {
        return bok1*bok2;
    }

    @Override
    public double obwod() {
        return 2*bok1+2*bok2;
    }
}
