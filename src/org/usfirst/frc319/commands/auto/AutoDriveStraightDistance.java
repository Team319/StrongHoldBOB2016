// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.auto;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;

/**
 *
 */
public class AutoDriveStraightDistance extends Command {

	private String defense = "";

    public AutoDriveStraightDistance(String defense) {

        requires(Robot.driveTrain);
        this.defense = defense;
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
    	double speed = -.85;
    	
    	if(defense.equalsIgnoreCase(AutoDictionary.DEF_MOAT)){
    		speed = -.9;
    	}else if(defense.equalsIgnoreCase(AutoDictionary.DEF_RAMPARTS)){
    		
    	}else if(defense.equalsIgnoreCase(AutoDictionary.DEF_TERRAIN)){

    	}else if(defense.equalsIgnoreCase(AutoDictionary.DEF_WALL)){
    		speed = -1.0;
    	}
    	
    	//pass in robot speed
    	Robot.driveTrain.driveStraight(speed); //speed

    }

    protected boolean isFinished() {
    	double finishDistance = 35000;

    	//custom finish distance
    	if(defense.equalsIgnoreCase(AutoDictionary.DEF_MOAT)){
    		finishDistance = 60000;
    	}else if(defense.equalsIgnoreCase(AutoDictionary.DEF_RAMPARTS)){

    	}else if(defense.equalsIgnoreCase(AutoDictionary.DEF_TERRAIN)){

    	}else if(defense.equalsIgnoreCase(AutoDictionary.DEF_WALL)){

    	}



    	if(Robot.driveTrain.getDistanceFromEncoderValues()> finishDistance){ //distance
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
