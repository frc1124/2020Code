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
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.cscore.UsbCamera;

import javax.annotation.ParametersAreNullableByDefault;

import edu.wpi.cscore.MjpegServer;
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
  public static Timer timer;
  public static Launcher launcher;
  public static Drive drive;
  public static Hopper hopper;
  public static Intake intake;
  public static DiscSpinner discspinner;
  public static UsbCamera camera;
  public static MjpegServer streamServer;
  

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
    pneumatics = RobotContainer.pneumatics;
    drive = RobotContainer.drive;
    launcher = RobotContainer.launcher;
    intake = RobotContainer.intake;
    discspinner = RobotContainer.discspinner;
    hopper = RobotContainer.hopper;
    timer = new Timer();
    pneumatics.init();
    c.start();
    camera = new UsbCamera("Targeting Camera", 0);
    camera.setBrightness(12);
    streamServer = new MjpegServer("Target Camera Stream", 1181);
    streamServer.setSource(camera);

    System.out.println(":afbefheshf" + (new Launch(launcher)).getInitTime());
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
    timer.start();
    //test code --> change it to auto1 (see Krish)

     ParallelCommandGroup autoShoot = new ParallelCommandGroup(
        (Command) new Launch(launcher, 6),
        new SequentialCommandGroup(
          (Command) new WaitCommand(3),
          (Command) new FeedBallz(hopper, 5)
        )
         );

    SequentialCommandGroup auto1 = new SequentialCommandGroup(
      (Command) new WaitCommand(6),
      (Command) new Move(drive, 40));
    // (Command)new Turn(drive, 180),);
    //(Command) new Move(drive, 40));
    SequentialCommandGroup auto2 = new SequentialCommandGroup(
      // new ParallelCommandGroup(
      //   (Command) new Launch(launcher),
      //   (Command) new FeedBallz(hopper)
      // ),
      //(Command) new Turn(drive, 29)
      (Command) new Move(drive, 18),
      (Command) new Launch(launcher, 3),
      (Command) new FeedBallz(hopper)
      // (Command) new Move(drive, -18),
      // (Command) new Turn(drive, -29),
      // (Command) new Move(drive, -40)
    );

    CommandScheduler.getInstance().schedule(autoShoot);
    CommandScheduler.getInstance().schedule(auto1);

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
    //CommandScheduler.getInstance().schedule(m_robotContainer.getTeleopDrive());
    //c.start();
    CommandScheduler.getInstance().schedule( (Command) new ArcadeDrive(drive, RobotContainer.j));
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // c.start();
    CommandScheduler.getInstance().run();
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
