/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;

/**
 *
 * @author Administrator
 * 
 * Helper class for managing a CAN-based Jaguar
 * 
 * 
 */
public class CANJag {
    public CANJaguar m_jag;
    public double m_voltage;
    public double m_current;
    public String m_description;
    public String m_lasterror = null;
    public boolean m_status = false;
    public double m_busvoltage;
    
    public CANJag(int jagnum, String descr) {
        m_description = descr;
        System.out.print("CAN init " + jagnum + " " + descr);
        try {
            m_jag = new CANJaguar(jagnum);
            System.out.println(" OK");
        } catch (CANTimeoutException ex) {
            m_lasterror = ex.getMessage();
            System.out.println(" FAILED: " + m_lasterror);
            m_status = false;
        }
        m_status = true;
    }
    
    public CANJaguar getJag()
    {
        return m_jag;
    }
    
    public double getVoltage()
    {
        try {
            m_voltage = m_jag.getOutputVoltage();
        } catch  (CANTimeoutException ex) {
            m_lasterror = ex.getMessage();
            System.out.println(m_lasterror + " " + m_description);
            m_status = false;
            return 0.0;
        }
        return m_voltage;
    }
    
    public double getCurrent() {            
        try {
            m_current = m_jag.getOutputCurrent();
        } catch  (CANTimeoutException ex) {
            m_lasterror = ex.getMessage();
            System.out.println(m_lasterror + " " + m_description);
            m_status = false;
            return 0.0;
        }
        m_status = true;
        return m_current;
    }
    
    public double getBusVoltage()
    {
        try {
            m_busvoltage = m_jag.getBusVoltage();
        } catch  (CANTimeoutException ex) {
            m_lasterror = ex.getMessage();
            System.out.println(m_lasterror + " " + m_description);
            m_status = false;
            return 0.0;
        }
        return m_busvoltage;
    }
    
    public boolean getStat() {
        return m_status;
    }
    
    public String getDescr() {
        return m_description;
    }
            
}


