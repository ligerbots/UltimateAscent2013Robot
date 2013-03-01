/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.subsystems;

/**
 *
 * @author cbf
 */
public class ScrewState {
        public int value = NEUTRAL_VALUE;
        public static final int NEUTRAL_VALUE = 0;
        public static final int LIFTING_VALUE = 1;
        public static final int LOWERING_VALUE = 2;
        
        public static final ScrewState NEUTRAL = new ScrewState(NEUTRAL_VALUE);
        public static final ScrewState LIFTING = new ScrewState(LIFTING_VALUE);
        public static final ScrewState LOWERING = new ScrewState(LOWERING_VALUE);
        
        private ScrewState(int value) {
            this.value = value;
        }
    
    }