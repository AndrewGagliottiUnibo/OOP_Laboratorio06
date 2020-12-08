package it.unibo.oop.lab.exception1;

public class Robot {

    private static final int MOVEMENT_DELTA = 1;
    private static final double MOVEMENT_DELTA_CONSUMPTION = 0.1;
    private static final double BATTERY_FULL = 100;
    private static final double BATTERY_EXHAUSTED = 0;

    private double batteryLevel;
    private final RobotEnvironment environment;
    private final String robotName;

    public Robot(final String robotName, final double batteryLevel) {
        this.batteryLevel = batteryLevel;
        this.environment = new RobotEnvironment(new RobotPosition(0, 0));
        this.robotName = robotName;
    }

    /**
     * 
     */
    public void moveUp() {
        this.moveToPosition(environment.getCurrPosX(), this.environment.getCurrPosY() + Robot.MOVEMENT_DELTA);
    }

    /**
     * 
     */
    public void moveDown() {
        this.moveToPosition(this.environment.getCurrPosX(), environment.getCurrPosY() - Robot.MOVEMENT_DELTA);
    }

    /**
     * 
     */
    public void moveLeft() {
        this.moveToPosition(this.environment.getCurrPosX() - Robot.MOVEMENT_DELTA, this.environment.getCurrPosY());
    }

    /**
     * 
     */
    public void moveRight() {
        this.moveToPosition(this.environment.getCurrPosX() + Robot.MOVEMENT_DELTA, this.environment.getCurrPosY());
    }

    /**
     * 
     */
    public void recharge() {
        this.batteryLevel = BATTERY_FULL;
    }

    /*
     * Per scelta di design ho deciso di lasciare pulito questo metodo e tirare l'eccezione
     * nel metodo consumeBattery()...questo garantisce una più facile lettura e coerenza nella
     * strutturazione del codice
     */
    private void moveToPosition(final int newX, final int newY) {
        if (this.isBatteryEnoughToMove()) {
            this.environment.move(newX, newY);
            this.consumeBatteryForMovement();
            this.log("Moved to position(" + newX + "," + newY + ").");
        }
    }

    /**
     * 
     */
    protected void consumeBatteryForMovement() {
        this.consumeBattery(Robot.MOVEMENT_DELTA_CONSUMPTION);
    }

    /**
     * @param amount
     * La chiamata al setter serve per fixare un bug della gestione dei double nel test della batteria.
     */
    protected void consumeBattery(final double amount) {
        if (batteryLevel >= amount) {
            this.batteryLevel -= amount;
        } else {
            this.setBatteryLevel();
            throw new NotEnoughBatteryException();
        }
    }

    /**
     * @return true if battery is enough
     */
    protected boolean isBatteryEnoughToMove() {
        return this.getBatteryLevel() >= Robot.MOVEMENT_DELTA_CONSUMPTION;
    }

    /**
     * @return a fixed number for the battery
     */
    public double getBatteryLevel() {
        return (double) Math.round(this.batteryLevel * 100) / 100;
    }

    /**
     * Creo un setter per sistemare un bug delle operazioni con i double per la verifica
     * della batteria rimanente nel test della batteria del robot.
     */
    public void setBatteryLevel() {
        this.batteryLevel = BATTERY_EXHAUSTED;
    }

    /**
     * @return environment
     */
    public RobotEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * 
     * @param msg
     */
    protected void log(final String msg) {
        System.out.println("[" + this.robotName + ":]" + msg);
    }
}
