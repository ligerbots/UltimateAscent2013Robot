/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.subsystems;
/**
 *
 * @author fitzpaj
 */
// create a class to fake an enum for state of Acquisition
public class AcquisitionState {

    public final int value;
    protected static final int SHOOT_VAL = 0;
    protected static final int FEED_VAL = 1;
    protected static final int PICKUP_VAL = 2;
    public static final AcquisitionState SHOOT = new AcquisitionState(SHOOT_VAL);
    public static final AcquisitionState FEED = new AcquisitionState(FEED_VAL);
    public static final AcquisitionState PICKUP = new AcquisitionState(PICKUP_VAL);

    private AcquisitionState(int value) {
        this.value = value;
    }
}
