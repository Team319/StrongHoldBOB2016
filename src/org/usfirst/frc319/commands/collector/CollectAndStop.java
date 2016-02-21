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
    	double speed = .85;
    	
    	if(Robot.collector.getAverageLeftAndRightBoulderIRSensor() <1){
    		speed=.85;
    	}
    	else{
    		speed =.2;
    	}
    		
    	Robot.collector.collectorGoIn(speed);
    	
    	Robot.shooter.setLeftShooterSpeed(800);
    	Robot.shooter.setRightShooterSpeed(800);
    	
    	
    	
    
    }

    protected boolean isFinished() {
    	
    	//highest distance the ball should go
        //return Robot.collector.loadIsFinished(2.5);
        ///return the isfinished from the IRcollector sensor pass it a smaller value than in the 
        return Robot.collector.bothIRSensorsCloseEnough(2.3);
    }

    protected void end() {
    	//default command is CollectorStop
    }

    protected void interrupted() {
    }
    
}
