// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc319.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */ 



public class ShooterSpeedUp extends Command {

	private static int atSpeedCounter =0;
	private static int numberOfSamplesAtSpeed= 20;
	
    public ShooterSpeedUp() {
        requires(Robot.shooter);

    }

    protected void initialize() {
    	atSpeedCounter = 0;
    	
    }

    protected void execute() {
    	
    	if(Robot.shooter.isShooterAtSpeed()){
    		atSpeedCounter++;
    		//System.out.println("atSpeedCOunter"+atSpeedCounter);
    	}
    	//System.out.println("Right Setpoint: " + Robot.shooter.getRightShooterSetpoint());
    	//System.out.println("Left Setpoint: " + Robot.shooter.getLeftShooterSetpoint());
    	//System.out.println("Right Speed: " + Robot.shooter.getRightShooterSpeed());
    	//System.out.println("Left Speed: " + Robot.shooter.getLeftShooterSpeed());
    	//possibly add an elseif its not at speed reset the counter
    	
    	Robot.shooter.setLeftShooterSpeed(Robot.constants.getConstant(BobConstants.SHOOTER_HIGH_SPEED_KEY));
    	Robot.shooter.setRightShooterSpeed(Robot.constants.getConstant(BobConstants.SHOOTER_HIGH_SPEED_KEY));
    	
    }

    protected boolean isFinished() {
        return atSpeedCounter>numberOfSamplesAtSpeed;
    }

    protected void end() {
    	atSpeedCounter = 0;
    }

    protected void interrupted() {
    }
    
}
