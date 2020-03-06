package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class Launch extends CommandBase{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private Launcher launcher;
  private double throttle;
  private double time;
  private double initialTime;

  public Launch(Launcher l) {
      launcher = l;
      throttle = 1;
      // Use addRequirements() here to declare launcher dependencies.
      addRequirements(launcher);
    }

  public Launch(Launcher l, double time) {
    launcher = l;
    throttle = 1;
    this.time = time;
   
    // Use addRequirements() here to declare launcher dependencies.
    addRequirements(launcher);
  }

  // public Launch(Launcher l) {
  //   launcher = l;
  //   this.throttle = throttle;
  //   // Use addRequirements() here to declare launcher dependencies.
  //   addRequirements(launcher);
    
  // }

  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      initialTime = Robot.timer.get();
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
      if (Robot.timer.get() - initialTime >= this.time){
        return true;
      }else{
        return false;
      }
    }

}

