/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import java.util.*;
import edu.wpi.first.wpilibj.SerialPort; 

import frc.robot.Constants;
import frc.robot.subsystems.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;



/**
 * An example command that uses an example subsystem.
 */
public class ArcadeDrive extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private PIDController fwdPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D);
  private PIDController rotPID = new PIDController(Constants.ROT_P, Constants.ROT_I, Constants.ROT_D);

  private AHRS navx;

  private final Drive drive;
  private final Joystick j;

  private final double throttle = .75;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArcadeDrive(Drive drive, Joystick j) {
    this.drive = drive;
    this.j = j;
    navx = drive.getNavxInstance(); 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // j.getRawButton(Constants.);
    // drive.arcadeDrive(j.getY() * throttle, j.getX()* throttle);
    //TODO: find getVelocity{X,Y,Z} and zero angle
    double a = j.getX() - navx.getYaw()/180;
    if (a < -1) a++;
    drive.arcadeDrive(
       MathUtil.clamp(fwdPID.calculate(navx.getVelocityY(), j.getY()), throttle, -throttle), 
       MathUtil.clamp(rotPID.calculate(navx.getYaw()/180, j.getX()), throttle, -throttle)
    );

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
