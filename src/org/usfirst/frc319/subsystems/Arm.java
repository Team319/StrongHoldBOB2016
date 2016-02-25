// Strong Hold BOB 2016 Subsystems (Arm)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.arm.ArmManualDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	private final CANTalon motor = RobotMap.armMotor;

	double p = 24;//
	double i = .25; //.25
	double d = 0;
	double f = 0;

	int iZone = 20;
	double rampRate = 0;
	int profile = 0;

	public double armHoldPosition = 0;
	public double armCurrentPosition = 0;
	
	public void initDefaultCommand() {

		setDefaultCommand(new ArmManualDrive());

	}	
	
	public Arm() {
		motor.changeControlMode(TalonControlMode.Position);
		motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motor.setPID(p, i, d, f, iZone, rampRate, profile);
		motor.reverseSensor(false); // needs to be tested
		motor.reverseOutput(false); // needs to be tested

		motor.enableLimitSwitch(true, true); 
		

	}

	public boolean getArmForwardLimit() {
		return motor.isFwdLimitSwitchClosed();

	}

	public boolean getArmReverseLimit() {
		return motor.isRevLimitSwitchClosed();

	}

	public void armManualDrive(double triggerValue) {
		//System.out.println("forward limit: "+ getArmForwardLimit());
		//System.out.println("reverse limit: "+ getArmReverseLimit());

		if (triggerValue < 0.1 && triggerValue > -0.1) {

			motor.changeControlMode(TalonControlMode.Position);
			motor.set(armHoldPosition);
		}

		else {
			motor.changeControlMode(TalonControlMode.PercentVbus);
			motor.set(triggerValue);
			armCurrentPosition = motor.getEncPosition();
			armHoldPosition = armCurrentPosition;

		}
	}

	public boolean isOnTarget() {
		double error = motor.getEncPosition() - motor.getSetpoint();
		System.out.println("Arm PID pos: " + motor.getEncPosition());
		System.out.println("Arm PID setpoint: " + motor.getSetpoint());
		System.out.println("Arm PID error: " + error);
		System.out.println("Talon Output: " + motor.getOutputVoltage());
		System.out.println("Talon error: " + motor.getError());
		
		armHoldPosition = motor.getEncPosition();

		if (getArmReverseLimit() && error > 0) {
			motor.setPosition(0);
			return true;
		} else if (error < 25 && error > -25) {
			return true;
		} else {
			return false;
		}
	}

	

	//------ARM POSITIONS------//
	
	public double armStoragePosition = 0;
	public double armShootFromTowerPosition = 1060;//original-1063//works consistently-1039//
	public double armAutoSearchPosition = 680;
	public double armAutoShootHighPosition = 925;
	public double armAutoShootLowPosition = 617;
	public double armShootFromCleatPosition = 1025;
	public double armCollectPosition = 1250;
	
	
	public void goToStorage() {
		setArmPosition(armStoragePosition);
	}
	
	public void goToCollect() {
		setArmPosition(armCollectPosition); //1250
	}

	public void gotToShootFromTower() {
		setArmPosition(armShootFromTowerPosition);
	}
	
	public void goToAutoSearchPosition(){
		setArmPosition(armShootFromTowerPosition);
	}
	
	public void goToAutoShootHighPosition() {
		setArmPosition(armAutoShootHighPosition);
	}
	
	public void goToAutoShootLowPosition() {
		setArmPosition(armAutoShootLowPosition);
	}
	
	public void goToCleatPosition(){
		setArmPosition(armShootFromCleatPosition);
	}
	
	public int getArmPosition(){
		return motor.getEncPosition();
	}
	
	public void setArmPosition(double position)
	{
		motor.changeControlMode(TalonControlMode.Position);
		motor.set(position);
	}
	public void armStop() {
		motor.set(0);
	}
	
}
