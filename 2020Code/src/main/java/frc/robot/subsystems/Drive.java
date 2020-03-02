/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Radar;

public class Drive extends SubsystemBase {
    
    // private SerialPort distanceSensor;
    // private Radar radar;

    private DifferentialDrive drive;
    public WPI_TalonSRX leftMaster;
    private WPI_TalonSRX leftSlave;
    public WPI_TalonSRX rightMaster;
    private WPI_TalonSRX rightSlave;
    
    public Encoder leftEncoder;
    public Encoder rightEncoder;

    private PIDController fwdRPID;
    private PIDController fwdLPID;
    private PIDController rotPID;

    private AHRS navx;
    
    public Drive() {
        
        // init radar sensor
        // radar = new Radar();
        // init color sensor

        // init the talons
        leftMaster = new WPI_TalonSRX(Constants.LEFT_MASTER);
        rightMaster = new WPI_TalonSRX(Constants.RIGHT_MASTER);
        leftSlave = new WPI_TalonSRX(Constants.LEFT_SLAVE);
        rightSlave = new WPI_TalonSRX(Constants.RIGHT_SLAVE);

        // init the encoders
        leftEncoder = new Encoder(Constants.LEFT_CHANNEL_A, Constants.LEFT_CHANNEL_B);
        rightEncoder = new Encoder(Constants.RIGHT_CHANNEL_A, Constants.RIGHT_CHANNEL_B);

        leftEncoder.setReverseDirection(true);
        rightEncoder.setReverseDirection(false);

        // set modes to break
        leftMaster.setNeutralMode(NeutralMode.Brake);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
        rightSlave.setNeutralMode(NeutralMode.Brake);

        // 8192 ticks per rev; 6 in diameter
        leftEncoder.setDistancePerPulse(2 * 3 * Math.PI / 2048);
        rightEncoder.setDistancePerPulse(2 * 3 * Math.PI / 2048);

        // pid controllers
        fwdRPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D, Constants.FWD_F_R);
        fwdLPID = new PIDController(Constants.FWD_P, Constants.FWD_I, Constants.FWD_D, Constants.FWD_F_L);

        rotPID = new PIDController(Constants.ROT_P, Constants.ROT_I, Constants.ROT_D, Constants.ROT_F);
        rotPID.setSetpoint(0);

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
        // SmartDashboard.putNumber("Radar Reading", radar.read());F
        SmartDashboard.putNumber("L Encoder D", leftEncoder.getDistance());
        SmartDashboard.putNumber("R Encoder D", rightEncoder.getDistance());
        SmartDashboard.putNumber("L Encoder R", leftEncoder.getRaw());
        SmartDashboard.putNumber("R Encoder R", rightEncoder.getRaw());
        SmartDashboard.putNumber("Left Velocity", leftEncoder.getRate());
        SmartDashboard.putNumber("Right Velocity", rightEncoder.getRate());
        SmartDashboard.putNumber("Average Distance", this.getAvgDistance());
    }

    public double getDistance() {
      // return radar.getInches();
      return 0;
    }

    public void drive(double distance, double angle) {
        angle -= navx.getYaw();
        if(angle < -180) angle += 360;
        
        double avgEncoderDistance = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
        arcadeDrive(
          fwdRPID.calculate(avgEncoderDistance, distance),
          rotPID.calculate(navx.getYaw(), angle)
        );
    }
    public boolean move(double distance) {
      
      // returns isFinished
      final double T = 0.1;
      final double tolerance = 0.1;
      fwdRPID.setSetpoint(distance);
      double s = fwdRPID.calculate(rightEncoder.getDistance(), distance);
      double l = fwdLPID.calculate(leftEncoder.getDistance(), distance);
      if( Math.abs(s) > tolerance ) {
        arcadeDrive(MathUtil.clamp(s, -T, T),0);
        return false;
      } else return true;
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
