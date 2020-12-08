package it.unibo.oop.lab.exception1;

public class PositionOutOfBoundException extends IllegalStateException {

    private static final long serialVersionUID = 5322262957171100503L;
    private final int x;
    private final int y;

    public PositionOutOfBoundException(final int initX, final int initY) {
        super();
        this.x = initX;
        this.y = initY;
    }

    /**
     * @return exception message
     */
    @Override
    public String toString() {
        return "Can not move to pos(" + this.x + ", " + this.y + "), out of bounds";
    }

    /**
     * @return toString
     */
    @Override
    public String getMessage() {
        return this.toString();
    }
}
