/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

public class Drive extends SubsystemBase {
    
    private SerialPort distanceSensor;

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
        
        // init color sensor

        // init the talons
        leftMaster = new WPI_TalonSRX(Constants.LEFT_MASTER);
        rightMaster = new WPI_TalonSRX(Constants.RIGHT_MASTER);
        leftSlave = new WPI_TalonSRX(Constants.LEFT_SLAVE);
        rightSlave = new WPI_TalonSRX(Constants.RIGHT_SLAVE);

        // init the encoders
        leftEncoder = new Encoder(Constants.LEFT_CHANNEL_A, Constants.LEFT_CHANNEL_B);
        rightEncoder = new Encoder(Constants.RIGHT_CHANNEL_A, Constants.RIGHT_CHANNEL_B);

        // 8192 ticks per rev; 6 in diameter
        leftEncoder.setDistancePerPulse(2 * 3 * Math.PI / 8192);
        leftEncoder.setDistancePerPulse(2 * 3 * Math.PI / 8192);

        // pid controllers
        fwdPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D);
        rotPID = new PIDController(Constants.ROT_P, Constants.ROT_I, Constants.ROT_D);

        // assign slaves to master
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
        
        // navx init
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

    public double getAvgDistance() {
      // inches per second
      return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
    }
    public double getAvgVelocity() {
      // inches per second
      return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // 28:50 ratio
        SmartDashboard.putNumber("L Encoder D", leftEncoder.getDistance());
        SmartDashboard.putNumber("R Encoder D", rightEncoder.getDistance());
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
    public boolean move(double distance) {
      
      double s = fwdPID.calculate(getAvgDistance(), 1000);
      if( s != 0 ) {
        arcadeDrive(s,0);
        return true;
      } else return false;
    }

    public boolean turn(double angle) {
      angle -= navx.getYaw();
      if (angle < -180)
        angle += 360;
        if( angle != 0 ) {
          arcadeDrive(0,angle/360);
          return true;
        } else return false;
      }

    public void stop() {
      leftMaster.set(0);
      rightMaster.set(0);
    }

}
