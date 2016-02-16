// Strong Hold BOB 2016 Subsystems (Drive Train)

package org.usfirst.frc319.subsystems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.usfirst.frc319.LeftMotionProfile;
import org.usfirst.frc319.RightMotionProfile;
import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.motionProfiles.*;

import com.kauailabs.navx.frc.AHRS;
import com.team319.robot.StatefulSubsystem;
import com.team319.robot.logging.LoggableCanTalon;
import com.team319.robot.logging.LoggableSensor;
import com.team319.trajectory.CombinedSrxMotionProfile;
import com.team319.trajectory.TrajectoryChangeListener;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.MotionProfileStatus;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends StatefulSubsystem implements TrajectoryChangeListener{

	public boolean shift;

	public String highGear = "High Gear";
	public String lowGear = "Low Gear";

	private final DoubleSolenoid shifter = RobotMap.driveTrainshifter;
	private final CANTalon rightDriveLead = RobotMap.driveTrainrightDriveLead;
	private final CANTalon leftDriveLead = RobotMap.driveTrainleftDriveLead;
	private final CANTalon rightDriveFollow = RobotMap.driveTrainrightDriveFollow;
	private final CANTalon leftDriveFollow = RobotMap.driveTrainleftDriveFollow;
	private final RobotDrive drivetrain = RobotMap.driveTraindriveTrain;
	public static AHRS imu; 

	LeftMotionProfile leftProfile = new LeftMotionProfile(leftDriveLead);
	RightMotionProfile rightProfile = new RightMotionProfile(rightDriveLead);
	
	private static CombinedSrxMotionProfile combinedProfile;

	public DriveTrain() {
		imu = new AHRS(SPI.Port.kMXP);
		imu.reset();
		
		rightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
		rightDriveFollow.changeControlMode(TalonControlMode.Follower);
		rightDriveFollow.set(rightDriveLead.getDeviceID());
		leftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
		leftDriveFollow.changeControlMode(TalonControlMode.Follower);
		leftDriveFollow.set(leftDriveLead.getDeviceID());
		
		rightDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	    rightDriveLead.configEncoderCodesPerRev(1024);
	    rightDriveLead.reverseSensor(true);//true
	    rightDriveLead.reverseOutput(false);
	    
	    rightDriveLead.setF(0.312);
	    rightDriveLead.setP(0.1);//0.28);
	    
	    leftDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	    leftDriveLead.configEncoderCodesPerRev(1024);
	    leftDriveLead.reverseSensor(false);
	    leftDriveLead.reverseOutput(true);
	    
	    leftDriveLead.setF(0.333);
	    leftDriveLead.setP(0.1);//0.28);
		
		
		
	}
	

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());

	}

	public void arcadeDrive(double moveValue, double rotateValue) {

		drivetrain.arcadeDrive(-moveValue, rotateValue, true);

		// true is a boolean for "squared inputs" - derrick 1/20/16

		// moved two lines below to teleop Periodic 3:20 pm 2/6/16
		leftProfile.control();
		rightProfile.control();
		// leftProfile.reset();
		// rightProfile.reset();

	}

	public int getLeftDrivetrainPosition() {
		return leftDriveLead.getEncPosition();
	}
	
	public int getRightDrivetrainPosition() {
		return rightDriveLead.getEncPosition();
	}
	

	public void setRightEncoderToZero() {
		rightDriveLead.setEncPosition(0);
	}

	// Output Shift to Smart Dash Board as a boolean (Wyatt- 1/27/16)
	public void shiftUp() {
		Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
		shift = false;

	}

	public void shiftDown() {
		Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
		shift = true;

	}

	public void controlRightMotionProfile() {
		rightProfile.control();
	}

	public void controlLeftMotionProfile() {
		leftProfile.control();
	}
	
	public boolean imuIsConnected(){
		return imu.isConnected();
		
	}
	
	//all of this is now in the FollowBothMotion Profiles Command
/*
	public void rightFollowMotionProfile() {

		// leftProfile.control();//I think this may need to be moved to the
		// default Command of the subsystem (Derrick 2/6/15)
		rightDriveLead.changeControlMode(TalonControlMode.MotionProfile);
		CANTalon.SetValueMotionProfile setOutput = rightProfile.getSetValue();
		rightDriveLead.set(setOutput.value);
	}

	public void leftFollowMotionProfile() {
		leftDriveLead.changeControlMode(TalonControlMode.MotionProfile);
		CANTalon.SetValueMotionProfile setOutput = leftProfile.getSetValue();
		leftDriveLead.set(setOutput.value);
	}

	public void enableMotionProfileMode() {
		rightDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightDriveLead.configEncoderCodesPerRev(1024);
		rightDriveLead.reverseSensor(true);
		// rightDriveLead.changeControlMode(TalonControlMode.MotionProfile);

		rightDriveLead.setF(0.15); // not sure where to tune PID -DErrick
									// 1/29/15
		rightDriveLead.setP(0);
		rightDriveLead.setI(0);
		rightDriveLead.setD(0);
		rightDriveLead.setIZone(0);
		rightDriveLead.setCloseLoopRampRate(0);

		leftDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftDriveLead.configEncoderCodesPerRev(1024);
		leftDriveLead.reverseSensor(true);
		// leftDriveLead.changeControlMode(TalonControlMode.MotionProfile);

		leftDriveLead.setF(0.15); // not sure where to tune PID -DErrick 1/29/15
		leftDriveLead.setP(0);
		leftDriveLead.setI(0);
		leftDriveLead.setD(0);
		leftDriveLead.setIZone(0);
		leftDriveLead.setCloseLoopRampRate(0);

	}

	public void startLeftMotionProfile() {
		leftProfile.startMotionProfile();
	}

	public void startRightMotionProfile() {
		rightProfile.startMotionProfile();
	}

	public void resetLeftMotionProfile() {
		leftProfile.reset();
	}

	public void resetRightMotionProfile() {
		rightProfile.reset();
	}

	public int getRightTimeoutCnt() {
		return rightProfile.getTimeoutCnt();
	}

	public int getLeftTimeoutCnt() {
		return leftProfile.getTimeoutCnt();
	}
*/
/*
	public boolean isRightMotionProfileFinished() {
		MotionProfileStatus rightMPStatus = new MotionProfileStatus();
		rightDriveLead.getMotionProfileStatus(rightMPStatus);

		return rightMPStatus.activePoint.isLastPoint;
	}

	public boolean isLeftMotionProfileFinished() {
		MotionProfileStatus leftMPStatus = new MotionProfileStatus();
		leftDriveLead.getMotionProfileStatus(leftMPStatus);

		return leftMPStatus.activePoint.isLastPoint;
	}
*/

	public void setModeToVBus() {
		rightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
		rightDriveFollow.changeControlMode(TalonControlMode.Follower);
		rightDriveFollow.set(rightDriveLead.getDeviceID());
		leftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
		leftDriveFollow.changeControlMode(TalonControlMode.Follower);
		leftDriveFollow.set(leftDriveLead.getDeviceID());
	}

	@Override
	public Map<String, Object> getCustomProperties() {
		// TODO Auto-generated method stub
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("isHighGear", !shift);
		return properties;
	}

	@Override
	public List<LoggableSensor> getSensors() {
		// TODO Auto-generated method stub
		List<LoggableSensor> sensors = new ArrayList<LoggableSensor>();
		
		sensors.add(new LoggableCanTalon("leftTalon", this.leftDriveLead));
		sensors.add(new LoggableCanTalon("rightTalon", this.rightDriveLead));
		
		return sensors;
	}

	@Override
	public void onTrajectoryChange(CombinedSrxMotionProfile combinedProfile) {
		//Robot.driveTrain.combinedProfile = combinedProfile;
		DriveTrain.combinedProfile = combinedProfile; // this is the same as the thing above it
	}
	
	public static CombinedSrxMotionProfile getCombinedProfile() {
		return combinedProfile;
	}

}
