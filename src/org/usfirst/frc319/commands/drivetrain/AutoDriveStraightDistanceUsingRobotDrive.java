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

		double currentdistance = 0;
		
    public AutoDriveStraightDistanceUsingRobotDrive() {

        requires(Robot.driveTrain);

    }

    protected void initialize() {
        Robot.driveTrain.setDTEncodersToZero();
    	RobotMap.driveTrainrightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveTrainrightDriveFollow.changeControlMode(TalonControlMode.Follower);
    	RobotMap.driveTrainrightDriveFollow.set(RobotMap.driveTrainrightDriveLead.getDeviceID());
     	RobotMap.driveTrainleftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
     	RobotMap.driveTrainleftDriveFollow.changeControlMode(TalonControlMode.Follower);
     	RobotMap.driveTrainleftDriveFollow.set(RobotMap.driveTrainleftDriveLead.getDeviceID());
     	

    Robot.driveTrain.resetGyro();
    Robot.driveTrain.shiftDown();
    System.out.println("DriveStraight Init  : " + Robot.driveTrain.getDistanceFromEncoderValues());
    currentdistance = Robot.driveTrain.getDistanceFromEncoderValues();
    }

    protected void execute() {
    	//pass in robot speed
    	Robot.driveTrain.driveStraight(-1);
    	//Robot.driveTrain.driveStraight(-1); //speed
    	System.out.println("Driving and distance is  : " + Robot.driveTrain.getDistanceFromEncoderValues());
   
    }

    protected boolean isFinished() {
    	if(Robot.driveTrain.getDistanceFromEncoderValues()> currentdistance + 60000){ //distance 60000
    		System.out.println("REached Distance");
    		Robot.driveTrain.enableBrakeMode(true);
    		return true;
    	}
    		else{
    			System.out.println("not at distance");
    			System.out.println("robot distance  : " +Robot.driveTrain.getDistanceFromEncoderValues());
    			return false;
    		}
    }

    protected void end() {
    	Robot.driveTrain.enableBrakeMode(false);
    	//Robot.driveTrain.setDTEncodersToZero();
    	
    
    }

    protected void interrupted() {
    }
    
}
