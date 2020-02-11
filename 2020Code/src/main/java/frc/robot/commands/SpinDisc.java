package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DiscSpinner;

public class SpinDisc extends CommandBase{
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

    @Override
    public void end(){
        discspinner.stop();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}