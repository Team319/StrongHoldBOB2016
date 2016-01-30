// Strong Hold BOB 2016 Commands (Collector Stop)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectorStop extends Command {

    public CollectorStop() {

        requires(Robot.collector);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.collector.collectorStop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
