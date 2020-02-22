package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Pneumatics;

public class RetractClimb extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    
    private Pneumatics pneumatics;
    private boolean isExtended;

    public RetractClimb(Pneumatics p){
        isExtended = false;
        pneumatics = p;
        addRequirements(pneumatics);

    }

    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //pneumatics.extendClimb();
        pneumatics.retractClimb();
        // if (!(this.isExtended)){
        //     pneumatics.extendClimb();
        //     isExtended = true;
        // } else if (this.isExtended){
        //     pneumatics.retractClimb();
        //     isExtended = false;
        // }  
        //Hook needs to hatch onto the bar
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