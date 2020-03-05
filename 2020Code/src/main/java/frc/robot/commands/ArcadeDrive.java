/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drive;
import frc.robot.Robot;

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

  private final double THROTTLE = -.6;

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
    navx.zeroYaw();
    drive.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drive.drive(j.getY(), -1*j.getX());
    // drive.arcadeDrive(1,0);
    // double angle = j.getX() - navx.getYaw()/180.0;
    //if (angle < -1) angle++;
    //double maxVel = Math.pi()*6*
    // System.out.println(fwdPID.calculate(drive.getAvgVelocity(), j.getY()*drive.getAvgVelocity()));
    // drive.arcadeDrive(
    //    MathUtil.clamp(fwdPID.calculate(drive.getAvgVelocity(), j.getY()*drive.getAvgVelocity()), THROTTLE, -THROTTLE), 
    //    0  
    //   //  MathUtil.clamp(rotPID.calculate(angle, j.getX()), THROTTLE, -THROTTLE)
    // );
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
