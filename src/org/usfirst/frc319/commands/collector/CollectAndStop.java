// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectAndStop extends Command {

    public CollectAndStop() {

        requires(Robot.collector);
        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = .5;
    	Robot.collector.collectorGoIn(speed);
    	Robot.shooter.setLeftShooterSpeed(400);
    	Robot.shooter.setRightShooterSpeed(400);
    	
    	
    
    }

    protected boolean isFinished() {
    	
    	//highest distance the ball should go
        return Robot.collector.loadIsFinished(1.25);
        ///return the isfinished from the IRcollector sensor pass it a smaller value than in the 
        
    }

    protected void end() {
    	//default command is CollectorStop
    }

    protected void interrupted() {
    }
    
}
