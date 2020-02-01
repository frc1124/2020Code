package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {

    public Compressor compressor = new Compressor(Constants.COMPRESSOR);
    public DoubleSolenoid intakeLifter = new DoubleSolenoid(Constants.INTAKE_LIFTER_A, Constants.INTAKE_LIFTER_B);

    
    public boolean running;
    
    public void init(){
        compressor.start();
    }
    
    public void raiseIntake(){
        intakeLifter.set(DoubleSolenoid.Value.kForward);
    }
    
    public void lowerIntake(){
        intakeLifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void intakeSolenoidOff(){
        intakeLifter.set(DoubleSolenoid.Value.kOff);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // setDefaultCommand(new SolenoidDefault());
    }
}
