// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.Climber;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;

/**
 *
 */
public class ClimberGoUpDistanceUsingEncoderPosition extends Command {

		double startDistance = 0;
		
    public ClimberGoUpDistanceUsingEncoderPosition() {

        requires(Robot.climber);

    }

    protected void initialize() {
    	
    	startDistance = Robot.climber.getClimberDistanceFromEncoderValue();

     }

    protected void execute() {
    	//pass in robot speed
    	Robot.climber.climberGoUp();
    	//Robot.driveTrain.driveStraight(-1); //speed
    	//System.out.println("Driving and distance is  : " + Robot.driveTrain.getDistanceFromEncoderValues());
    	System.out.println("Climber Lead Current Draw = " +Robot.climber.getClimberLeadCurrent());
    	System.out.println("Climber Follow Current Draw = " +Robot.climber.getClimberFollowCurrent());
    }

    protected boolean isFinished() {
    	
    	//---------------stops the climb if current exceeds 40 Amps ------------------//
    	if(Robot.climber.getClimberLeadCurrent()>100 ||Robot.climber.getClimberLeadCurrent()>100){
    		System.out.println("Current Exceeded During Climb- Climb Aborted");
    		return true;
    	}
    	else if(Robot.climber.getClimberDistanceFromEncoderValue()- startDistance < -50065){ //distance 60000
    		System.out.println("Reached Distance");
    		Robot.driveTrain.enableBrakeMode(true);
    		return true;
    	}
    		else{
    			
    			return false;
    		}
    }

    protected void end() {
    	//Robot.driveTrain.enableBrakeMode(false);
    	//Robot.driveTrain.setDTEncodersToZero();
    	
    
    }

    protected void interrupted() {
    }
    
}
