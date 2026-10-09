import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaxSystem {
    private final Map<String, Person> persons = new LinkedHashMap<>();
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();


    public void addPerson(Person p) {
        if (persons.containsKey(p.getLicenseNumber())) {
            throw new IllegalArgumentException("Person already registered: " + p.getLicenseNumber());
        }
        persons.put(p.getLicenseNumber(), p);
    }

    public void deletePerson(String license) {
        Person p = persons.remove(license);
        if (p == null) {
            throw new IllegalArgumentException("Person not found: " + license);
        }
        for (Vehicle v : p.getVehicles()) {
            v.setOwner(null);
        }
    }


    public void addVehicle(Vehicle v) {
        if (vehicles.containsKey(v.getPlateNumber())) {
            throw new IllegalArgumentException("Vehicle already registered: " + v.getPlateNumber());
        }
        vehicles.put(v.getPlateNumber(), v);
    }

    public void deleteVehicle(String plate) {
        Vehicle v = vehicles.remove(plate);
        if (v == null) {
            throw new IllegalArgumentException("Vehicle not found: " + plate);
        }
        if (v.getOwner() != null) {
            v.getOwner().removeVehicle(v);
        }
    }

    public Vehicle searchVehicle(String plate) {
        return vehicles.get(plate);
    }

    public Person searchPerson(String license) {
        return persons.get(license);
    }

    public double sellVehicle(String plate, String license) {
        Vehicle v = searchVehicle(plate);
        if (v == null) {
            throw new IllegalArgumentException("Vehicle not found: " + plate);
        }
        if (v.getOwner() != null) {
            throw new IllegalStateException("Vehicle " + plate + " already has an owner. Use transferVehicle instead.");
        }
        Person buyer = searchPerson(license);
        if (buyer == null) {
            throw new IllegalArgumentException("Person not found: " + license);
        }
        buyer.addVehicle(v);
        v.setOwner(buyer);
        return v.calculateTax();
    }

    public void transferVehicle(String plate, String license) {
        Vehicle v = searchVehicle(plate);
        if (v == null) {
            throw new IllegalArgumentException("Vehicle not found: " + plate);
        }
        Person currentOwner = v.getOwner();
        if (currentOwner == null) {
            throw new IllegalStateException("Vehicle " + plate + " has no owner. Use sellVehicle instead.");
        }
        Person newOwner = searchPerson(license);
        if (newOwner == null) {
            throw new IllegalArgumentException("Person not found: " + license);
        }
        if (newOwner == currentOwner) {
            throw new IllegalArgumentException("The new owner is the same as the current owner.");
        }
        currentOwner.removeVehicle(v);
        newOwner.addVehicle(v);
        v.setOwner(newOwner);
    }

    public String generateAnnualTaxList() {
        StringBuilder taxList = new StringBuilder("===== ANNUAL VEHICLE TAX LIST =====\n");
        for (Person p : persons.values()) {
            List<Vehicle> ownerVehicles = p.getVehicles();
            if (!ownerVehicles.isEmpty()) {
                taxList.append("\n").append(p).append("\n");
                for (Vehicle v : ownerVehicles) {
                    double tax = v.calculateTax();
                    taxList.append(String.format("   %-55s %10.2f EUR%n", v, tax));
                }
                double total = p.getTotalTax();
                taxList.append(String.format("   %-55s %10.2f EUR%n", "TOTAL", total));
            }
        }
        return taxList.toString();
    }
}