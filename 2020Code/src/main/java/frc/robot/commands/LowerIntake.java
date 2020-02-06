package frc.robot.commands;

import frc.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowerIntake extends CommandBase{
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
          // j.getRawButton(Constants.LaunchButton)
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
