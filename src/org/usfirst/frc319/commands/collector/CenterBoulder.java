// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CenterBoulder extends Command {

    public CenterBoulder() {

        requires(Robot.collector);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.collector.collectorGoOut(.3);
    }

    protected boolean isFinished() {
        return Robot.collector.CenterBoulderIsFinished(100);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
