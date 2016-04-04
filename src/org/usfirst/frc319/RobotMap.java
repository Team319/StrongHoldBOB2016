// Strong Hold BOB 2016 Robot Map


package org.usfirst.frc319;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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

    public static void init() {
        
    	//-----   Drive Train   -----
    	driveTrainleftDriveLead = new CANTalon(7);
        driveTrainleftDriveFollow = new CANTalon(6);
        driveTrainrightDriveLead = new CANTalon(2);
        driveTrainrightDriveFollow = new CANTalon(3);
        
        LiveWindow.addActuator("driveTrain", "rightDriveLead", driveTrainrightDriveLead);
        LiveWindow.addActuator("driveTrain", "leftDriveLead", driveTrainleftDriveLead);
        // Test these to make sure if they are a problem or not (Wyatt-1/27/2016)
        // Turned it back on seems to work (Joshua 1/27/16 3:21pm)
        LiveWindow.addActuator("driveTrain", "leftDriveFollow", driveTrainleftDriveFollow);
        LiveWindow.addActuator("driveTrain", "rightDriveFollow", driveTrainrightDriveFollow);
        
        driveTraindriveTrain = new RobotDrive(driveTrainleftDriveLead, driveTrainrightDriveLead);
        
        driveTraindriveTrain.setSafetyEnabled(true);
        driveTraindriveTrain.setExpiration(0.1);
        driveTraindriveTrain.setSensitivity(0.5);
        driveTraindriveTrain.setMaxOutput(1.0);

        driveTrainshifter = new DoubleSolenoid(0, 0, 1);
        LiveWindow.addActuator("driveTrain", "shifter", driveTrainshifter);
        
        climberSolenoid = new DoubleSolenoid(0, 2, 3);
        
        gyro= new AnalogGyro(0);// Must be on input 0!
        gyro.setSensitivity(0.0067);//tuned to 360 for gyro on blue bot
        
        
        //-----   Collector   -----
        
        
        collectorcollectorMotor = new CANTalon(9);//moved to 9 from 8
        LiveWindow.addActuator("collector", "collectorMotor", collectorcollectorMotor);
        
        collectorboulderSensor = new DigitalInput(0);
        LiveWindow.addSensor("collector", "boulderSensor", collectorboulderSensor);
        
        leftBoulderIRSensor = new AnalogInput(1);
        LiveWindow.addSensor("collector", "leftBoulderIRSensor", leftBoulderIRSensor);
        //Also Map the AnalogIR sensor to AnalogInput(0);
        
        rightBoulderIRSensor = new AnalogInput(2);
        LiveWindow.addActuator("collector", "rightBoulderIRSensor", rightBoulderIRSensor);
        
        //-----   Shooter   -----
        shooterleftShooter = new CANTalon(5);//changed from 9 - orange bot complete - to switch on blue bot 
        LiveWindow.addActuator("shooter", "leftShooter", shooterleftShooter);
        
        shooterrightShooter = new CANTalon(4); //changing from 0 - orange bot complete - to switch on blue bot
        LiveWindow.addActuator("shooter", "rightShooter", shooterrightShooter);
        
        //-----   Arm   -----
        //moved to 0 when installing climber from 1
        armMotor = new CANTalon(0);
        LiveWindow.addActuator("arm", "armMotor", armMotor);
        
        //-----   Climber   -----
        climberMotorLead = new CANTalon(8);//moved climber to 8 from 9
        LiveWindow.addActuator("climber", "climberMotorLead", climberMotorLead);
        
        climberMotorFollow = new CANTalon(1);//moved climber follow to 1 from 0
        LiveWindow.addActuator("climber", "climberMotorFollow", climberMotorFollow);
        
        //-----   Camera   -----
        towerCameratowerRelay = new Relay(0);
        LiveWindow.addActuator("towerCamera", "towerRelay", towerCameratowerRelay);
        
        //-----   Compressor   -----
        compressorcompressor = new Compressor(0);
        compressorpressureSensor = new AnalogInput(3);
        
        LiveWindow.addSensor("compressor", "pressureSensor", compressorpressureSensor);
        
    }
    
}
