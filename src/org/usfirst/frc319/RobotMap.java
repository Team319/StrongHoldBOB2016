// Strong Hold BOB 2016 Robot Map

package org.usfirst.frc319;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static CANTalon driveTrainleftDriveLead;
	public static CANTalon driveTrainrightDriveLead;
	public static CANTalon driveTrainleftDriveFollow;
	public static CANTalon driveTrainrightDriveFollow;
	public static RobotDrive driveTraindriveTrain;
	public static DoubleSolenoid driveTrainshifter;
	public static DoubleSolenoid climberSolenoid;
	public static CANTalon collectorcollectorMotor;
	public static DigitalInput collectorboulderSensor;
	public static AnalogInput leftBoulderIRSensor;
	public static AnalogInput rightBoulderIRSensor;
	public static CANTalon shooterleftShooter;
	public static CANTalon shooterrightShooter;
	public static CANTalon armMotor;
	public static CANTalon climberMotorLead;
	public static CANTalon climberMotorFollow;
	public static Relay towerCameratowerRelay;
	public static Compressor compressorcompressor;
	public static AnalogInput compressorpressureSensor;
	public static AnalogGyro gyro;
	public static BuiltInAccelerometer rioAccelerometer;
	public static Relay flashLight;

	public static void init() {

		// ----- Drive Train -----
		driveTrainleftDriveLead = new CANTalon(7);
		driveTrainleftDriveFollow = new CANTalon(6);
		driveTrainrightDriveLead = new CANTalon(2);
		driveTrainrightDriveFollow = new CANTalon(3);

		driveTraindriveTrain = new RobotDrive(driveTrainleftDriveLead,
				driveTrainrightDriveLead);

		driveTraindriveTrain.setSafetyEnabled(true);
		driveTraindriveTrain.setExpiration(0.1);
		driveTraindriveTrain.setSensitivity(0.5);
		driveTraindriveTrain.setMaxOutput(1.0);

		driveTrainshifter = new DoubleSolenoid(0, 0, 1);

		climberSolenoid = new DoubleSolenoid(0, 2, 3);

		gyro = new AnalogGyro(0);// Must be on input 0!
		gyro.setSensitivity(0.0067);// tuned to 360 for gyro on blue bot
		rioAccelerometer = new BuiltInAccelerometer();

		// ----- Collector -----

		collectorcollectorMotor = new CANTalon(9);

		leftBoulderIRSensor = new AnalogInput(1);
		rightBoulderIRSensor = new AnalogInput(2);

		// ----- Shooter -----
		shooterleftShooter = new CANTalon(5);
		shooterrightShooter = new CANTalon(4);

		// ----- Arm -----
		armMotor = new CANTalon(0);
		flashLight = new Relay(2);

		// ----- Climber -----
		climberMotorLead = new CANTalon(8);
		climberMotorFollow = new CANTalon(1);

		// ----- Camera -----
		towerCameratowerRelay = new Relay(0);

		// ----- Compressor -----
		compressorcompressor = new Compressor(0);
		compressorpressureSensor = new AnalogInput(3);

	}

}
