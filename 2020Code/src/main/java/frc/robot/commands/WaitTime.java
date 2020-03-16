
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
/**
 * An example command that uses an example subsystem.
 */
public class WaitTime extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private double iTime;
  private double eTime;
  private Timer timer;

 
 

  public WaitTime(double eTime) {
    this.eTime = eTime;
    this.timer = Robot.timer;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      iTime = timer.get();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() - iTime >= eTime;
  }
}
