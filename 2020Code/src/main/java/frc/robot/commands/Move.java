
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
public class Move extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private PIDController fwdPID;
  private final Drive drive;

  private final double THROTTLE = .75;
  private double initialDistance;
  private double distance;
  private boolean isFinished = false;
  private double speed = 0;
  private final double TOLERANCE = 0.05;
 
 

  public Move(Drive drive, double distance) {
    this.distance = distance;
    this.drive = drive;

    this.initialDistance = drive.getAvgDistance();
    fwdPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D, Constants.FWD_F);

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

    initialDistance = drive.getAvgDistance();
    fwdPID.setSetpoint(distance);
    
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    speed = fwdPID.calculate(drive.getAvgDistance(), distance+initialDistance);
    drive.arcadeDrive(MathUtil.clamp(fwdPID.calculate(drive.getAvgDistance(), distance+initialDistance), -THROTTLE, THROTTLE),0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(speed) <= TOLERANCE;
  }
}
