package exercise.three.banking.holder;

public abstract class AccountHolder {
    private final int idNumber;

    /**
     * @param idNumber The government-issued ID used during account setup.
     */
    protected AccountHolder(int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return private int {@link AccountHolder#idNumber}
     */
    public int getIdNumber() {
        return idNumber;
    }
}
