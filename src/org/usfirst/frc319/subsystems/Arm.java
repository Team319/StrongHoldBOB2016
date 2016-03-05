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

	double p_up = 22;// 25
	double i_up = .05; // .25
	double d_up = .25;
	double f_up = 0;

	double p_down = 11;// 25
	double i_down = .05; // .25
	double d_down = .25;
	double f_down = 0;

	int iZone = 7;
	double rampRate = 0;
	int profile_up = 0;
	int profile_down = 1;

	public double armHoldPosition = 0;
	public double armCurrentPosition = 0;

	public void initDefaultCommand() {

		setDefaultCommand(new ArmManualDrive());

	}

	public Arm() {
		motor.changeControlMode(TalonControlMode.Position);
		motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		// store both PIDF sets to their respective profiles.
		motor.setPID(p_down, i_down, d_down, f_down, iZone, rampRate,
				profile_down);
		motor.setPID(p_up, i_up, d_up, f_up, iZone, rampRate, profile_up);
		motor.reverseSensor(false); // needs to be tested//changed to true when
									// encoder got moved to non-driven side
		motor.reverseOutput(true); // needs to be tested//changed to true same
									// as above

		motor.enableLimitSwitch(true, true);

	}

	public boolean getArmForwardLimit() {
		return motor.isFwdLimitSwitchClosed();

	}

	public boolean getArmReverseLimit() {
		return motor.isRevLimitSwitchClosed();

	}

	public void armManualDrive(double triggerValue) {
		// System.out.println("forward limit: "+ getArmForwardLimit());
		// System.out.println("reverse limit: "+ getArmReverseLimit());
		//System.out.println(" ArmHoldPosition:" + armHoldPosition);
		//System.out.println(" ArmCurrentPosition:" + armCurrentPosition);
		if (triggerValue < 0.1 && triggerValue > -0.1) {
			motor.changeControlMode(TalonControlMode.Position);
			motor.set(armHoldPosition);
		}

		else {
			motor.changeControlMode(TalonControlMode.PercentVbus);
			motor.set(triggerValue);
			updateArmPosition();
			//armHoldPosition = armCurrentPosition;
		}
	}

	public boolean isOnTarget() {
		double error = motor.getEncPosition() - motor.getSetpoint();
		System.out.println("Arm Enc pos: " + motor.getEncPosition());
		//System.out.println("Arm pos: " + motor.getPosition());
		System.out.println("Arm PID setpoint: " + motor.getSetpoint());
		System.out.println("Arm PID error: " + error);
		System.out.println("Talon Output Voltage: " + motor.getOutputVoltage());
		//System.out.println("Talon error: " + motor.getError());

		updateArmPosition();
		smartSelectPIDConstants();

		if (getArmReverseLimit() && error < 0) {
			motor.setPosition(0);
			return true;
		} else if (error <= 2 && error >= -2) {
			return true;
		} else {
			return false;
		}
	}

	public void smartSelectPIDConstants() {
		double error = motor.getError();

		// if on stowed side of vertical
		if (motor.getEncPosition() > armVerticalPosition) {
			if (error <= 0) {
				//System.out.println("Selecting Up Constants");
				motor.setProfile(profile_up);
			} else {
				//System.out.println("Selecting Down Constants");
				motor.setProfile(profile_down);
			}
		// if on ground side of vertical
		} else {
			if (error >= 0) {
				//System.out.println("Selecting Up Constants");
				motor.setProfile(profile_up);
			} else {
				//System.out.println("Selecting Down Constants");
				motor.setProfile(profile_down);
			}
		}
	}

	// ------ARM POSITIONS------//

	public double armStoragePosition = 0;
	public double armShootFromTowerPosition = -394;// original-1063//works
													// consistently-1039//
	public double armAutoSearchPosition = -253;// 680
	public double armAutoShootHighPosition = -344;// 925
	public double armAutoShootLowPosition = -230;// 617
	public double armShootFromCleatPosition = -324;// 1025
	public double armCollectPosition = -427;// 1250*-.372 // all values scaled
	public double armVerticalPosition = -190;

	public void goToStorage() {
		setArmPosition(armStoragePosition);
	}

	public void goToCollect() {
		setArmPosition(armCollectPosition); // 1250
	}

	public void gotToShootFromTower() {
		setArmPosition(armShootFromTowerPosition);
	}

	public void goToAutoSearchPosition() {
		setArmPosition(armShootFromTowerPosition);
	}

	public void goToAutoShootHighPosition() {
		setArmPosition(armAutoShootHighPosition);
	}

	public void goToAutoShootLowPosition() {
		setArmPosition(armAutoShootLowPosition);
	}

	public void goToCleatPosition() {
		setArmPosition(armShootFromCleatPosition);
	}

	public int getArmPosition() {
		return motor.getEncPosition();
	}

	public void setArmPosition(double position) {
		motor.changeControlMode(TalonControlMode.Position);
		motor.set(position);
	}

	public void armStop() {
		motor.set(0);
	}

	// update the armHoldPosition and nothing else.
	// used in disabledPeriodic to keep track of arm and prevent
	// sudden movement on enable.
	public void updateArmPosition() {
		this.armHoldPosition = motor.getPosition();
		
		double prevOutputVoltage = motor.getOutputVoltage();
		
		if (getArmReverseLimit() && prevOutputVoltage < 0) {
			motor.setPosition(0);
			System.out.println("Home Limit Reached: Reseting Encoder.");
		}
	}

}
