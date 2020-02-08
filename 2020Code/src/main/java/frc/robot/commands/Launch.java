package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Launcher;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Launch extends CommandBase{
  private Launcher launcher;
    public Launch(Launcher launcher) {
        this.launcher = launcher;
        // Use addRequirements() here to declare launcher dependencies.
        addRequirements(launcher);
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
      }
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
<<<<<<< HEAD
          if (RobotContainer.getKey("A"));
            launcher.run();
=======

          launcher.run();
>>>>>>> 4078868b111a789cde33c897c7bd913c85bc99d1
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
        launcher.stop();
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return false;
      }

}

