// Strong Hold BOB 2016 Subsystems (Collector)

package org.usfirst.frc319.subsystems;

import java.util.Set;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

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
    //link the AnalogInput to the RobotMap

    public void initDefaultCommand() {

        setDefaultCommand(new CollectorStop());

    }
    
    public Collector(){
    	motor.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public boolean getBoulderSensor(){
  	 	
    	return boulderSensor.get();
    }
   
    public void collectorGoIn(double speed){    	
    	motor.set(-speed);
    }
    public void stop(){
    	motor.set(0);
    }
    public void collectorGoOut(double speed){
    	motor.set(speed);
    }
    
    public void centerBoulder(double centeringDeltal){
    	motor.changeControlMode(TalonControlMode.Position);
    	//we will also need to set quadfeedbackmode and other things here
    	motor.set(centeringDeltal);
    }
    
    public double getleftBoulderIRSensor(){
    	return leftBoulderIRSensor.getVoltage();
    }
    
    public double getrightBoulderIRSensor(){
    	return rightBoulderIRSensor.getVoltage();
    }
    //add a getter for The BoulderIRSensor
    
    public void setCollectorEncoderToZero(){
    	motor.setEncPosition(0);
    	//?? this was .setPosition last year... wyatt (2/11/2016)
    }
    
    public boolean loadIsFinished(double loadDistance){
    	if (getleftBoulderIRSensor() > loadDistance){
    		
    	return true;	
    	
    	}else{
    		return false;
    	}
    	
    }
    //make an isfinished that gets passed a double from the Load Boulder Command
    
    public void loadBoulder(double loadDelta){
    	motor.changeControlMode(TalonControlMode.Position);
    	//we will also need to set quadfeedbackmode and other things here
    	
    	motor.set(loadDelta);
    }
    
}

