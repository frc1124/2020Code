/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
//For a more advanced odometry drive: https://docs.wpilib.org/en/latest/docs/software/examples-tutorials/trajectory-tutorial/creating-drive-subsystem.html
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Drive extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   * Uses ArcadeDrive
   */
  /*  */
  private DifferentialDrive drive;
  private WPI_TalonSRX leftMaster;
  private WPI_TalonSRX leftSlave;
  private WPI_TalonSRX rightMaster;
  private WPI_TalonSRX rightSlave;
  public Drive() {


    // init the talons
    leftMaster = new WPI_TalonSRX(Constants.LEFT_MASTER);
    rightMaster = new WPI_TalonSRX(Constants.RIGHT_MASTER);
    leftSlave = new WPI_TalonSRX(Constants.LEFT_SLAVE);
    rightSlave = new WPI_TalonSRX(Constants.RIGHT_SLAVE);

    // assign slaves to master
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    drive = new DifferentialDrive(leftMaster, rightMaster);
  }

  public void arcadeDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
