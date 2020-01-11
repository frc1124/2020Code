package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Launcher extends SubsystemBase{
    private WPI_TalonSRX LaunchRoller;

    public Launcher(){
        LaunchRoller = new WPI_TalonSRX(Constants.TOP_ROLLER);
    }
    
    public void Launch() {

    }
}

@Override 
public void periodic(){
    
}