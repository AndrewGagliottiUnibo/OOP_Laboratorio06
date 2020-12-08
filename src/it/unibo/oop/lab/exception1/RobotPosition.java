package it.unibo.oop.lab.exception1;

public class RobotPosition {

    private int x;
    private int y;

    public RobotPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @param x
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /** 
     * @param y
     */
    public void setY(final int y) {
        this.y = y;
    }
}
