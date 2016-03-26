// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc319.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ShooterGoAuto extends Command {

    public ShooterGoAuto() {

        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	Robot.shooter.setLeftShooterSpeed(Robot.constants.getConstant(BobConstants.SHOOTER_HIGH_SPEED_KEY));
    	Robot.shooter.setRightShooterSpeed(Robot.constants.getConstant(BobConstants.SHOOTER_HIGH_SPEED_KEY));
    	
    }

    protected boolean isFinished() {
        return true;
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
