package seedu.address.model.pastry;

public class Pastry {
    private final Name name;
    private final Price price;

    public Pastry(Name name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Name getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
