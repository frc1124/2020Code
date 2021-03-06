package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase{
    private TalonSRX hopperRoller;
    private double throttle;

    public Hopper(){
        hopperRoller = new TalonSRX(Constants.HOPPER_ROLLER);
        //hopperRoller.setNeutralMode(NeutralMode.Brake);
        throttle = .5;
    }

    public void run(double THROTTLE){
        this.throttle = THROTTLE;
        hopperRoller.set(ControlMode.PercentOutput, throttle);    
    }

    public void stop(){
        hopperRoller.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {

    }
}