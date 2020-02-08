package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Launcher extends SubsystemBase{
    private VictorSPX launchRoller;
    private final double THROTTLE = 1;
    
    public Launcher(){
        launchRoller = new VictorSPX(Constants.TOP_ROLLER);
    }
    
    public void run() {
        launchRoller.set(ControlMode.PercentOutput, THROTTLE);
    }
    public void stop() {
        launchRoller.set(ControlMode.PercentOutput, 0);
    }
    
    
    @Override 
    public void periodic(){
        
    }
}