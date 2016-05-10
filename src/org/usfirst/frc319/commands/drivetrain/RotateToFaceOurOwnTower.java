// Strong Hold BOB 2016 Commands (Collector   In)

package org.usfirst.frc319.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class RotateToFaceOurOwnTower extends PIDCommand {
	
	
	
	public static final double IZONE = 25.0;
	public static final double TOLERANCE =1;
	
	double setpoint = 0;
	static double P = 0.025;
	static double I = 0.0022;
	static double D = 0;
	
    public RotateToFaceOurOwnTower() {
    	
    	super("RotateToAngle", P, I, D, 0.02);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(1);//
    	//Absolute Tolerance is basically an inherited Is finished
    	//that the PID controller uses in "OnTarget" - 
    	//within how many degrees is good enough to end the command

    	
    	setpoint = 180;
    	
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
    
    	this.setSetpoint(setpoint);
    	this.getPIDController().enable();
    	System.out.println("I have the setpoint and its"+ setpoint);
    }

    protected void execute() {
    	PIDController controller = this.getPIDController();
    	if (Math.abs(controller.getError()) > IZONE){ //maybe not at first but if we need it.
    		controller.setPID(P, 0, D);
    	}
    	else{
    		controller.setPID(P, I, D);
    	}
    }

    protected boolean isFinished() {
    	return isOnTarget();
    	
    }
    private double prevError = 0;
    
	public boolean isOnTarget(){
		double currentError = this.getPIDController().getError();
		double errorChange = prevError - currentError;
		prevError = currentError;

		boolean actuallyFinished = false;

		if (Math.abs(currentError) < TOLERANCE ){//&& errorChange == 0 ) {
			actuallyFinished = true;
		}
		SmartDashboard.putBoolean("Rotate is Finished", actuallyFinished);
		return actuallyFinished;
	}

    protected void end() {
    	
    this.getPIDController().reset();
    }

    protected void interrupted() {
    }  
}  