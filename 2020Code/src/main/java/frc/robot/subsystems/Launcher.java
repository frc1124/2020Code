package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;

public class Launcher extends SubsystemBase{
    private TalonSRX launchRoller;
    
    // public double THROTTLE;
    private Encoder encoder;
    private PIDController launchController;
    private double setpoint = 3500 / 60; // Desired rev/sec
    // private DutyCycleEncoder encoder;
    
    public Launcher(){
        launchRoller = new TalonSRX(Constants.TOP_ROLLER);
        launchRoller.setNeutralMode(NeutralMode.Brake);
        encoder = new Encoder(Constants.LAUNCHER_CHANNEL_A, Constants.LAUNCHER_CHANNEL_B);
        encoder.setDistancePerPulse(8192);
        // launchController = new PIDController(Constants.LAUNCH_P, Constants.LAUNCH_I,Constants.LAUNCH_D, Constants.LAUNCH_F);
        // launchController.setSetpoint(30);
        launchRoller.config_kP(0, Constants.LAUNCH_P);
        launchRoller.config_kI(0, Constants.LAUNCH_I);
        launchRoller.config_kD(0, Constants.LAUNCH_D);
        launchRoller.config_kF(0, Constants.LAUNCH_F);

    }

    public void initialize() {
      
    }
   
    public void run() {
        launchRoller.set(ControlMode.Velocity, 5530 * 8096 / 600);
        // launchRoller.set(ControlMode.PercentOutput, launchController.calculate(encoder.getRate(), setpoint));
    }
    public void set(double THROTTLE) {
        launchRoller.set(ControlMode.PercentOutput, THROTTLE);
        // launchRoller.set(ControlMode.PercentOutput, launchController.calculate(encoder.getRate(), setpoint));
    }


    public void stop() {
        launchRoller.set(ControlMode.PercentOutput, 0);
    }

    public double speed() {
        //returns speed in revolutions
        // return encoder.getRate();
        return encoder.getDistance();
    }
    
    
    @Override 
    public void periodic(){
        // SmartDashboard.putNumber("Launcher Speed: ", speed());
        // SmartDashboard.putNumber("Launcher voltage", launchRoller.getOutputCurrent());
        // SmartDashboard.putNumber("Launcher Encoder Raw", encoder.get());
    }
}