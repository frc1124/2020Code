
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

  private PIDController fwdRPID;
  private PIDController fwdLPID;
  private final Drive drive;

  private final double THROTTLE = .75;
  private double initialDistance;
  private double distance;
  private boolean isFinished = false;
  private double lSpeed = 0;
  private double rSpeed = 0;
  private final double TOLERANCE = .5;
 
 

  public Move(Drive drive, double distance) {
    this.distance = distance;
    this.drive = drive;

    this.initialDistance = drive.getAvgDistance();
   
    fwdLPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D, Constants.FWD_F_L);
    fwdRPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D, Constants.FWD_F_R);

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
    drive.resetEncoders();
    initialDistance = drive.getAvgDistance();
    fwdLPID.setSetpoint(distance);
    fwdRPID.setSetpoint(distance);

    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lSpeed = fwdLPID.calculate(drive.leftEncoder.getDistance(), distance+initialDistance);
    rSpeed = fwdRPID.calculate(drive.rightEncoder.getDistance(), distance+initialDistance);
    drive.leftMaster.set(lSpeed);
    drive.rightMaster.set(rSpeed * -1);
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
    return 
    (Math.abs(drive.leftEncoder.getDistance() - (distance + initialDistance)) <= TOLERANCE) && 
    (Math.abs(drive.rightEncoder.getDistance() - (distance + initialDistance)) <= TOLERANCE);
  }
}
