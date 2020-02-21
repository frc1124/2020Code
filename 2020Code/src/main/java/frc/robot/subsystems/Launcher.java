package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Launcher extends SubsystemBase{
    private TalonSRX launchRoller;
    private final double THROTTLE = 1;
    private Encoder encoder;
    
    public Launcher(){
        launchRoller = new TalonSRX(Constants.TOP_ROLLER);
        launchRoller.setNeutralMode(NeutralMode.Brake);
        encoder = new Encoder(Constants.LAUNCHER_CHANNEL_A, Constants.LAUNCHER_CHANNEL_B);
        encoder.setDistancePerPulse(8192);
    }
    
    public void run() {
        launchRoller.set(ControlMode.PercentOutput, THROTTLE);
    }
    public void stop() {
        launchRoller.set(ControlMode.PercentOutput, 0);
    }

    public double speed() {
        //returns speed in revolutions
        return encoder.getRate();
    }
    
    
    @Override 
    public void periodic(){
        SmartDashboard.putNumber("Launcher Speed: ", speed());
    }
}