package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Hopper;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class FeedBallz extends CommandBase {

    private Hopper hopper;

    public FeedBallz(Hopper h) {
        this.hopper = h;
        addRequirements(hopper);
    }

    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // if(RobotContainer.getKey("LB"));
        hopper.run();
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      hopper.stop();
    }
    
     // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}