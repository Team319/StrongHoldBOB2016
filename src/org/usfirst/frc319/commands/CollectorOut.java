// Strong Hold BOB 2016 Commands (Collector Out)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectorOut extends Command {

    public CollectorOut() {

        requires(Robot.collector);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.collector.collectorGoOut();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
