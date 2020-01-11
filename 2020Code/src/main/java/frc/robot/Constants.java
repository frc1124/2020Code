/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;

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
    public static final int RIGHT_MASTER = 0;
    public static final int RIGHT_SLAVE = 15;
    public static final int LEFT_MASTER = 1;
    public static final int LEFT_SLAVE = 14;

    //Launcher Mapping
    public static final int TOP_ROLLER = 3;
    public static final int BOTTOM_ROLLER = 4; //This is for the shooter design


    public static final int ARCADE_STICK = 0;
    // public static HashMap<String, Integer> controller = new HashMap<String, Integer>();
    // controller.put("A", 0);

    public JoystickButton[] jbuttons = [
        new JoystickButton()


    ];
    
}
