
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Radar;


/**
 * An example command that uses an example subsystem.
 */
public class Target extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private boolean ranOnce = false;
  private final Drive drive;
  private final int imageWidth = 160;
  private final int fov = 64;

  private double angle;
  private double targetDistance = 0;
  private double centerX;
  private Solenoid lights;
  private Radar radar;
  /**
   * Creates a new Target.
   *
   * @param subsystem The subsystem used by this command.
   */
  // centerX from GRIP, distance from radar sensor
  public Target(Drive drive) {
    // this.centerX = centerX;
    this.drive = drive;
    this.lights = new Solenoid(Constants.LIGHT);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    this.radar = new Radar();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lights.set(true);
    double[] defaultValue = new double[0];
    double[] centers;

    NetworkTable table = NetworkTableInstance.getDefault().getTable("GRIP/greenReflective");
    centers = table.getEntry("centerX").getDoubleArray(defaultValue);
    
    if (centers.length != 0){
      centerX = centers[0];
    } else {
      centerX = 80; 
    }
  
    SmartDashboard.putNumber("cx", centerX);
    angle = (fov * centerX) / imageWidth;
    SmartDashboard.putNumber("Radar Distance", radar.getInches());
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lights.set(true);
    CommandScheduler.getInstance().schedule(new Turn(drive, angle));
    CommandScheduler.getInstance().schedule(new Move(drive, drive.getDistance() - targetDistance));
    CommandScheduler.getInstance().schedule(new Turn(drive, -angle));
    // SmartDashboard.putString("", );
    ranOnce = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    lights.set(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ranOnce;
  }
}
