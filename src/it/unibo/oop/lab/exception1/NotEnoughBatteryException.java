package it.unibo.oop.lab.exception1;

public class NotEnoughBatteryException extends IllegalStateException {

    private static final long serialVersionUID = 3216244578907543256L;

    /**
     * @return exception message
     */
    public String toString() {
        return "Battery is over!";
    }

    /**
     * @return toString
     */
    public String getMessage() {
        return this.toString();
    }
}
