// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.drivetrain;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.auto.AutoDictionary;

import com.team319.auto.AutoConfig;
import com.team319.auto.AutoManager;

/**
 *
 */
public class AutoDriveStraightDistanceUsingRobotDrive extends Command {

	double startingDistance = 0;

	public AutoDriveStraightDistanceUsingRobotDrive() {

		requires(Robot.driveTrain);

	}

	protected void initialize() {
		Robot.driveTrain.setDTEncodersToZero();
		/*
		RobotMap.driveTrainrightDriveLead
				.changeControlMode(TalonControlMode.PercentVbus);
		RobotMap.driveTrainrightDriveFollow
				.changeControlMode(TalonControlMode.Follower);
		RobotMap.driveTrainrightDriveFollow
				.set(RobotMap.driveTrainrightDriveLead.getDeviceID());
		RobotMap.driveTrainleftDriveLead
				.changeControlMode(TalonControlMode.PercentVbus);
		RobotMap.driveTrainleftDriveFollow
				.changeControlMode(TalonControlMode.Follower);
		RobotMap.driveTrainleftDriveFollow.set(RobotMap.driveTrainleftDriveLead
				.getDeviceID());
		*/
		Robot.driveTrain.resetGyro();
		Robot.driveTrain.shiftDown();
		System.out.println("DriveStraight Init  : "
				+ Robot.driveTrain.getDistanceFromEncoderValues());
		startingDistance = Robot.driveTrain.getDistanceFromEncoderValues();
	}

	protected void execute() {
		// pass in robot speed
		double speed = -1;

		try {
			String selectedDefense = AutoManager.getInstance().getConfig()
					.getSelectedAuto().getSelectedDefense();
			if (selectedDefense.equalsIgnoreCase(AutoDictionary.DEF_WALL)) {
				// drive the default speed
			} else if (selectedDefense
					.equalsIgnoreCase(AutoDictionary.DEF_LOW_BAR)) {
				speed = -.75;
			}
		} catch (Exception e) {

		}
		/**
		 * if(AutoManager.getInstance().getConfig().getSelectedAuto().
		 * getSelectedDefense().equalsIgnoreCase(AutoDictionary.DEF_TERRAIN)){
		 * speed = .7; System.out.println("Driving at slow speed"); }else{
		 * System.out.println("Driving at full speed"); }
		 **/
		Robot.driveTrain.driveStraight(speed);
		// Robot.driveTrain.driveStraight(-1); //speed
		System.out.println("Driving and distance is  : "
				+ Robot.driveTrain.getDistanceFromEncoderValues());

	}

	protected boolean isFinished() {
		double distanceToDrive = 47500;

		try {
			String selectedDefense = AutoManager.getInstance().getConfig()
					.getSelectedAuto().getSelectedDefense();
			if (selectedDefense.equalsIgnoreCase(AutoDictionary.DEF_RAMPARTS)) {
				distanceToDrive = 60000;
			} else if (selectedDefense
					.equalsIgnoreCase(AutoDictionary.DEF_LOW_BAR)) {
				distanceToDrive = 52500;
			}
		} catch (Exception e) {

		}
		
		double targetDistance = distanceToDrive + startingDistance;

		if (Robot.driveTrain.getDistanceFromEncoderValues() > targetDistance) {// 60000){ //distance 60000
			System.out.println("Reached Distance");
			Robot.driveTrain.enableBrakeMode(true);
			return true;
		} else {
			System.out.println("not at distance");
			System.out.println("robot distance  : "
					+ Robot.driveTrain.getDistanceFromEncoderValues());
			return false;
		}
	}

	protected void end() {
		Robot.driveTrain.enableBrakeMode(false);
		// Robot.driveTrain.setDTEncodersToZero();

	}

	protected void interrupted() {
	}

}
