// Strong Hold BOB 2016 Subsystems (Collector)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class collector extends Subsystem {

    private final CANTalon collectorMotor = RobotMap.collectorcollectorMotor;
    private final DigitalInput boulderSensor = RobotMap.collectorboulderSensor;

    public void initDefaultCommand() {

        setDefaultCommand(new CollectorStop());

    }
    
    public collector(){
    	collectorMotor.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public boolean getBoulderSensor(){
  	 	
    	return Robot.collector.boulderSensor.get();
    }
   
    public void collectorGoIn(double speed){
    	
    	Robot.collector.collectorMotor.set(speed);
    }
    public void collectorStop(double speed){
    	Robot.collector.collectorMotor.set(speed);
    }
    public void collectorGoOut(double speed){
    	Robot.collector.collectorMotor.set(-speed);
    }
    
    
}

