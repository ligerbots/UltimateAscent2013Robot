/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Administrator
 */
public class CANJags {
    public CANJag jags[];
    
    public CANJags(int number) {
        // CAN bus numberin starts at 2, so we allocate and array from 2 to 9
        // so we can index directly by CAN ID and not worry about +/- errors
        jags = new CANJag[number+2];
    }
    
    public CANJaguar init(int i, String description) {
       CANJag j = new CANJag(i, description);
       jags[i] = j;
       return j.getJag();
    }
    
    public void UpdateDashboard()
    {
        // remember, CAN IDs start at 2. Array will actually be 10 long,
        // so 9 is our highest index
        for(int i=2; i!=jags.length; i++) {
          CANJag j = jags[i];
          SmartDashboard.putBoolean(Integer.toString(i), j.getStat());
          SmartDashboard.putNumber("V:"+ j.getDescr(), j.getVoltage());
          SmartDashboard.putNumber("A:"+ j.getDescr(), j.getCurrent());
        }
    }
    
}
