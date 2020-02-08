import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class Hopper {
    private TalonSRX hopperRoller;
    private final double throttle = 1;

    public Hopper(){
        hopperRoller = new TalonSRX(Constants.HOPPER_ROLLER);
    }

    public void run(){
        hopperRoller.set(ControlMode.PercentOutput, throttle);
        
    }

    public void stop(){
        hopperRoller.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {

    }
}