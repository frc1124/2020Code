package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DiscSpinner extends SubsystemBase{
    private WPI_TalonSRX spinner;
    private final int THROTTLE = 1; 

    public DiscSpinner(){
        spinner = new WPI_TalonSRX(Constants.SPINNER);
   }

   public void start() {spinner.set(THROTTLE);}

   public void stop() {spinner.set(0);}

}