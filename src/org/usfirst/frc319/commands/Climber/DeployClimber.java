// Strong Hold BOB 2016 Commands (Collector Stop)

package org.usfirst.frc319.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class DeployClimber extends Command {

    public DeployClimber() {

        requires(Robot.climber);

    }

    protected void initialize() {
    	Robot.climber.deployClimber();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
