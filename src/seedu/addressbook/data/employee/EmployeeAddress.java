package seedu.addressbook.data.employee;

import seedu.addressbook.data.exception.IllegalValueException;

//@@author kianhong95
/**
 * Represents an Employee's address in the Rms.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class EmployeeAddress {

    public static final String EXAMPLE = "Clementi Ave 2, Blk 543 #13-12";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Employee addresses can be in any format"
                                                            + " but cannot be more than 50 characters";
    public static final String ADDRESS_VALIDATION_REGEX = ".{1,50}+";

    public final String value;

    /**
     * Empty constructor
     */
    public EmployeeAddress() {
        this.value = "";
    }

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public EmployeeAddress(String address) throws IllegalValueException {
        String trimmedAddress = address.trim();
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = trimmedAddress;
    }

    /**
     * Returns true if a given string is a valid Employee email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmployeeAddress // instanceof handles nulls
                && this.value.equals(((EmployeeAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
