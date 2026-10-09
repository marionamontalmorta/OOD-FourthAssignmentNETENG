public class Main {
    public static void main(String[] args) {
        TaxSystem system = new TaxSystem();

        system.addPerson(new Person("11111111A", "Anna", "Puig", "Carrer Major 1, Girona"));
        system.addPerson(new Person("22222222B", "Marc", "Soler", "Rambla Nova 20, Tarragona"));
        system.addPerson(new Person("33333333C", "Laia", "Vidal", "Passeig de Gracia 5, Barcelona"));

        system.addVehicle(new Car("1234ABC", "Seat", "Ibiza", FuelType.PETROL, 120));
        system.addVehicle(new Car("5678DEF", "Volkswagen", "Golf", FuelType.DIESEL, 110));
        system.addVehicle(new Car("9012GHI", "Toyota", "Corolla", FuelType.HYBRID, 90));
        system.addVehicle(new Motorcycle("3456JKL", "Honda", "CBR", 600));
        system.addVehicle(new Car("7890MNO", "Renault", "Clio", FuelType.PETROL, 115)); // stays at the dealership

        System.out.printf("Sold 1234ABC to Anna. Annual tax: %.2f EUR%n", system.sellVehicle("1234ABC", "11111111A"));
        System.out.printf("Sold 3456JKL to Anna. Annual tax: %.2f EUR%n", system.sellVehicle("3456JKL", "11111111A"));
        System.out.printf("Sold 5678DEF to Marc. Annual tax: %.2f EUR%n", system.sellVehicle("5678DEF", "22222222B"));
        System.out.printf("Sold 9012GHI to Marc. Annual tax: %.2f EUR%n", system.sellVehicle("9012GHI", "22222222B"));

        system.transferVehicle("9012GHI", "11111111A");
        System.out.println("Transferred 9012GHI from Marc to Anna.");

        system.searchPerson("22222222B").setAddress("Carrer Nou 3, Reus");

        Vehicle found = system.searchVehicle("9012GHI");
        System.out.println("\nSearch 9012GHI -> " + found + ", owner: " + found.getOwner());
        Vehicle atDealer = system.searchVehicle("7890MNO");
        System.out.println("Search 7890MNO -> " + atDealer + ", owner: "
                + (atDealer.getOwner() == null ? "none (at the dealership)" : atDealer.getOwner()));
        Person marc = system.searchPerson("22222222B");
        System.out.println("Search 22222222B -> " + marc + ", address: " + marc.getAddress()
                + ", vehicles: " + marc.getVehicles());
        System.out.println("Search 0000XXX -> " + system.searchVehicle("0000XXX"));

        System.out.println("\n--- Error cases ---");
        tryAction("Sell a vehicle that already has an owner",
                () -> system.sellVehicle("1234ABC", "22222222B"));
        tryAction("Transfer a vehicle with no owner",
                () -> system.transferVehicle("7890MNO", "22222222B"));
        tryAction("Transfer to the current owner",
                () -> system.transferVehicle("1234ABC", "11111111A"));
        tryAction("Transfer to a person that does not exist",
                () -> system.transferVehicle("1234ABC", "99999999Z"));
        tryAction("Add a duplicated plate number",
                () -> system.addVehicle(new Motorcycle("1234ABC", "Yamaha", "MT-07", 689)));

        System.out.println();
        System.out.println(system.generateAnnualTaxList());
    }

    private static void tryAction(String description, Runnable action) {
        try {
            action.run();
            System.out.println(description + " -> OK");
        } catch (RuntimeException e) {
            System.out.println(description + " -> ERROR: " + e.getMessage());
        }
    }
}