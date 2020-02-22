package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {

    private Compressor compressor;
    private DoubleSolenoid intakeLifter;
    private DoubleSolenoid climber;
    // private DoubleSolenoid rightClimber;
    // private DoubleSolenoid leftClimber;
    
    public boolean running = false;
    
    public Pneumatics(Compressor c) {
        compressor = c;
        intakeLifter = new DoubleSolenoid(Constants.INTAKE_LIFTER_A, Constants.INTAKE_LIFTER_B);
        climber = new DoubleSolenoid(Constants.CLIMB_A, Constants.CLIMB_B);
        // leftClimber = new DoubleSolenoid(Constants.LEFT_CLIMB_A, Constants.LEFT_CLIMB_B);
        // rightClimber = new DoubleSolenoid(Constants.RIGHT_CLIMB_A, Constants.RIGHT_CLIMB_B);
    }

    public void init(){
        compressor.setClosedLoopControl(false);
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

    public void extendClimb(){
        climber.set(DoubleSolenoid.Value.kForward);
        // leftClimber.set(DoubleSolenoid.Value.kForward);
        // rightClimber.set(DoubleSolenoid.Value.kForward);
    }

    public void retractClimb(){
        climber.set(DoubleSolenoid.Value.kReverse);
        // leftClimber.set(DoubleSolenoid.Value.kReverse);
        // rightClimber.set(DoubleSolenoid.Value.kReverse);
    }

    public void stop(){
        //compressor.setClosedLoopControl(false);
        compressor.stop();
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // setDefaultCommand(new SolenoidDefault());
    }
}
