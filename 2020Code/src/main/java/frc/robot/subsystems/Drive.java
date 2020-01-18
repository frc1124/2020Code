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
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;

public class Drive extends SubsystemBase {
    

    private DifferentialDrive drive;
    private WPI_TalonSRX leftMaster;
    private WPI_TalonSRX leftSlave;
    private WPI_TalonSRX rightMaster;
    private WPI_TalonSRX rightSlave;
    
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private PIDController fwdPID;
    private PIDController rotPID;

    private AHRS navx;
    
    public Drive() {
        
        // init the talons
        leftMaster = new WPI_TalonSRX(Constants.LEFT_MASTER);
        rightMaster = new WPI_TalonSRX(Constants.RIGHT_MASTER);
        leftSlave = new WPI_TalonSRX(Constants.LEFT_SLAVE);
        rightSlave = new WPI_TalonSRX(Constants.RIGHT_SLAVE);

        // init the encoders
        leftEncoder = new Encoder(Constants.LEFT_CHANNEL_A, Constants.LEFT_CHANNEL_B);
        rightEncoder = new Encoder(Constants.RIGHT_CHANNEL_A, Constants.RIGHT_CHANNEL_B);

        // TODO: FIND this value
        leftEncoder.setDistancePerPulse(1);
        leftEncoder.setDistancePerPulse(1);

        fwdPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D);
        rotPID = new PIDController(Constants.ROT_P, Constants.ROT_I, Constants.ROT_D);

        // assign slaves to master
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
        
        // navx
        navx = new AHRS(SPI.Port.kMXP);
        
        drive = new DifferentialDrive(leftMaster, rightMaster);
    }
    
    public void arcadeDrive(double fwd, double rot) {
        drive.arcadeDrive(fwd, rot);

    }

    public void resetEncoders() {

      leftEncoder.reset();
      rightEncoder.reset();
    }
    
    public AHRS getNavxInstance() {
      return navx;
    }

    public double getAvgVelocity() {
      return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    public void drive(double distance, double angle) {
        angle -= navx.getYaw();
        if(angle < -180) angle += 360;
        
        double avgEncoderDistance = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
        arcadeDrive(
          fwdPID.calculate(avgEncoderDistance, distance),
          rotPID.calculate(navx.getYaw(), angle)
        );
    }
    public void stop() {
      leftMaster.set(0);
      rightMaster.set(0);
    }

}
