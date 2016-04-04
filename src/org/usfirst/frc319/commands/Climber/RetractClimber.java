// Strong Hold BOB 2016 Commands (Collector Stop)

package org.usfirst.frc319.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class RetractClimber extends Command {

    public RetractClimber() {

        requires(Robot.climber);

    }

    protected void initialize() {
    	Robot.climber.retractClimber();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
