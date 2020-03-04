package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Launch extends CommandBase{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private Launcher launcher;
  private double throttle;
    public Launch(Launcher l) {
        launcher = l;
        // Use addRequirements() here to declare launcher dependencies.
        addRequirements(launcher);
      }

    public Launch(Launcher l, double throttle) {
      launcher = l;
      this.throttle = throttle;
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
          launcher.run(throttle);
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

