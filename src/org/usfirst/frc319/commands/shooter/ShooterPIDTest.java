// Strong Hold BOB 2016 Commands (Shooter PID Test)

package org.usfirst.frc319.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ShooterPIDTest extends Command {

    public ShooterPIDTest() {

        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	//ONLY UNCOMMENT ONE AT A TIME!
    	//Robot.shooter.rightShooterpIDTest();
    	Robot.shooter.leftShooterpIDTest();
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
