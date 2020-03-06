package frc.robot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Radar {
    private double distance;
    private SerialPort sensor;
    public Radar() {
        sensor = new SerialPort(1200, SerialPort.Port.kOnboard);
    }

    public double getLastRead() {
        return distance;
    }

    public double getInches() {
        return distance / 25.4;
    }

    public double read() {
        // reading in mm
        String rawOutput = sensor.readString(6*8);
        if(rawOutput.charAt(0) == 'R') {
            for(int i = 1; i < 5; i++) {
                distance += Integer.parseInt(rawOutput.substring(8, (i+1) * 8),2) * (Math.pow(10,i));
            }
        }
        sensor.flush();
        
        return distance;
    }


}