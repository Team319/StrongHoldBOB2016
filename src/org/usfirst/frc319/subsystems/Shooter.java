// Strong Hold BOB 2016 Subsystems (Shooter)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.shooter.MaintainSpeed;
import org.usfirst.frc319.commands.shooter.ShooterDoNothing;
import org.usfirst.frc319.commands.shooter.ShooterStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private final CANTalon leftShooter = RobotMap.shooterleftShooter;
	private final CANTalon rightShooter = RobotMap.shooterrightShooter;

	// ---- used IN PID TUNING----//
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;

	// ---------------------------//

	public Shooter() {

		rightShooter.changeControlMode(TalonControlMode.Speed);
		rightShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightShooter.reverseSensor(false);
		rightShooter.reverseOutput(true);
		// when installed check to make sure encoder and voltage are matched
		rightShooter.configNominalOutputVoltage(+0.0f, +0.0f);
		rightShooter.configPeakOutputVoltage(+0.0f, -12.0f);
		//rightShooter.setProfile(0);
		
		double rightF = Robot.constants.getConstant(BobConstants.SHOOTER_RIGHT_F_KEY);
		double rightP = Robot.constants.getConstant(BobConstants.SHOOTER_RIGHT_P_KEY);
		double rightI = Robot.constants.getConstant(BobConstants.SHOOTER_RIGHT_I_KEY);
		double rightD = Robot.constants.getConstant(BobConstants.SHOOTER_RIGHT_D_KEY);
		
		int rightIZone = (int)Robot.constants.getConstant(BobConstants.SHOOTER_RIGHT_IZONE_KEY);
		
		rightShooter.setPID(rightP, rightI, rightD, rightF, rightIZone, rightShooter.getCloseLoopRampRate(), 0);
		
		/*
		rightShooter.setF(0.02997); // was 0.02997 before weights //0.0611 during burnout testing
		rightShooter.setP(0.11);//0.11 before weights //1.166 during burnout
		rightShooter.setI(.0011);// .0011-original//
		rightShooter.setD(1.1);
		rightShooter.setIZone(50);// might be increased to 100 (Derrick
									// -1/29/16)
		*/
		
		leftShooter.changeControlMode(TalonControlMode.Speed);
		leftShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftShooter.reverseSensor(true);
		// check to make sure encoder and voltage are matched
		leftShooter.configNominalOutputVoltage(+0.0f, +0.0f);
		leftShooter.configPeakOutputVoltage(+12.0f, 0.0f);
		
		double leftF = Robot.constants.getConstant(BobConstants.SHOOTER_LEFT_F_KEY);
		double leftP = Robot.constants.getConstant(BobConstants.SHOOTER_LEFT_P_KEY);
		double leftI = Robot.constants.getConstant(BobConstants.SHOOTER_LEFT_I_KEY);
		double leftD = Robot.constants.getConstant(BobConstants.SHOOTER_LEFT_D_KEY);
		int leftIZone = (int)Robot.constants.getConstant(BobConstants.SHOOTER_LEFT_IZONE_KEY);
		leftShooter.setPID(leftP, leftI, leftD, leftF, leftIZone, leftShooter.getCloseLoopRampRate(), 0);
		/*
		leftShooter.setProfile(0);
		leftShooter.setF(0.0288179); // 0.02997 before weights
		leftShooter.setP(0.11); // 0.11
		leftShooter.setI(.0011);
		leftShooter.setD(1.1); // 1.133
		leftShooter.setIZone(50);// (Derrick -1/29/16)
		*/

	}

	public void stop() {
		rightShooter.set(0);
		leftShooter.set(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MaintainSpeed());
	}

	public void setRightShooterSpeed(double speed) {
		rightShooter.set(speed);
	}

	public void setLeftShooterSpeed(double speed) {
		leftShooter.set(speed);
	}

	public void setRightShooterStop() {
		rightShooter.set(0);
	}

	public void setLeftShooterStop() {
		leftShooter.set(0);
	}

	public double getLeftShooterSpeed() {
		return leftShooter.getSpeed();
	}

	public double getRightShooterSpeed() {
		return rightShooter.getSpeed();
	}

	public double getLeftShooterSetpoint() {
		return leftShooter.getSetpoint();
	}

	public double getRightShooterSetpoint() {
		return rightShooter.getSetpoint();
	}

	public double leftMotorOutputVoltage() {
		return leftShooter.getOutputVoltage();
	}

	public double leftMotorBusVoltage() {
		return leftShooter.getBusVoltage();
	}

	// ---------TUNING PID SHOOTER MOTORS----USED FOR GETTING SHOOTERS TO BE
	// CONSISTENT-----//
	public void rightShooterpIDTest() {
		double motorOutput = rightShooter.getOutputVoltage()
				/ rightShooter.getBusVoltage();
		double motorSpeed = rightShooter.getSpeed();

		// OPEN THE CONSOLE FROM THE DRIVER STATION TO READ OUT PUT VALUES
		_sb.append("\tout:");
		_sb.append(motorOutput);
		_sb.append("\tspd:");
		_sb.append(motorSpeed);

		// --IF YOU PUSH A BCUTTON THE SHOOTER WILL ATTEMPT TO GET TO
		// "Right Fixed Target Speed"
		if (Robot.oi.operatorController.getRawButton(2)) {
			/* Speed mode */

			double rightFixedTargetSpeed = 4000;
			// double targetSpeed = Robot.oi.xBoxController.getRawAxis(0) *
			// 1500.0;
			rightShooter.changeControlMode(TalonControlMode.Speed);
			rightShooter.set(rightFixedTargetSpeed);

			_sb.append("\terr:");
			_sb.append(rightShooter.getClosedLoopError());
			_sb.append("ttrg:");
			_sb.append(rightFixedTargetSpeed);
		}// OTHERWISE IT WILL DRIVE THE MOTOR OFF THE LEFT JOYSTICK X-AXIS
		else {
			rightShooter.changeControlMode(TalonControlMode.PercentVbus);
			rightShooter.set(Robot.oi.operatorController.getRawAxis(5));
		}

		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());

		}

		_sb.setLength(0);

	}

	// ---------TUNING PID SHOOTER MOTORS----USED FOR GETTING SHOOTERS TO BE
	// CONSISTENT-----//
	public void leftShooterpIDTest() {
		double motorOutput = leftShooter.getOutputVoltage()
				/ leftShooter.getBusVoltage();
		double motorSpeed = leftShooter.getSpeed();

		// OPEN THE CONSOLE FROM THE DRIVER STATION TO READ OUT PUT VALUES
		_sb.append("\tout:");
		_sb.append(motorOutput);
		_sb.append("\tspd:");
		_sb.append(motorSpeed);

		if (Robot.oi.operatorController.getRawButton(2)) {

			// --IF YOU PUSH A BCUTTON THE SHOOTER WILL ATTEMPT TO GET TO
			// "Right Fixed Target Speed"

			double leftFixedTargetSpeed = 4000;
			// double targetSpeed = Robot.oi.xBoxController.getRawAxis(0) *
			// 1500.0;

			leftShooter.changeControlMode(TalonControlMode.Speed);
			leftShooter.set(leftFixedTargetSpeed); // leftFixedTargetSpeed

			_sb.append("\terr:");
			_sb.append(leftShooter.getClosedLoopError());
			_sb.append("ttrg:");
			_sb.append(leftFixedTargetSpeed); // leftFixedTargetSpeed

		}// OTHERWISE IT WILL DRIVE THE MOTOR OFF THE LEFT JOYSTICK X-AXIS
		else {
			leftShooter.changeControlMode(TalonControlMode.PercentVbus);
			leftShooter.set(Robot.oi.operatorController.getRawAxis(5));
		}

		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}

		_sb.setLength(0);
	}

	// /----THIS IS NOT WHERE YOU CHANGE THE SHOOTER SPEED

	public boolean isShooterAtSpeed() {
		double leftSetPoint = leftShooter.getSetpoint();
		double rightSetPoint = rightShooter.getSetpoint();
		double leftSpeed = leftShooter.getSpeed();
		double rightSpeed = rightShooter.getSpeed();
		double shootingErrorThreshold = 120;
		/*
		System.out.println("leftSetPoint: " + leftSetPoint);
		System.out.println("leftSpeed: " + leftSpeed);
		System.out.println("leftError: " + leftShooter.getClosedLoopError());
		
		System.out.println("rightSetPoint: " + rightSetPoint);
		System.out.println("rightSpeed: " + rightSpeed);
		System.out.println("rightError: " + rightShooter.getClosedLoopError());
		*/
		if ((Math.abs(leftSpeed - leftSetPoint) < shootingErrorThreshold)
				&& (Math.abs(rightSpeed - rightSetPoint) < shootingErrorThreshold)) {
			return true;
		} else
			return false;
	}
	
	public boolean hasShooterReachedSetpoint(){
		double leftSetPoint = leftShooter.getSetpoint();
		double rightSetPoint = rightShooter.getSetpoint();
		double leftSpeed = leftShooter.getSpeed();
		double rightSpeed = rightShooter.getSpeed();
		
		return (rightSpeed >= rightSetPoint) && (leftSpeed >= leftSetPoint);
	}
	
	public double getLeftShooterPosition(){
		return leftShooter.getEncPosition();
	}
	public double getRightShooterPosition(){
		return rightShooter.getEncPosition();
	}
	

}
