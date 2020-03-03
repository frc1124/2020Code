/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Pneumatics;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private Pneumatics pneumatics;
  public static Compressor c;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    c = new Compressor(Constants.COMPRESSOR);
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    pneumatics = m_robotContainer.pneumatics;
    pneumatics.init();
    //c.setClosedLoopControl(true);
    c.start();
    // SmartDashboard.putNumber("FWD_P", 0);
    // SmartDashboard.putNumber("FWD_I", 0);
    // SmartDashboard.putNumber("FWD_D", 0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and   running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putBoolean("Compressor", c.enabled());
    SmartDashboard.putNumber("c", c.getCompressorCurrent());
    SmartDashboard.putBoolean("c2", c.getCompressorNotConnectedFault());
    SmartDashboard.putBoolean("c3", c.getCompressorNotConnectedStickyFault());
    SmartDashboard.putBoolean("c4", c.getCompressorShortedFault());
    SmartDashboard.putBoolean("c5", c.getCompressorShortedStickyFault());
    SmartDashboard.putBoolean("c6", c.getPressureSwitchValue());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    //pneumatics.stop();
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  
  // private final Drive drive;
  // private final Hopper hopper;
  // private final Launcher launcher;
  
  @Override
  public void autonomousInit() {
    // drive = new Drive();
    // hopper = new Hopper();
    // launcher = new Launcher();

    // CommandScheduler.getInstance().schedule(new Turn(drive, 180));
    // CommandScheduler.getInstance().schedule(new Move(drive, 120));
    // CommandScheduler.getInstance().schedule(new Launch(launcher));
    // // timer.delay(2.0);	
    // CommandScheduler.getInstance().schedule(new FeedBallz(hopper));
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    CommandScheduler.getInstance().schedule(m_robotContainer.getTeleopDrive());
    //c.start();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // c.start();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
