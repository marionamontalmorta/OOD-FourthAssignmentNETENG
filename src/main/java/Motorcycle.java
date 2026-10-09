public class Motorcycle extends Vehicle {
    private static final double TAX_RATE = 0.10;

    private int engineDisplacement;

    public Motorcycle(String plateNumber, String maker, String model, int engineDisplacement) {
        super(plateNumber, maker, model);
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public double calculateTax() {
        return engineDisplacement * TAX_RATE;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public String toString() {
        return "Motorcycle " + super.toString() + " (" + engineDisplacement + " cc)";
    }
}