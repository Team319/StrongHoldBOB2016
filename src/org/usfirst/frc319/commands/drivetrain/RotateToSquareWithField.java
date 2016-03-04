// Strong Hold BOB 2016 Commands (Collector   In)

package org.usfirst.frc319.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class RotateToSquareWithField extends PIDCommand {
	double setpoint = 0;
	static double P = 0;
	static double I = 0;
	static double D = 0;
	
    public RotateToSquareWithField() {
    	
    	super("RotateToAngle", P, I, D, 0.02);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(0);//
    	//Absolute Tolerance is basically an inherited Is finished
    	//that the PID controller uses in "OnTarget" - 
    	//within how many degrees is good enough to end the command

    	
    	setpoint = 0;
    	
        requires(Robot.driveTrain);

    }
    
    protected double returnPIDInput() {
    	
    	return Robot.driveTrain.getGyroAngle(); // the input to the PID command
    											//what you want to change
    }
    
    protected void usePIDOutput(double output) {
    	
    	
    	Robot.driveTrain.arcadeDrive(0, output);//what you want to move in order
    											// to change
    }
    
    
    protected void initialize() {
    
    	this.setSetpoint(Robot.driveTrain.getGyroAngle() + setpoint);
    	this.getPIDController().enable();
    	System.out.println("I have the setpoint and its"+ setpoint);
    }

    protected void execute() {
    	PIDController controller = this.getPIDController();
   
    }

    protected boolean isFinished() {
    	return this.getPIDController().onTarget();
    	
    }

    protected void end() {
    	
    
    }

    protected void interrupted() {
    }  
}
