// Strong Hold BOB 2016 Subsystems (Shooter)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class shooter extends Subsystem {
	
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;
	
    private final CANTalon leftShooter = RobotMap.shooterleftShooter;
    private final CANTalon rightShooter = RobotMap.shooterrightShooter;

    public shooter(){
    	rightShooter.changeControlMode(TalonControlMode.Speed);
    	rightShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	rightShooter.reverseSensor(true);
    	//when installed check to make sure encoder and voltage are matched  	
    	rightShooter.configNominalOutputVoltage(+0.0f, +0.0f);
    	rightShooter.configPeakOutputVoltage(+12.0f,  0.0f);
    	rightShooter.setProfile(0);
    	rightShooter.setF(0.02997);
    	rightShooter.setP(0.1133);
    	rightShooter.setI(0);
    	rightShooter.setD(0);
    	
    	leftShooter.changeControlMode(TalonControlMode.Speed);
    	leftShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	leftShooter.reverseSensor(true);
    	//check to make sure encoder and voltage are matched
    	leftShooter.configNominalOutputVoltage(+0.0f, +0.0f);
    	leftShooter.configPeakOutputVoltage(+12.0f,  0.0f);
    	leftShooter.setProfile(0);
    	leftShooter.setF(0.02997); //0.02997
    	leftShooter.setP(0.11); //0.1133
    	leftShooter.setI(.0011);
    	leftShooter.setD(1.1); //1.133
    	leftShooter.setIZone(50);
    	
    }

    public void initDefaultCommand() {     
        setDefaultCommand(new ShooterPIDTest());
    }
    
    public void rightShooterSpin(){
    	rightShooter.set(3000);
    	
    }
    
    public void leftShooterSpin(){
    	leftShooter.set(-3000);
    }
    
    public void rightShooterStop(){
    	rightShooter.set(0);
    	
    }
    
    public void leftShooterStop(){
    	leftShooter.set(0);
    	
    }
    
    public double leftShooterSpeed(){
    	return leftShooter.getSpeed();
    }
    
    public double rightShooterSpeed(){
    	return rightShooter.getSpeed();
    	
    }
    
    public double leftMotorOutputVoltage(){
    	
    return leftShooter.getOutputVoltage();
    }
    
    public double leftMotorBusVoltage(){
    
    return leftShooter.getBusVoltage();
    }
    
    		
    public void rightShooterpIDTest(){
    	double motorOutput = rightShooter.getOutputVoltage()/rightShooter.getBusVoltage();
    	double motorSpeed = rightShooter.getSpeed();
    	
    	_sb.append("\tout:");
    	_sb.append(motorOutput);
    	_sb.append("\tspd:");
    	_sb.append(motorSpeed);
    	
    if(Robot.oi.xBoxController.getRawButton(1)){
    	/*Speed mode*/
    	
    	double rightFixedTargetSpeed = 3000;
    	//double targetSpeed = Robot.oi.xBoxController.getRawAxis(0) * 1500.0;
    	rightShooter.changeControlMode(TalonControlMode.Speed);
    	rightShooter.set(rightFixedTargetSpeed);
    	
    	_sb.append("\terr:");
		_sb.append(rightShooter.getClosedLoopError());
		_sb.append("ttrg:");
		_sb.append(rightFixedTargetSpeed);
		
    }else{
    	rightShooter.changeControlMode(TalonControlMode.PercentVbus);
    	rightShooter.set(Robot.oi.xBoxController.getRawAxis(0));
    
    }
    
    if(++_loops >= 10){
    		_loops = 0;
    		System.out.println(_sb.toString());
    	
    	}
    	
        _sb.setLength(0);
    
    }
    
    public void leftShooterpIDTest(){
    	double motorOutput = leftShooter.getOutputVoltage()/leftShooter.getBusVoltage();
    	double motorSpeed = leftShooter.getSpeed();
    	
    	_sb.append("\tout:");
    	_sb.append(motorOutput);
    	_sb.append("\tspd:");
    	_sb.append(motorSpeed);
    	
    if(Robot.oi.xBoxController.getRawButton(1)){
    	
    	double leftFixedTargetSpeed =4000;
    	double targetSpeed = Robot.oi.xBoxController.getRawAxis(0) * 1500.0;
    	
    	leftShooter.changeControlMode(TalonControlMode.Speed);
    	leftShooter.set(leftFixedTargetSpeed); //leftFixedTargetSpeed
    	
    	_sb.append("\terr:");
    	_sb.append(leftShooter.getClosedLoopError());
    	_sb.append("ttrg:");
    	_sb.append(leftFixedTargetSpeed); //leftFixedTargetSpeed
    	
    	} else{
    	
    	leftShooter.changeControlMode(TalonControlMode.PercentVbus);
    	leftShooter.set(Robot.oi.xBoxController.getRawAxis(0));
    
    	}
    
    if(++_loops >=10){
    	_loops = 0;
    	System.out.println(_sb.toString());
   
    	}
  
    _sb.setLength(0);
    
    }    
}

