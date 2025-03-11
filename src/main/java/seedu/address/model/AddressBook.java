package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniquePersonList;
import seedu.address.model.pastry.Pastry;
import seedu.address.model.pastry.UniquePastryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniquePastryList pastries;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        pastries = new UniquePastryList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Client> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the pastry list with {@code pastries}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPastries(List<Pastry> pastries) {
        this.pastries.setPastries(pastries);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPastries(newData.getPastryList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Client person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Client p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Client target, Client editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Client key) {
        persons.remove(key);
    }

    //// pastry-level operations

    /**
     * Returns true if a pastry with the same identity as {@code pastry} exists in the address book.
     */
    public boolean hasPastry(Pastry pastry) {
        requireNonNull(pastry);
        return pastries.contains(pastry);
    }

    /**
     * Adds a pastry to the address book.
     * The pastry must not already exist in the address book.
     */
    public void addPastry(Pastry p) {
        pastries.add(p);
    }

    /**
     * Replaces the given pastry {@code target} in the list with {@code editedPastry}.
     * {@code target} must exist in the address book.
     * The pastry identity of {@code editedPastry} must not be the same as another existing person in the address book.
     */
    public void setPastry(Pastry target, Pastry editedPastry) {
        requireNonNull(editedPastry);

        pastries.setPastry(target, editedPastry);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePastry(Pastry key) {
        pastries.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Client> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pastry> getPastryList() {
        return pastries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + pastries.hashCode();
    }
}
