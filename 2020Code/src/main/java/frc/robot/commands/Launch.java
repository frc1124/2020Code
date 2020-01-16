package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Launcher;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import edu.wpi.first.wpilibj.Joystick;
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
          // j.getRawButton(Constants.LaunchButton)
          launcher.run();
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

