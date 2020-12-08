package it.unibo.oop.lab.exception1;

public class RobotEnvironment {

    /**
     * 
     */
    public static final int WORLD_X_UPPER_LIMIT = 50;
    /**
     * 
     */
    public static final int WORLD_X_LOWER_LIMIT = 0;
    /**
     * 
     */
    public static final int WORLD_Y_UPPER_LIMIT = 80;
    /**
     * 
     */
    public static final int WORLD_Y_LOWER_LIMIT = 0;

    private final RobotPosition position;

    public RobotEnvironment(final RobotPosition pos) {
        this.position = pos;
    }

    /**
     * Se non posso muovermi tiro un'eccezione.
     * 
     * @param newX
     * @param newY
     */
    public void move(final int newX, final int newY) {
        if (newX >= RobotEnvironment.WORLD_X_LOWER_LIMIT
                && newX <= RobotEnvironment.WORLD_X_UPPER_LIMIT
                && newY >= RobotEnvironment.WORLD_Y_LOWER_LIMIT
                && newY <= RobotEnvironment.WORLD_Y_UPPER_LIMIT) {
            this.position.setX(newX);
            this.position.setY(newY);
        } else {
            throw new PositionOutOfBoundException(newX, newY);
        }
    }

    /**
     * @return X
     */
    public int getCurrPosX() {
        return this.position.getX();
    }

    /**
     * @return Y
     */
    public int getCurrPosY() {
        return this.position.getY();
    }
}
