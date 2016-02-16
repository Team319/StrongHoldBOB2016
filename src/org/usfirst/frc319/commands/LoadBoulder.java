// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class LoadBoulder extends Command {

    public LoadBoulder() {

        requires(Robot.collector);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = -.3;
    	Robot.collector.collectorGoOut(speed);
    	//drive the collector out at a slow speed 
    	//Robot.collector.loadBoulder(loadDelta);
    	
    	
    }

    protected boolean isFinished() {
    	double loadDistance = .8;
        return Robot.collector.loadIsFinished(loadDistance);
        
        //Call the isfinished from the Collector Subsystem pass it the loadDelta
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
