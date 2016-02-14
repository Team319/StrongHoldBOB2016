// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectAndStop extends Command {

    public CollectAndStop() {

        requires(Robot.collector);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = 1;
    	Robot.collector.collectorGoIn(speed);
    	
    	
    
    }

    protected boolean isFinished() {
        return Robot.collector.getBoulderSensor();
        ///return the isfinished from the IRcollector sensor pass it a smaller value than in the 
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
