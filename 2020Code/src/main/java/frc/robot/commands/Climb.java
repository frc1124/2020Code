package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Pneumatics;

public class Climb extends CommandBase{
    private final Pneumatics pneumatics = new Pnuematics();

    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (RobotContainer.logitechMap("B")){
            pneumatics.extendClimb();
        } else if (RobotContainer.logitechMap("X")){
            pneumatics.retractClimb();
        }
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