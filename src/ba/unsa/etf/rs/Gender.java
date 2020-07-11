package ba.unsa.etf.rs;

public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        return super.toString().substring(0, 1).toUpperCase().concat(super.toString().substring(1).toLowerCase());
    }
}
