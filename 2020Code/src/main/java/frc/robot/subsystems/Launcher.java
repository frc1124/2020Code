package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Launcher extends SubsystemBase{
    private WPI_TalonSRX launchRoller;
    private final double THROTTLE = 1;
    
    public Launcher(){
        launchRoller = new WPI_TalonSRX(Constants.TOP_ROLLER);
    }
    
    public void run() {
        launchRoller.set(THROTTLE);
    }
    public void stop() {
        launchRoller.set(0);
    }
    
    
    @Override 
    public void periodic(){
        
    }
}