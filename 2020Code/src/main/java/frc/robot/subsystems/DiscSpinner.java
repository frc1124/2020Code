package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DiscSpinner extends SubsystemBase{

    private final I2C.Port i2cPort = I2C.Port.kOnboard; // what is an I2C port

    private final ColorSensorV3 m_colorSensor;

    private final ColorMatch m_colorMatcher;

    public final Color Blue = ColorMatch.makeColor(0, 0, 1);
    public final Color Green = ColorMatch.makeColor(0, 1, 0);
    public final Color Red = ColorMatch.makeColor(1, 0, 0);
    public final Color Yellow = ColorMatch.makeColor(1, 1, 0);

    private WPI_TalonSRX spinner;
    private final double THROTTLE = 1; 

    //@Override
    public Color getColor() {
        // This method will be called once per scheduler run
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        return match.color;
    }

    public DiscSpinner(){
        spinner = new WPI_TalonSRX(Constants.DISC_ROLLER);
        //spinner.setNeutralMode(NeutralMode.Brake);
        m_colorSensor = new ColorSensorV3(i2cPort);
        m_colorMatcher = new ColorMatch();
        m_colorMatcher.addColorMatch(Blue);
        m_colorMatcher.addColorMatch(Green);
        m_colorMatcher.addColorMatch(Red);
        m_colorMatcher.addColorMatch(Yellow);
    }

    public void run() {
        spinner.set(THROTTLE);
    }

    public void stop() {
        spinner.set(0);
    }

    @Override
    public void periodic(){
        String value = "";
        if (this.getColor() == Blue) {
            value = "Blue";
        } else if(this.getColor() == Green){
            value = "Green";
        } else if (this.getColor() == Yellow){
            value = "Yellow";
        }else if(this.getColor() == Red){
            value = "Red";
        }
        
        SmartDashboard.putString("Color", value);
    }
}