package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Pneumatics;

public class ExtendClimb extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    
    private Pneumatics pneumatics;
    public ExtendClimb(Pneumatics p){
        pneumatics = p;
        addRequirements(pneumatics);

    }
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        pneumatics.extendClimb();
        //Hook needs to hatch onto the bar
        //pneumatics.retractClimb();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    return false;
    }
    
}