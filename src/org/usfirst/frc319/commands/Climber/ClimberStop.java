// Strong Hold BOB 2016 Commands (Collector Stop)

package org.usfirst.frc319.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ClimberStop extends Command {

    public ClimberStop() {

        requires(Robot.climber);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.climber.climberStop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
