
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;
/**
 * An example command that uses an example subsystem.
 */
public class Turn extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private PIDController rotPID;
  private double desiredAngle;
  private final Drive drive;

  private final double THROTTLE = .75;
  private double initialAngle;
  private double angle;
  private boolean isFinished = false;
  private final double TOLERANCE = .5;
 
 

  public Turn(Drive drive, double angle) {
    this.angle = angle;
    this.drive = drive;

    this.initialAngle = drive.getNavxInstance().getYaw();
    desiredAngle = initialAngle + angle;
    while(desiredAngle > 180) {
      desiredAngle -= 180;
    }
    while(desiredAngle < -180) {
      desiredAngle += 180;
    }
   
    rotPID = new PIDController(Constants.ROT_P, Constants.ROT_I, Constants.ROT_D, Constants.ROT_F);

    // fwdPID = new PIDController(SmartDashboard.getNumber("FWD_P", 0),
    //                            SmartDashboard.getNumber("FWD_I", 0),
    //                            SmartDashboard.getNumber("FWD_D", 0),
    //                            SmartDashboard.getNumber("FWD_F", Constants.FWD_F)
    // );

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialAngle = drive.getNavxInstance().getYaw();
    rotPID.setSetpoint(angle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rot = rotPID.calculate(drive.getNavxInstance().getYaw(), desiredAngle);
    drive.arcadeDrive(0, rot);
    // MathUtil.clamp(lSpeed, -THROTTLE, THROTTLE,0);
    // MathUtil.clamp(rSpeed , -THROTTLE, THROTTLE,0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(drive.getNavxInstance().getYaw() - desiredAngle) <= TOLERANCE;
  }
}
