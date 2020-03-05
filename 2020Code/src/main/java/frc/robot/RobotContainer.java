/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.XboxController;
// import frc.robot.commands.ExampleCommand;
// import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Compressor;
import java.util.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Robot;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Drive drive = new Drive();
  public static final Joystick j = new Joystick(Constants.ARCADE_STICK); // Creates a joystick on port 1
  public static final Joystick j2 = new Joystick(Constants.COMMAND_STICK);
  public static final Drive drive = new Drive();
  public static final ArcadeDrive arcadeDrive = new ArcadeDrive(drive, j);
  public static final Intake intake = new Intake();
  public static final Hopper hopper = new Hopper();
  public static final Launcher launcher = new Launcher();
  public static final DiscSpinner discspinner = new DiscSpinner();
  public static final Pneumatics pneumatics = new Pneumatics(Robot.c);
  


  private boolean isRetracted = true;

 
  public Command getTeleopDrive() {
      return arcadeDrive;
  }

  public static HashMap<String, Button> logitechMap = new HashMap<String, Button>();

  public static Button getKey(String key) { 
    logitechMap.put("A", new JoystickButton(j, 1));
    logitechMap.put("B", new JoystickButton(j, 2));
    logitechMap.put("X", new JoystickButton(j, 3));
    logitechMap.put("Y", new JoystickButton(j, 4));
    logitechMap.put("LB", new JoystickButton(j, 5));
    logitechMap.put("RB", new JoystickButton(j, 6));
    logitechMap.put("Back", new JoystickButton(j, 7));
    logitechMap.put("A", new JoystickButton(j, 1));
    return logitechMap.get(key);
  }
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    // JAY: this is how you do it
    // button1.whenPressed(new ExampleCommand());
    getKey("Y").whileHeld(new SuccBallz(intake));
    getKey("X").whileHeld(new FeedBallz(hopper));
    getKey("A").toggleWhenPressed(new Launch(launcher, 1), true);
  
    //getKey("LB").whileHeld(new SpinDisc(discspinner));
    //getKey("LB").whenPressed(new ExtendClimb(pneumatics));
    //getKey("RB").whenPressed(new RetractClimb(pneumatics));
    //getKey("RB").whileHeld(new );
    //rohan is sexy (ps. Rohan wrote that)
    getKey("B").whileHeld(new SpinDisc(discspinner));
    // getKey("Back").whenPressed(new Move(drive, 132));
    //getKey("B").whenPressed(new Turn(drive, 90));
    getKey("Back").toggleWhenPressed(new Launch(launcher, -1), true);
  }





  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
