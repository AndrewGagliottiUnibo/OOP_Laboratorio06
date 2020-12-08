package it.unibo.oop.lab.collections2;

public class UserImpl implements User {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String username;
    /*
     * to be "lazily" initialized.
     */
    private int hash;

    public UserImpl(final String name, final String surname, final String user) {
        this(name, surname, user, -1);
    }

    public UserImpl(final String name, final String surname, final String user, final int userAge) {
        this.firstName = name;
        this.lastName = surname;
        this.age = userAge;
        this.username = user;
    }

    /**
     * @return firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * @return true if age is above 0
     */
    public boolean isAgeDefined() {
        return this.age > 0;
    }

    /**
     * @return true if objects are equal
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            /*
             * Performance optimization
             */
            return true;
        }
        if (o != null && getClass().equals(o.getClass())) {
            final UserImpl user = (UserImpl) o;
            return firstName.equals(user.getFirstName())
                    && lastName.equals(user.getLastName())
                    && username.equals(user.getUsername())
                    && age == user.getAge();
        }
        return false;
    }

    /**
     * @return hash code
     */
    //hashCode support
    public int hashCode() {
        /*
         * All fields are final and immutable: lazy initialization allowed.
         */
        if (hash == 0) {
            hash = firstName.hashCode() ^ lastName.hashCode() ^ username.hashCode() ^ age;
        }
        return hash;
    }

    /**
     * @return all data
     */
   public String toString() {
        return "[ " + this.firstName + " " + this.lastName + " " + this.age + " " + this.username + " ]";
    }
}
