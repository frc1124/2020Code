package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends SubsystemBase{
    private WPI_TalonSRX intakeRoller;
    private final double THROTTLE = 1;
    
    public Intake(){
        intakeRoller = new WPI_TalonSRX(Constants.INTAKE_ROLLER);
    }
    
    public void run() {
        intakeRoller.set(THROTTLE);
    }
    public void stop() {
        intakeRoller.set(0);
    }
    

    @Override 
    public void periodic(){
        
    }
}