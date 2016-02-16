// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectorInUntilBallIsGone extends Command {

    public CollectorInUntilBallIsGone() {

        requires(Robot.collector);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = 1;
    	Robot.collector.collectorGoIn(speed);
    }

    protected boolean isFinished() {
        return false;
        //implement bellow when bouldersensor is working		
        //Robot.collector.ballIsGone(20);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
