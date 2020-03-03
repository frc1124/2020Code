package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DiscSpinner;
import frc.robot.subsystems.ColorSensor;

public class SpinDisc extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    
    DiscSpinner discspinner;

    public SpinDisc(DiscSpinner d){
        discspinner = d;
        addRequirements(discspinner);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        discspinner.run();
    }

    //@Override

    public void end(){
        discspinner.stop();
    }

    @Override
    protected void interrupted(){
        discspinner.stop();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}