// Strong Hold BOB 2016 Robot Map


package org.usfirst.frc319;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
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
    public static CANTalon collectorcollectorMotor;
    public static DigitalInput collectorboulderSensor;
    public static CANTalon shooterleftShooter;
    public static CANTalon shooterrightShooter;
    public static CANTalon armarmMotor;
    public static CANTalon climberclimberMotor;
    public static Relay towerCameratowerRelay;
    public static Compressor compressorcompressor;
    public static AnalogInput compressorpressureSensor;

    public static void init() {
        
    	//-----   Drive Train   -----
    	driveTrainleftDriveLead = new CANTalon(0);
        driveTrainleftDriveFollow = new CANTalon(1);
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

        driveTrainshifter = new DoubleSolenoid(1, 0, 1);
        LiveWindow.addActuator("driveTrain", "shifter", driveTrainshifter);
        
        //-----   Collector   -----
        collectorcollectorMotor = new CANTalon(6);
        LiveWindow.addActuator("collector", "collectorMotor", collectorcollectorMotor);
        
        collectorboulderSensor = new DigitalInput(0);
        LiveWindow.addSensor("collector", "boulderSensor", collectorboulderSensor);
        
        //-----   Shooter   -----
        shooterleftShooter = new CANTalon(4);
        LiveWindow.addActuator("shooter", "leftShooter", shooterleftShooter);
        
        shooterrightShooter = new CANTalon(5);
        LiveWindow.addActuator("shooter", "rightShooter", shooterrightShooter);
        
        //-----   Arm   -----
        armarmMotor = new CANTalon(7);
        LiveWindow.addActuator("arm", "armMotor", armarmMotor);
        
        //-----   Climber   -----
        climberclimberMotor = new CANTalon(8);
        LiveWindow.addActuator("climber", "climberMotor", climberclimberMotor);
        
        //-----   Camera   -----
        towerCameratowerRelay = new Relay(0);
        LiveWindow.addActuator("towerCamera", "towerRelay", towerCameratowerRelay);
        
        //-----   Compressor   -----
        compressorcompressor = new Compressor(1);
        compressorpressureSensor = new AnalogInput(0);
        
        LiveWindow.addSensor("compressor", "pressureSensor", compressorpressureSensor);
        
    }
    
}
