package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.pastry.Name;
import seedu.address.model.pastry.Pastry;
import seedu.address.model.pastry.Price;

/**
 * Jackson-friendly version of {@link Pastry}.
 */
class JsonAdaptedPastry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pastry's %s field is missing!";

    private final String name;
    private final String price;

    /**
     * Constructs a {@code JsonAdaptedPastry} with the given pastry details.
     */
    @JsonCreator
    public JsonAdaptedPastry(@JsonProperty("name") String name, @JsonProperty("price") String price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Converts a given {@code Pastry} into this class for Jackson use.
     */
    public JsonAdaptedPastry(Pastry source) {
        this.name = source.getName().toString();
        this.price = source.getPrice().toString(); // BigDecimal to String for JSON serialization
    }

    /**
     * Converts this Jackson-friendly adapted pastry object into the model's {@code Pastry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pastry.
     */
    public Pastry toModelType() throws IllegalValueException {
        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (this.name.trim().isEmpty()) {
            throw new IllegalValueException("Pastry name cannot be empty.");
        }

        // Handle the price, converting from String to BigDecimal
        if (this.price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "price"));
        }
        final Name modelName;
        final Price modelPrice;

        try {
            modelName = new Name(name);
            modelPrice = new Price(price);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Price must be a valid number.");
        }

        return new Pastry(modelName, modelPrice);
    }
}
