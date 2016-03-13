// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.drivetrain;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;

/**
 *
 */
public class AutoDriveStraightDistanceUsingRobotDrive extends Command {

    public AutoDriveStraightDistanceUsingRobotDrive() {

        requires(Robot.driveTrain);

    }

    protected void initialize() {
    	RobotMap.driveTrainrightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveTrainrightDriveFollow.changeControlMode(TalonControlMode.Follower);
    	RobotMap.driveTrainrightDriveFollow.set(RobotMap.driveTrainrightDriveLead.getDeviceID());
     	RobotMap.driveTrainleftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
     	RobotMap.driveTrainleftDriveFollow.changeControlMode(TalonControlMode.Follower);
     	RobotMap.driveTrainleftDriveFollow.set(RobotMap.driveTrainleftDriveLead.getDeviceID());
     	
    Robot.driveTrain.setDTEncodersToZero();
    Robot.driveTrain.resetGyro();
    Robot.driveTrain.shiftDown();
    }

    protected void execute() {
    	//pass in robot speed
    	Robot.driveTrain.driveStraight(-.85); //speed
   
    }

    protected boolean isFinished() {
    	if(Robot.driveTrain.getDistanceFromEncoderValues()> 60000){ //distance
    		System.out.println("REached Distance");
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
