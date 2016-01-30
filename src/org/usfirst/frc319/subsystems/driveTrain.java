// Strong Hold BOB 2016 Subsystems (Drive Train)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.MotionProfileExample;
import org.usfirst.frc319.RightExampleMotionProfile;
import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.MotionProfileStatus;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class driveTrain extends Subsystem {

	public boolean shift;

	public String highGear = "High Gear";
	public String lowGear = "Low Gear";
	
    private final DoubleSolenoid shifter = RobotMap.driveTrainshifter;
    private final CANTalon rightDriveLead = RobotMap.driveTrainrightDriveLead;
    private final CANTalon leftDriveLead = RobotMap.driveTrainleftDriveLead;
    private final CANTalon rightDriveFollow = RobotMap.driveTrainrightDriveFollow;
    private final CANTalon leftDriveFollow = RobotMap.driveTrainleftDriveFollow;    
    private final RobotDrive drivetrain = RobotMap.driveTraindriveTrain;
    private MotionProfileExample _example = new MotionProfileExample(rightDriveLead);
    
    public driveTrain(){
 	   rightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
 	   rightDriveFollow.changeControlMode(TalonControlMode.Follower);
 	   rightDriveFollow.set(rightDriveLead.getDeviceID());
 	   leftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
 	   leftDriveFollow.changeControlMode(TalonControlMode.Follower);
 	   leftDriveFollow.set(leftDriveLead.getDeviceID());
 	   
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
	
    }
    
    public void arcadeDrive(double moveValue, double rotateValue){
    	drivetrain.arcadeDrive(moveValue, rotateValue, true);

		// true is a boolean for "squared inputs" - derrick 1/20/16
		
    }
    //Output Shift to Smart Dash Board as a boolean (Wyatt- 1/27/16)
    public void shiftUp(){
    	Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kForward);
    	shift = false;
    	
    }
    
    public void shiftDown(){
        Robot.driveTrain.shifter.set(DoubleSolenoid.Value.kReverse);
    	shift = true;
     
    }
    
    public void followMotionProfile(){
    	    	
    	_example.control();
    	CANTalon.SetValueMotionProfile setOutput = _example.getSetValue();
    	rightDriveLead.set(setOutput.value);
    	
    	
    }
    
    public void enableMotionProfileMode(){
    	rightDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	rightDriveLead.configEncoderCodesPerRev(1024);
    	rightDriveLead.reverseSensor(true);
    	rightDriveLead.changeControlMode(TalonControlMode.MotionProfile);
    	
    	leftDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	leftDriveLead.configEncoderCodesPerRev(1024);
    	leftDriveLead.reverseSensor(false);
    	leftDriveLead.changeControlMode(TalonControlMode.MotionProfile);
    	
    }
    
    public void startMotionProfile(){
    	_example.startMotionProfile();
    }
    public void resetMotionProfile(){
    	_example.reset();
    }
    
    public boolean isMotionProfileFinished(){
    	MotionProfileStatus rightMPStatus = new MotionProfileStatus();
    	rightDriveLead.getMotionProfileStatus(rightMPStatus);
    	
    	return rightMPStatus.activePoint.isLastPoint;
    }
}

