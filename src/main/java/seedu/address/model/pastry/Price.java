package seedu.address.model.pastry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Pastry's price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Prices should be of the form '3', '6.5' or '123.57'";

    public static final String VALIDATION_REGEX = "^\\d+(\\.\\d{1,2})?$";
    public final String amount;

    /**
     * Constructs a {@code Price}.
     *
     * @param phone A valid price.
     */
    public Price(String amount) {
        requireNonNull(amount);
        checkArgument(isValidPrice(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.amount;
    }
}
