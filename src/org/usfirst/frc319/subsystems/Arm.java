// Strong Hold BOB 2016 Subsystems (Arm)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.arm.ArmManualDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {
	public boolean flash = false;
	private final CANTalon motor = RobotMap.armMotor;
	private static final double THRESHOLD = 3; // encoder ticks
	private final Relay flashLight = RobotMap.flashLight;

	double p_up = 22;// 25
	double i_up = .05; // .25
	double d_up = .25;
	double f_up = 0;

	double p_down = 11;// 25
	double i_down = .015; // .25
	double d_down = .25;
	double f_down = 0;

	int iZone = 10;// 7;

	double rampRate = 0;
	int profile_up = 0;
	int profile_down = 1;

	public double armHoldPosition = 0;
	public double armCurrentPosition = 0;

	public double currentError = 0;
	public double prevError = 0;
	
	public double verticalOffsetDegrees = 0;
	public double verticalOffsetErrorDegrees = 0;
	public double encoderTicksPerDegree = 2.746;
	
	public void initDefaultCommand() {

		setDefaultCommand(new ArmManualDrive());

	}

	public Arm() {
		motor.changeControlMode(TalonControlMode.Position);
		motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		// store both PIDF sets to their respective profiles.
		/*
		 * double p_up = Robot.constants.getConstant("armP_up"); double i_up =
		 * Robot.constants.getConstant("armI_up"); double d_up =
		 * Robot.constants.getConstant("armD_up"); double f_up =
		 * Robot.constants.getConstant("armF_up");
		 * 
		 * double p_down = Robot.constants.getConstant("armP_down"); double
		 * i_down = Robot.constants.getConstant("armI_down"); double d_down =
		 * Robot.constants.getConstant("armD_down"); double f_down =
		 * Robot.constants.getConstant("armF_down");
		 */
		int iZone = 20;// 7;
		motor.setPID(p_down, i_down, d_down, f_down, iZone, rampRate,
				profile_down);
		motor.setPID(p_up, i_up, d_up, f_up, iZone, rampRate, profile_up);
		motor.setAllowableClosedLoopErr(1);
		motor.reverseSensor(false); // needs to be tested//changed to true when
									// encoder got moved to non-driven side
		motor.reverseOutput(false); // needs to be tested//changed to true same
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
		// System.out.println(" ArmHoldPosition:" + armHoldPosition);
		// System.out.println(" ArmCurrentPosition:" + armCurrentPosition);
		if (triggerValue < 0.1 && triggerValue > -0.1) {
			motor.changeControlMode(TalonControlMode.Position);
			motor.set(armHoldPosition);
		}

		else {
			motor.changeControlMode(TalonControlMode.PercentVbus);
			motor.set(triggerValue);
			updateArmPosition();
			// armHoldPosition = armCurrentPosition;
		}
	}

	public boolean isOnTarget() {
		currentError = motor.getEncPosition() - motor.getSetpoint();
		double errorChange = prevError - currentError;
		prevError = currentError;

		/*
		 * System.out.println("Arm Enc pos: " + motor.getEncPosition());
		 * //System.out.println("Arm pos: " + motor.getPosition());
		 * System.out.println("Arm PID setpoint: " + motor.getSetpoint());
		 * System.out.println("Arm PID error: " + currentError);
		 * System.out.println("Talon Output Voltage: " +
		 * motor.getOutputVoltage()); //System.out.println("Talon error: " +
		 * motor.getError());
		 */
		updateArmPosition();
		smartSelectPIDConstants();

		boolean isActuallyOnTarget = false;
		/*
		 * if (getArmReverseLimit() && currentError < 0) { motor.setPosition(0);
		 * isActuallyOnTarget = true; }
		 */
		// else
		if (Math.abs(currentError) < THRESHOLD && errorChange == 0) {
			isActuallyOnTarget = true;
		}
		//if the limit switch is hit then you can't go further anyway
		//if(Robot.arm.getArmReverseLimit()&& errorChange>0){
			//isActuallyOnTarget = true;
		
		SmartDashboard.putBoolean("Arm on Target", isActuallyOnTarget);
		return isActuallyOnTarget;
	}

	public void smartSelectPIDConstants() {
		double error = motor.getError();

		// if on stowed side of vertical
		if (motor.getEncPosition() > Robot.constants.getConstant(BobConstants.ARM_VERTICAL_POS_KEY)){// Robot.constants.getConstant("armVerticalPosition")) {
			if (error <= 0) {
				// System.out.println("Selecting Up Constants");
				motor.setProfile(profile_up);
			} else {
				// System.out.println("Selecting Down Constants");
				motor.setProfile(profile_down);
			}
			// if on ground side of vertical
		} else {
			if (error >= 0) {
				// System.out.println("Selecting Up Constants");
				motor.setProfile(profile_up);
			} else {
				// System.out.println("Selecting Down Constants");
				motor.setProfile(profile_down);
			}
		}
	}

	// ------ARM POSITIONS------//
/*
	public double armStoragePosition = 0;
	public double armShootFromTowerPosition = -394;
	public double armAutoSearchPosition = -253;//
	public double armAutoShootHighPosition = -344;//
	public double armAutoShootLowPosition = -230;// 
	public double armLowGoalPosition = -366;
	public double armShootFromCleatPosition = -324; // 
	double armCollectPosition = -396;// 
	public double armVerticalPosition = -190;

	public void goToStorage() {
		setArmPosition(armStoragePosition);
	}

	public void goToCollect() {
		setArmPosition(armCollectPosition);
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

	public void goToLowGoalPosition() {
		setArmPosition(armLowGoalPosition);
	}
*/
	public int getArmPosition() {
		return motor.getEncPosition();
	}

	public void setArmPosition(double position) {
		motor.changeControlMode(TalonControlMode.Position);
		motor.set(position);
	}
	public void setArmPositionDegrees(double degrees){
		setArmPosition(Math.round(degrees * encoderTicksPerDegree));
	}

	public void armStop() {
		motor.set(0);
	}

	// update the armHoldPosition and nothing else.
	// used in disabledPeriodic to keep track of arm and prevent
	// sudden movement on enable.
	public void updateArmPosition() {
		this.armHoldPosition = motor.getPosition();
	}
	//set vertical offset is in degrees
	public void setTargetVerticalOffset(double verticalOffset) {
		this.verticalOffsetDegrees = verticalOffset;
		this.verticalOffsetErrorDegrees = Robot.constants.getConstant(BobConstants.CAMERA_OUTERWORKS_TARGET_VERTICAL_OFFSET)- this.verticalOffsetDegrees;
	}
	public double getVerticalOffsetError(){
		return this.verticalOffsetErrorDegrees;
	}
	public double getArmDegrees(){
		return motor.getPosition() * encoderTicksPerDegree;
	}
	public void turnOnFLashLight(){
		Robot.arm.flashLight.set(Relay.Value.kForward);
		
		flash = true;
	}
	public void turnOffFlashLight(){
		this.flashLight.set(Relay.Value.kOff);
		
		flash = false;
		
	}
	
}
