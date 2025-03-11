package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.pastry.Pastry;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Client> getPersonList();

    /**
     * Returns an unmodifiable view of the pastries list.
     * This list will not contain any duplicate pastries.
     */
    ObservableList<Pastry> getPastryList();

}
