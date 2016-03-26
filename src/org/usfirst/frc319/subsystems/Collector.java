// Strong Hold BOB 2016 Subsystems (Collector)

package org.usfirst.frc319.subsystems;

import java.util.Set;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.collector.CollectorStop;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Collector extends Subsystem {

	private final CANTalon motor = RobotMap.collectorcollectorMotor;
	private final DigitalInput boulderSensor = RobotMap.collectorboulderSensor;
	private final AnalogInput leftBoulderIRSensor = RobotMap.leftBoulderIRSensor;
	private final AnalogInput rightBoulderIRSensor = RobotMap.rightBoulderIRSensor;

	// link the AnalogInput to the RobotMap

	public void initDefaultCommand() {

		setDefaultCommand(new CollectorStop());

	}

	public Collector() {
		motor.changeControlMode(TalonControlMode.PercentVbus);
	}

	public boolean getBoulderSensor() {

		return boulderSensor.get();
	}

	public void collectorGoIn(double speed) {
		motor.set(-speed);
	}

	public void stop() {
		motor.set(0);
	}

	public void collectorGoOut(double speed) {
		motor.set(speed);
	}

	public double getleftBoulderIrSensorVoltage() {
		return leftBoulderIRSensor.getVoltage();
	}

	public double getrightBoulderIrSensorVoltage() {
		return rightBoulderIRSensor.getVoltage();
	}

	public double getleftBoulderIrSensorAverageVoltage() {
		return leftBoulderIRSensor.getAverageVoltage();
	}

	public double getrightBoulderIrSensorAverageVoltage() {
		return rightBoulderIRSensor.getAverageVoltage();
	}
	
	public double getAverageLeftAndRightBoulderIRSensor() {
		return (leftBoulderIRSensor.getVoltage() + rightBoulderIRSensor
				.getVoltage()) / 2;
	}

	// add a getter for The BoulderIRSensor

	public void setCollectorEncoderToZero() {
		motor.setEncPosition(0);
		// ?? this was .setPosition last year... wyatt (2/11/2016)
	}

	public boolean CenterBoulderIsFinished(double loadDistance) {

		// further away is lower
		if (getAverageLeftAndRightBoulderIRSensor() < loadDistance) {

			return true;

		} else {
			return false;
		}
	}

	public boolean loadIsFinished(double loadDistance) {

		// close is a higher value
		if (getAverageLeftAndRightBoulderIRSensor() > loadDistance) {

			return true;

		} else {
			return false;
		}

	}

	public boolean bothIRSensorsCloseEnough(double threshold) {
		if (rightBoulderIRSensor.getVoltage() > threshold
				&& leftBoulderIRSensor.getVoltage() > threshold) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ballIsGone(double ballIsGoneThreshold) {
		if (getAverageLeftAndRightBoulderIRSensor() > ballIsGoneThreshold) {

			return true;

		} else {
			return false;
		}

	}

	public void loadBoulder(double loadDelta) {
		motor.changeControlMode(TalonControlMode.Position);
		// we will also need to set quadfeedbackmode and other things here

		motor.set(loadDelta);
	}

	public void shoot() {
		motor.set(1);
	}

}
