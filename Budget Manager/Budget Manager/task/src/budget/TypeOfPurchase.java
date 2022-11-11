package budget;

enum TypeOfPurchase {
    FOOD("Food", 0),
    CLOTHES("Clothes", 0),
    ENTERTAINMENT("Entertainment", 0),
    OTHER("Other", 0),
    ALL("All", 0);

    private final String name;
    private double sum;

    TypeOfPurchase(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return this.name;
    }

    public void setSum(double price) {
        this.sum += price;
    }

    public double getSum() {
        return this.sum;
    }
}
