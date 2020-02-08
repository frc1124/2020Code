
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drive;

/**
 * An example command that uses an example subsystem.
 */
public class Target extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drive drive;
  private final int imageWidth = 0;
  private final int fov = 120;

  private int angle; 
  private double targetDistance = 0;
  private int centerX;

  /**
   * Creates a new Target.
   *
   * @param subsystem The subsystem used by this command.
   */
  // centerX from GRIP, distance from radar sensor
  public Target(Drive drive, int centerX) {
    this.centerX = centerX;
    this.drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    angle = (fov * centerX) / imageWidth;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    CommandScheduler.getInstance().schedule(new Turn(drive, angle));
    CommandScheduler.getInstance().schedule(new Move(drive.getDistanceSensor() - targetDistance));
    CommandScheduler.getInstance().schedule(new Turn(drive, -angle));
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
