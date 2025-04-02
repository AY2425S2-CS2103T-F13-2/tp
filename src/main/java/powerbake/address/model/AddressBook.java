package powerbake.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import powerbake.address.commons.util.ToStringBuilder;
import powerbake.address.model.order.Order;
import powerbake.address.model.order.UniqueOrderList;
import powerbake.address.model.pastry.Pastry;
import powerbake.address.model.pastry.UniquePastryList;
import powerbake.address.model.person.Person;
import powerbake.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniquePastryList pastries;
    private final UniqueOrderList orders;

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
        orders = new UniqueOrderList();
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
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the pastry list with {@code pastries}.
     * {@code pastries} must not contain duplicate pastries.
     */
    public void setPastries(List<Pastry> pastries) {
        this.pastries.setPastries(pastries);
    }

    /**
     * Replaces the contents of the order list with {@code orders}.
     * {@code orders} must not contain duplicate pastries.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPastries(newData.getPastryList());
        setOrders(newData.getOrderList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
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
     * The pastry identity of {@code editedPastry} must not be the same as another existing pastry in the address book.
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

    /**
     * Returns true if an order with the same identity as {@code order} exists in the address book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Adds an order to the address book.
     * The order must not already exist in the address book.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the address book.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);
        orders.setOrder(target, editedOrder);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeOrder(Order key) {
        orders.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("pastries", pastries)
                .add("orders", orders)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pastry> getPastryList() {
        return pastries.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    public int getOriginalIndex(Person person) {
        requireNonNull(person);
        return getPersonList().indexOf(person);
    }

    public int getOriginalIndex(Pastry pastry) {
        requireNonNull(pastry);
        return getPastryList().indexOf(pastry);
    }

    public int getOriginalIndex(Order order) {
        requireNonNull(order);
        return getOrderList().indexOf(order);
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
        return persons.equals(otherAddressBook.persons)
            && pastries.equals(otherAddressBook.pastries)
            && orders.equals(otherAddressBook.orders);
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + pastries.hashCode() + orders.hashCode();
    }
}
