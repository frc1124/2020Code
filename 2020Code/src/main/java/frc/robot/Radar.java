package frc.robot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Radar {
    private int distance;
    private SerialPort sensor;
    public Radar() {
        sensor = new SerialPort(1200, SerialPort.Port.kOnboard);
    }

    public int getLastRead() {
        return distance;
    }

    public double getInches() {
        return distance / 25.4;
    }

    public int read() {
        // reading in mm
        String rawOutput = sensor.readString(6);
        if(rawOutput.charAt(0) == 'R') {
            distance = Integer.parseInt(rawOutput.substring(1, 5));
        }
        sensor.flush();
        
        return distance;
    }


}