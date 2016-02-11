   // Strong Hold BOB 2016 Subsystems (Arm)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class arm extends Subsystem {

    private final CANTalon armMotor = RobotMap.armarmMotor;

    double p = 1;
    double i = 0;
    double d = 0;
    double f = 0;
    
    int iZone = 0;
    double rampRate = 0;
    int profile = 0;
    
    public double armHoldPosition = 0;
    public double armCurrentPosition = 0;
    
    public void initDefaultCommand() {

        setDefaultCommand(new ArmManualDrive());

    }

    public void arm (){
    	armMotor.changeControlMode(TalonControlMode.Position);
    	armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	armMotor.setPID(p, i, d, f, iZone, rampRate, profile);
		armMotor.reverseSensor(false);  //needs to be tested
		armMotor.reverseOutput(true);   //needs to be tested
	
		//armMotor.enableLimitSwitch(true, true);  //turn on once limit switches are installed plz :)
    	
    }
    public boolean getArmTopLimit(){
    	return armMotor.isFwdLimitSwitchClosed();
    	
    }
    
    public boolean getArmBottomLimit(){
    return armMotor.isRevLimitSwitchClosed();
    
    }
    
    public void armManualDrive (double triggerValue){
    	
   if(triggerValue < 0.1 && triggerValue > -0.1){
	   
	   armMotor.changeControlMode(TalonControlMode.Position);
	   armMotor.set(armHoldPosition);
	   }
   
   
   else {
	   armMotor.changeControlMode(TalonControlMode.PercentVbus);
	   armMotor.set(triggerValue);
	   armCurrentPosition = armMotor.getEncPosition();
	   armHoldPosition = armCurrentPosition;
	   
        }
    }
   
   public boolean isFinished(){
	   double error = armMotor.getEncPosition() - armMotor.getSetpoint();
	   
	   armHoldPosition = armMotor.getEncPosition();
	   
	   if(getArmBottomLimit() && error < 0){
		   armMotor.setPosition(0);
		   return true;
	    }
	   else if(error<50 && error>-50){
		   return true;
	    }else{
		   return false;
	    }
   }
   
    
   
   public void goToCollect(double position){
	   armMotor.set(position);
   }
   
   public void goToStorage(){
	   armMotor.set(0);
   }
   
   public void gotToShootFromTower(){
	   armMotor.set(500);
   }
   
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


