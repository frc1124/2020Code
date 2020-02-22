package frc.robot.commands;

import frc.robot.subsystems.Pneumatics;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class LowerIntake extends CommandBase{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private Pneumatics pneumatics;
    public LowerIntake(Pneumatics pneumatics) {
        this.pneumatics = pneumatics;
        // Use addRequirements() here to declare pneumatics dependencies.
        addRequirements(pneumatics);
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
      }
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
         pneumatics.lowerIntake();
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

