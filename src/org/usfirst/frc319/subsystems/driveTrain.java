// Strong Hold BOB 2016 Subsystems (Drive Train)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class driveTrain extends Subsystem {

	public boolean shift;

    private final DoubleSolenoid shifter = RobotMap.driveTrainshifter;
    private final CANTalon rightDriveLead = RobotMap.driveTrainrightDriveLead;
    private final CANTalon leftDriveLead = RobotMap.driveTrainleftDriveLead;
    private final CANTalon rightDriveFollow = RobotMap.driveTrainrightDriveFollow;
    private final CANTalon leftDriveFollow = RobotMap.driveTrainleftDriveFollow;    
    private final RobotDrive drivetrain = RobotMap.driveTraindriveTrain;

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
    
}

