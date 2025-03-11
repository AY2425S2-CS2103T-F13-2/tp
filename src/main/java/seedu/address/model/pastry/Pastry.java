package seedu.address.model.pastry;

import seedu.address.model.client.Client;

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

    /**
     * Returns true if both pastries have the same name.
     * This defines a weaker notion of equality between two pastries.
     */
    public boolean isSamePastry(Pastry otherPastry) {
        if (otherPastry == this) {
            return true;
        }

        return otherPastry != null
                && otherPastry.getName().equals(getName());
    }
}
