package it.unibo.oop.lab.exception1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public final class BaseRobotTest {

    @Test
    public void testRobotMovementBase() {

        final Robot r1 = new Robot("SimpleRobot", 100);
        assertEquals("[CHECKING ROBOT INIT POS X]", 0, r1.getEnvironment().getCurrPosX());
        assertEquals("[CHECKING ROBOT INIT POS Y]", 0, r1.getEnvironment().getCurrPosY());

        try {
            for (int i = 0; i < RobotEnvironment.WORLD_X_UPPER_LIMIT; i++) {
                r1.moveRight();
            } 

            r1.moveRight();
            fail("Exception must have been thrown before");
        } catch (PositionOutOfBoundException exception) {
            System.out.println(exception.getMessage());
        } catch (NotEnoughBatteryException exception) {
            fail("This Exception wasn't expected");
        }

        assertEquals("[MOVING RIGHT ROBOT POS X]", RobotEnvironment.WORLD_X_UPPER_LIMIT, r1.getEnvironment().getCurrPosX());
        assertEquals("[MOVING RIGHT ROBOT POS Y]", 0, r1.getEnvironment().getCurrPosY());

        try {
            for (int i = 0; i < RobotEnvironment.WORLD_Y_UPPER_LIMIT; i++) {
                r1.moveUp();
            }

            r1.moveUp();
            System.out.println(r1.getBatteryLevel());
            fail("Exception must have been thrown before");
        } catch (PositionOutOfBoundException exception) {
            System.out.println(exception.getMessage());
        } catch (NotEnoughBatteryException exception) {
            fail("This Exception wasn't expected");
        }

        assertEquals("[MOVING RIGHT ROBOT POS X]", RobotEnvironment.WORLD_X_UPPER_LIMIT, r1.getEnvironment().getCurrPosX());
        assertEquals("[MOVING RIGHT ROBOT POS Y]", RobotEnvironment.WORLD_Y_UPPER_LIMIT, r1.getEnvironment().getCurrPosY());
    }

    @Test
    public void testRobotBatteryBase() {
        final Robot r2 = new Robot("SimpleRobot2", 20);

        try {
           while (r2.getBatteryLevel() > 0) {
                r2.moveUp();
                r2.moveDown();
            }

        } catch (PositionOutOfBoundException exception) {
            fail("This Exception wasn't expected");
        } catch (NotEnoughBatteryException exception) {
            System.out.println(exception.getMessage());
        }

        /*
         * Ho dovuto inserire il setter a 0 per la batteria per far sì che la asserEquals() funzionasse
         */
        assertEquals(0d, r2.getBatteryLevel(), 0);
        assertEquals("[CHECKING ROBOT INIT POS Y]", 0, r2.getEnvironment().getCurrPosY());
        r2.recharge();
        assertEquals(100, r2.getBatteryLevel(), 0);
    }
}
