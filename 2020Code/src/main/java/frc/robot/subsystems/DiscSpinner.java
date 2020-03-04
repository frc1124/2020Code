package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DiscSpinner extends SubsystemBase{

    private final I2C.Port i2cPort = I2C.Port.kOnboard; // what is an I2C port

    private final ColorSensorV3 m_colorSensor;

    private final ColorMatch m_colorMatcher;

    public final Color Blue = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public final Color Green = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public final Color Red = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public final Color Yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);
    public final Encoder discEncoder;

    private VictorSPX spinner;
    private final double THROTTLE = .25; 
    private double distance;

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
    
        m_colorMatcher.addColorMatch(Blue);
        m_colorMatcher.addColorMatch(Green);
        m_colorMatcher.addColorMatch(Red);
        m_colorMatcher.addColorMatch(Yellow);
        ColorMatchResult match = m_colorMatcher.matchClosestColor(this.getColor());
        return match.color;
    }

    public DiscSpinner(){
        spinner = new VictorSPX(Constants.DISC_ROLLER);
        //spinner.setNeutralMode(NeutralMode.Brake);
        m_colorSensor = new ColorSensorV3(i2cPort);
        m_colorMatcher = new ColorMatch();
        discEncoder = new Encoder(6,7);
        distance = 0;
    }

     public DiscSpinner(int Distance){
        spinner = new VictorSPX(Constants.DISC_ROLLER);
        //spinner.setNeutralMode(NeutralMode.Brake);
        m_colorSensor = new ColorSensorV3(i2cPort);
        m_colorMatcher = new ColorMatch();
        discEncoder = new Encoder(6,7);
        distance = 0;
    }


    public void run() {
        // if (Distance == 0){
        //     spinner.set(ControlMode.PercentOutput,THROTTLE);
        // } else if (discEncoder.getDistance() <= distance){
        //     spinner.set(ControlMode.PercentOutput,THROTTLE);
        // }
        
    }

    public void stop() {
        spinner.set(ControlMode.PercentOutput,0);
    }

    @Override
    public void periodic(){
        String value = "";        
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        SmartDashboard.putNumber("Spin Encoder", discEncoder.getDistance());
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
       
        if (this.matchColor().equals(Blue)) {
            value = "Blue";
        } else if(this.matchColor().equals(Green)){
            value = "Green";
        } else if (this.matchColor().equals(Yellow)){
            value = "Yellow";
        } else if(this.matchColor().equals(Red)){
            value = "Red";
        }
        
        SmartDashboard.putNumber("Color red", m_colorSensor.getRed());
        SmartDashboard.putNumber("COlor blue", m_colorSensor.getBlue());
        SmartDashboard.putNumber("Color green", m_colorSensor.getGreen());

        SmartDashboard.putString("Color",  value);
    }
}