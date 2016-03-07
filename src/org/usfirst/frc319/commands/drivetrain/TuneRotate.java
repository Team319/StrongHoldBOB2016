
// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.commands.camera.Target;
import org.usfirst.frc319.commands.camera.TargetManager;

import com.team319.pid.IPidChangeListener;
import com.team319.pid.Pid;
import com.team319.pid.status.PidStatus;
import com.team319.pid.status.PidStatusManager;

/**
 *
 */
public class TuneRotate extends PIDCommand {
	public static final double IZONE = 25.0;
	public static final int NUM_SAMPLES_REQUIRED = 10;
	public static final double TOLERANCE = 1;

	double setpoint = 0;
	static double P = .025;
	static double I = 0.0022;
	static double D = 0.0;

	long startTime;
	public int numSamplesOnTarget = 0;

    public TuneRotate() {
    	super("RotateToAngle", P, I, D, 0.02);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(1);//
    	//Absolute Tolerance is basically an inherited Is finished
    	//that the PID controller uses in "OnTarget" -
    	//within how many degrees is good enough to end the command


        requires(Robot.driveTrain);

    }

    protected double returnPIDInput() {

    	return Robot.driveTrain.getGyroAngle(); // the input to the PID command
    											//what you want to change
    }

    int x = 0;
    protected void usePIDOutput(double output) {
    	x++;
    	if(x++ % 5 == 0){
    		x = 0;
    		PidStatus status = new PidStatus();
    		status.setTime(System.currentTimeMillis() - startTime);
    		status.setOutput(output);
    		status.setSetpoint(45);
    		status.setPosition(Robot.driveTrain.getGyroAngle());
    		status.setError(this.getPIDController().getError());
    		PidStatusManager.getInstance().setStatus(status);

    	}

    	double limit = .8; //0.8	//lets not do this part to start with
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
    	Robot.driveTrain.resetGyro();
    	//Robot.driveTrain.setVoltageRampRate(36.0);
    	Target target = TargetManager.getInstance().getTarget();

    	setpoint = target.getHorizontalOffset();

    	setpoint *= -1;

    	//setpoint = 10;

    	SmartDashboard.putNumber("Rotation Setpoint", setpoint);

    	startTime = System.currentTimeMillis();
    	numSamplesOnTarget = 0;

    	this.setSetpoint(Robot.driveTrain.getGyroAngle() + setpoint);
    	this.getPIDController().enable();
    	System.out.println("I have the setpoint and its"+ setpoint);
    }



    protected void execute() {
    	if(Robot.driveTrain.getPid() != null){
    		P = Robot.driveTrain.getPid().getP();
    		I = Robot.driveTrain.getPid().getI();
    		D = Robot.driveTrain.getPid().getD();
    	}



    	PIDController controller = this.getPIDController();
    	if (Math.abs(controller.getError()) > IZONE){ //maybe not at first but if we need it.
    		controller.setPID(P, 0, D);
    	}
    	else{
    		controller.setPID(P, I, D);
    	}

    	SmartDashboard.putNumber("P", P);
    	SmartDashboard.putNumber("I", I);
    	SmartDashboard.putNumber("Error", controller.getError());

    }

    protected boolean isFinished() {
    	return isOnTarget();
    	/**
    	boolean actuallyFinished = false;
    	if (Math.abs(this.getPIDController().getError()) < TOLERANCE)
    	{
    		numSamplesOnTarget++;
    	}
    	else numSamplesOnTarget = 0;

    	if (numSamplesOnTarget >= NUM_SAMPLES_REQUIRED)
    	{
    		actuallyFinished = true;
    	}
    	SmartDashboard.putBoolean("Rotate is Finished", actuallyFinished);
    	return actuallyFinished;
    	**/
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
    	//Robot.driveTrain.setVoltageRampRate(144.0);

    }

    protected void interrupted() {
    }
}





//----pasted code from slack 3/6/16-------//
/*
// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.commands.camera.Target;
import org.usfirst.frc319.commands.camera.TargetManager;

import com.team319.pid.IPidChangeListener;
import com.team319.pid.Pid;


public class TuneRotate extends PIDCommand {
	public static final double IZONE = 6.0;
	public static final int NUM_SAMPLES_REQUIRED = 10;
	public static final double TOLERANCE = 0.3;

	double setpoint = 0;
	static double P = .025;
	static double I = 0.0022;
	//static double I_small = 0.001;
	static double D = 0;

	long startTime;
	public int numSamplesOnTarget = 0;

    public TuneRotate() {
    	super("RotateToAngle", P, I, D, 0.02);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(1);//
    	//Absolute Tolerance is basically an inherited Is finished
    	//that the PID controller uses in "OnTarget" -
    	//within how many degrees is good enough to end the command


        requires(Robot.driveTrain);

    }

    protected double returnPIDInput() {

    	return Robot.driveTrain.getGyroAngle(); // the input to the PID command
    											//what you want to change
    }
    protected void usePIDOutput(double output) {
    	
    	double limit = 1; //0.8	//lets not do this part to start with
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
    	Robot.driveTrain.resetGyro();
    	//Robot.driveTrain.setVoltageRampRate(36.0);
    	Target target = TargetManager.getInstance().getTarget();

    	setpoint = target.getHorizontalOffset();

    	setpoint *= -1;

    	SmartDashboard.putNumber("Rotation Setpoint", setpoint);

    	startTime = System.currentTimeMillis();
    	numSamplesOnTarget = 0;

    	this.setSetpoint(Robot.driveTrain.getGyroAngle() + setpoint);
    	this.getPIDController().enable();
    	System.out.println("I have the setpoint and its"+ setpoint);
    }


    protected void execute() {
    	if(Robot.driveTrain.getPid() != null){
    		P = Robot.driveTrain.getPid().getP();
    		I = Robot.driveTrain.getPid().getI();
    		D = Robot.driveTrain.getPid().getD();
    	}

    	PIDController controller = this.getPIDController();
    	if (Math.abs(controller.getError()) > IZONE){ //maybe not at first but if we need it.
    		controller.setPID(P, 0, D);
    	}
    	else{
    		controller.setPID(P, I, D);
    	}

    	SmartDashboard.putNumber("P", P);
    	SmartDashboard.putNumber("I", I);
    	SmartDashboard.putNumber("Error", controller.getError());

    }

    protected boolean isFinished() {
    	boolean actuallyFinished = false;
    	if (Math.abs(this.getPIDController().getError()) < TOLERANCE)
    	{
    		numSamplesOnTarget++;
    	}
    	else numSamplesOnTarget = 0;

    	if (numSamplesOnTarget >= NUM_SAMPLES_REQUIRED)
    	{
    		actuallyFinished = true;
    	}
    	SmartDashboard.putBoolean("Rotate is Finished", actuallyFinished);
    	return actuallyFinished;
    }

    protected void end() {

    	this.getPIDController().reset();
    	//Robot.driveTrain.setVoltageRampRate(144.0);

    }

    protected void interrupted() {
    }
}
*/
