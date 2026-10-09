import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private final String licenseNumber;
    private String name;
    private String surname;
    private String address;
    private final List<Vehicle> vehicles;

    public Person(String licenseNumber, String name, String surname, String address) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void removeVehicle(Vehicle v) {
        vehicles.remove(v);
    }

    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    public double getTotalTax() {
        double total = 0;
        for (Vehicle v : vehicles) {
            total += v.calculateTax();
        }
        return total;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (" + licenseNumber + ")";
    }
}