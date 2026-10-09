
public class Car extends Vehicle {
    private FuelType fuelType;
    private double co2Emissions;

    public Car(String plateNumber, String maker, String model,
               FuelType fuelType, double co2Emissions) {
        super(plateNumber, maker, model);
        this.fuelType = fuelType;
        this.co2Emissions = co2Emissions;
    }

    @Override
    public double calculateTax() {
        return co2Emissions * fuelType.getPricePerGram();
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(double co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    @Override
    public String toString() {
        return "Car " + super.toString() + " (" + fuelType + ", " + co2Emissions + " g CO2)";
    }
}