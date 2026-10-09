public enum FuelType {
    PETROL(1.4),
    DIESEL(1.8),
    HYBRID(1.2);

    private final double pricePerGram;

    FuelType(double pricePerGram) {
        this.pricePerGram = pricePerGram;
    }

    public double getPricePerGram() {
        return pricePerGram;
    }
}