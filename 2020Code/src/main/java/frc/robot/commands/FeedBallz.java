package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Hopper;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class FeedBallz extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    private Hopper hopper;
    private double throttle;
    private double time;
    private double initialTime;
    private boolean needsTime = false;

    public FeedBallz(Hopper h) {
      this.hopper = h;
      this.throttle = .6;
      addRequirements(hopper);
    }

    public FeedBallz(Hopper h, double throttle, boolean isSpeed) {
      this.hopper = h;
      this.throttle = throttle;
      needsTime = false;
      addRequirements(hopper);
    
    }
    public FeedBallz(Hopper h, double time) {
        this.hopper = h;
        this.throttle = .4;
        this.time = time;
        needsTime = true;
        addRequirements(hopper);
    }

    @Override
    public void initialize() {
      initialTime = Robot.timer.get();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      hopper.run(throttle);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      hopper.stop();
    }
    
     // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      if(needsTime)
        return (Robot.timer.get() - initialTime > this.time);
        else {
          return false;
        }
    }
}