package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.pastry.Pastry;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_PASTRY = "Pastries list contains duplicate pastry(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedPastry> pastries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and pastries.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("pastries") List<JsonAdaptedPastry> pastries) {
        if (persons != null) {
            this.persons.addAll(persons);
        }
        if (pastries != null) {
            this.pastries.addAll(pastries);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        pastries.addAll(source.getPastryList().stream().map(JsonAdaptedPastry::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        // Add persons
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Client person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        // Add pastries
        for (JsonAdaptedPastry jsonAdaptedPastry : pastries) {
            Pastry pastry = jsonAdaptedPastry.toModelType();
            if (addressBook.hasPastry(pastry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PASTRY);
            }
            addressBook.addPastry(pastry);
        }

        return addressBook;
    }

}
