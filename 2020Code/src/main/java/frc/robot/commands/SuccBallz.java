package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.RobotContainer;
import frc.robot.Constants;;

public class SuccBallz extends CommandBase{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private Intake intake; 
    private double throttle;

    public SuccBallz(Intake Intake) {
        this.intake = Intake;
        this.throttle = .75;
        // Use addRequirements() here to declare launcher dependencies.
        addRequirements(intake);
    }
    
    public SuccBallz(Intake Intake, double throttle) {
      this.intake = Intake;
      this.throttle = throttle;
      // Use addRequirements() here to declare launcher dependencies.
      addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
          intake.run(throttle);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      intake.stop();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}