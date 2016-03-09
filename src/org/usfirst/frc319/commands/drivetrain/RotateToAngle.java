// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.commands.camera.Target;
import org.usfirst.frc319.commands.camera.TargetManager;

import com.team319.pid.status.PidStatus;
import com.team319.pid.status.PidStatusManager;

/**
 *
 */
public class RotateToAngle extends PIDCommand {
	
	double setpoint = 0;
	static double P = .036;
	static double I = 0.0016;
	static double D = 0;
	
    public RotateToAngle(double degrees) {
    	
    	super("RotateToAngle", P, I, D, 0.02);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(1);//
    	//Absolute Tolerance is basically an inherited Is finished
    	//that the PID controller uses in "OnTarget" - 
    	//within how many degrees is good enough to end the command

    	
    	setpoint = degrees;
    	
        requires(Robot.driveTrain);

    }
    
    protected double returnPIDInput() {
    	
    	return Robot.driveTrain.getGyroAngle(); // the input to the PID command
    											//what you want to change
    }
    
    protected void usePIDOutput(double output) {
    	
    	
    	double limit = .5; //0.8	//lets not do this part to start with
    	if (output < -limit){
    		output = -limit;
    	}
    	else if (output > limit){
    		output = limit;
    	}
        Robot.driveTrain.arcadeDrive(0, output);////what you want to move in order
		// to change
    }
    
    
    protected void initialize() {
    	
    	this.setSetpoint(Robot.driveTrain.getGyroAngle() + setpoint);
    	this.getPIDController().enable();
    	System.out.println("I have the setpoint and its"+ setpoint);
    }

    protected void execute() {
    	PIDController controller = this.getPIDController();
    	
    	if (Math.abs(controller.getError()) > 5){ //maybe not at first but if we need it.
    		controller.setPID(P, 0, D);    		
    	}
    	else{
    		controller.setPID(P, I, D); 
    	}
    	
    	
   
    }

    protected boolean isFinished() {
    	return this.getPIDController().onTarget();
    	
    }

    protected void end() {
    
    	this.getPIDController().reset();
    
    }

    protected void interrupted() {
    }  
}
