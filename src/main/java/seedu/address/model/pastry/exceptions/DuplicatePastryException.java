package seedu.address.model.pastry.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePastryException extends RuntimeException {
    public DuplicatePastryException() {
        super("Operation would result in duplicate pastry");
    }
}
