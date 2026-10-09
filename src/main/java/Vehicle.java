public abstract class Vehicle {
    private final String plateNumber;
    private String maker;
    private String model;
    private Person owner;               // null when the vehicle has no owner (e.g. at the dealership)

    public Vehicle(String plateNumber, String maker, String model) {
        this.plateNumber = plateNumber;
        this.maker = maker;
        this.model = model;
        this.owner = null;
    }

    public abstract double calculateTax();

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return plateNumber + " - " + maker + " " + model;
    }
}