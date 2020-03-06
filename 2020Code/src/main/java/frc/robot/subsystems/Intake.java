package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends SubsystemBase{
    private VictorSPX intakeRoller;
    private double THROTTLE;
    
    public Intake(){
        intakeRoller = new VictorSPX(Constants.INTAKE_ROLLER);
        intakeRoller.setNeutralMode(NeutralMode.Brake);
        THROTTLE = .5;
    }
    
    public void run(double throttle) {
        this.THROTTLE = -throttle;
        intakeRoller.set(ControlMode.PercentOutput, THROTTLE);
    }
    public void stop() {
        intakeRoller.set(ControlMode.PercentOutput, 0);
    }

    @Override 
    public void periodic(){
        
    }
}