/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Drive Train Mapping
    public static final int RIGHT_MASTER = 1;
    public static final int RIGHT_SLAVE = 2;
    public static final int LEFT_MASTER = 5;
    public static final int LEFT_SLAVE = 0;

    public static final int ARCADE_STICK = 0;
    
    private final double maxCIMVelocity = 5330/60;
    private final double gearRatio = 10.71;

    private final double velocityMax = (maxCIMVelocity * Math.PI * 6)/gearRatio;
    private final double vMax = 12;
    private final double k = vMax/velocityMax;

    public static final double FWD_P = 0.0125;
    public static final double FWD_I = 0;
    public static final double FWD_D = 0;
    public static final double FWD_F_R = .0881;
    public static final double FWD_F_L = .0862;




    public static final double ROT_P = .4;
    public static final double ROT_I = 0;
    public static final double ROT_D = 0;
    public static final double ROT_F = .05;

    public static final int TOP_ROLLER = 4;

    public static final int INTAKE_ROLLER = 7;
    public static final int HOPPER_ROLLER = 3;
    public static final int DISC_ROLLER = 6;
    
    public static final int LEFT_CHANNEL_A = 0;
    public static final int LEFT_CHANNEL_B = 1;
    public static final int RIGHT_CHANNEL_A = 2;
    public static final int RIGHT_CHANNEL_B = 3;
    public static final int LAUNCHER_CHANNEL_A = 4;
    public static final int LAUNCHER_CHANNEL_B = 5;
    
    public static final int COMPRESSOR = 0;
    public static final int INTAKE_LIFTER_A = 1;
    public static final int INTAKE_LIFTER_B = 0;
    public static final int CLIMB_A = 2;
    public static final int CLIMB_B = 3;    
    
	
}