// Strong Hold BOB 2016 Subsystems (Climber)

package org.usfirst.frc319.subsystems;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.Climber.ClimberStop;
import org.usfirst.frc319.commands.Climber.ManualClimb;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Climber extends Subsystem {

    private final CANTalon climberMotorLead = RobotMap.climberMotorLead;
    private final CANTalon climberMotorFollow = RobotMap.climberMotorFollow;
    private final DoubleSolenoid climberSolenoid = RobotMap.climberSolenoid;
    //map the solenoid to here
	public double climberHoldPosition = 0;
	public double climberCurrentPosition = 0;

    
    
    public Climber(){
    	climberMotorLead.changeControlMode(TalonControlMode.PercentVbus);
    	climberMotorLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	climberMotorFollow.changeControlMode(TalonControlMode.Follower);
    	climberMotorFollow.set(climberMotorLead.getDeviceID());
    	climberMotorLead.reverseSensor(true);
    	climberMotorLead.reverseOutput(false);
    	climberMotorFollow.reverseOutput(true);
    }
    public void initDefaultCommand() {

        setDefaultCommand(new ClimberStop());

    }
    public void manualClimb(double joystickValue){
    	
    	
    		if (joystickValue < 0.1 && joystickValue > -0.1) {
    			this.climberMotorLead.changeControlMode(TalonControlMode.Position);
    			climberMotorLead.set(climberHoldPosition);
    		}

    		else {
    			climberMotorLead.changeControlMode(TalonControlMode.PercentVbus);
    			climberMotorLead.set(joystickValue);
    			updateClimberPosition();
    			// armHoldPosition = armCurrentPosition;
    		}
    }
    	
    

	public void updateClimberPosition() {
		this.climberHoldPosition = climberMotorLead.getPosition();
	}

    
    public void climberStop(){
    	climberMotorLead.set(0);
    }
    public void climberGoUp(){
    	climberMotorLead.set(1);
    }
    public void climberGoDown(){
    	climberMotorLead.set(-1);
    }
    public double getClimberDistanceFromEncoderValue(){
    	double climberDistance = climberMotorLead.getEncPosition();
    	return climberDistance;
    }
    public void enableBrakeModeClimber(boolean brakeClimb){
    	this.climberMotorFollow.enableBrakeMode(brakeClimb);
    	this.climberMotorLead.enableBrakeMode(brakeClimb);
    }
    public void deployClimber(){
    	Robot.climber.climberSolenoid.set(DoubleSolenoid.Value.kForward);
    //	Robot.climber.
    }
    public void retractClimber(){
    	Robot.climber.climberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public double getClimberLeadCurrent(){
    	return this.climberMotorLead.getOutputCurrent();
    }
    
    public double getClimberFollowCurrent(){
    	return this.climberMotorFollow.getOutputCurrent();
    }
    
    
    //add deploy climber and retract climber methods
    
}

