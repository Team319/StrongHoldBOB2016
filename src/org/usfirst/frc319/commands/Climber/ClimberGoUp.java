// Strong Hold BOB 2016 Commands (Collector Stop)

package org.usfirst.frc319.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ClimberGoUp extends Command {

    public ClimberGoUp() {

        requires(Robot.climber);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.climber.climberGoUp();
    	System.out.println("climb go up called");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
