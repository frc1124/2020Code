package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
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
        // m_colorMatcher.addColorMatch(Color.kBlue);
        // m_colorMatcher.addColorMatch(Color.kGreen);
        // m_colorMatcher.addColorMatch(Color.kRed);
        // m_colorMatcher.addColorMatch(Color.kYellow);
        // ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        double r = (double)m_colorSensor.getRed();
        double g = (double)m_colorSensor.getGreen();
        double b = (double) m_colorSensor.getBlue();
        double mag = r + g + b;

        return new Color(r / mag , g / mag , b / mag );
    }
    public Color matchColor() {
    
        m_colorMatcher.addColorMatch(Color.kBlue);
        m_colorMatcher.addColorMatch(Color.kGreen);
        m_colorMatcher.addColorMatch(Color.kRed);
        m_colorMatcher.addColorMatch(Color.kYellow);
        ColorMatchResult match = m_colorMatcher.matchClosestColor(this.getColor());
        return match.color;
    }

    public DiscSpinner(){
        spinner = new WPI_TalonSRX(Constants.DISC_ROLLER);
        //spinner.setNeutralMode(NeutralMode.Brake);
        m_colorSensor = new ColorSensorV3(i2cPort);
        m_colorMatcher = new ColorMatch();

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
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        
        // if(gameData.length() > 0)
        // {
        //     switch (gameData.charAt(0))
        //     {
        //         case 'B' :
        //             value = "Blue";
        //         break;
        //         case 'G' :
        //             value = "Green";
        //         break;
        //         case 'R' :
        //             value = "Red";
        //         break;
        //         case 'Y' :
        //             value = "Yellow";
        //         break;
        //         default :
        //         //This is corrupt data
        //         break;
        //     }
        // } else {
        // //Code for no data received yet
        // }
       
        if (this.matchColor().equals(Color.kBlue)) {
            value = "Blue";
        } else if(this.matchColor().equals(Color.kGreen)){
            value = "Green";
        } else if (this.matchColor().equals(Color.kYellow)){
            value = "Yellow";
        } else if(this.matchColor().equals(Color.kRed)){
            value = "Red";
        }
        
        SmartDashboard.putNumber("Color red", this.getColor().red);
        SmartDashboard.putNumber("COlor blue", this.getColor().blue);
        SmartDashboard.putNumber("Color green", this.getColor().green);

        SmartDashboard.putString("Color",  value);
    }
}