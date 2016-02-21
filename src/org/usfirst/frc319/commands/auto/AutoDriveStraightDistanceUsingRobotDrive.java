// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class AutoDriveStraightDistanceUsingRobotDrive extends Command {

    public AutoDriveStraightDistanceUsingRobotDrive() {

        requires(Robot.driveTrain);

    }

    protected void initialize() {
    
    Robot.driveTrain.setDTEncodersToZero();
    Robot.driveTrain.resetGyro();
    Robot.driveTrain.shiftDown();
    }

    protected void execute() {
    	//pass in robot speed
    	Robot.driveTrain.driveStraight(-1);
   
    }

    protected boolean isFinished() {
    	if(Robot.driveTrain.getDistanceFromEncoderValues()> 30000){
    		return true;
    	}
    		else{
    			System.out.println("not at distance");
    			System.out.println("robot distance" +Robot.driveTrain.getDistanceFromEncoderValues());
    			return false;
    		}
    }

    protected void end() {
    	Robot.driveTrain.setDTEncodersToZero();
    	
    
    }

    protected void interrupted() {
    }
    
}
