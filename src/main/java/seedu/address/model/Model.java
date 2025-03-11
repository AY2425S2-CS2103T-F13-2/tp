package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;
import seedu.address.model.pastry.Pastry;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluates to true */
    Predicate<Client> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Pastry> PREDICATE_SHOW_ALL_PASTRIES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    //// Client-related methods ////

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Client person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Client target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Client person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Client target, Client editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Client> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Client> predicate);

    //// Pastry-related methods ////

    /**
     * Returns true if a pastry with the same identity as {@code pastry} exists in the address book.
     */
    boolean hasPastry(Pastry pastry);

    /**
     * Deletes the given pastry.
     * The pastry must exist in the address book.
     */
    void deletePastry(Pastry target);

    /**
     * Adds the given pastry.
     * {@code pastry} must not already exist in the address book.
     */
    void addPastry(Pastry pastry);

    /**
     * Replaces the given pastry {@code target} with {@code editedPastry}.
     * {@code target} must exist in the address book.
     * The pastry identity of {@code editedPastry} must not be the same as another existing pastry in the address book.
     */
    void setPastry(Pastry target, Pastry editedPastry);

    /** Returns an unmodifiable view of the filtered pastry list */
    ObservableList<Pastry> getFilteredPastryList();

    /**
     * Updates the filter of the filtered pastry list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPastryList(Predicate<Pastry> predicate);
}
