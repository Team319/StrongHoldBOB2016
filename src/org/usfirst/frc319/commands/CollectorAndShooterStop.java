// Strong Hold BOB 2016 Commands (Collector Stop)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectorAndShooterStop extends Command {

    public CollectorAndShooterStop() {
        requires(Robot.collector);
        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.collector.stop();
    	Robot.shooter.stop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
