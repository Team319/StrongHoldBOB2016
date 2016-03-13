// Strong Hold BOB 2016 Commands (Arcade Drive)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;

/**
 *
 */
public class ArcadeDrive extends Command {

   
    public ArcadeDrive() {
  
        requires(Robot.driveTrain);

    }

    protected void initialize() {
    	RobotMap.driveTrainrightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveTrainrightDriveFollow.changeControlMode(TalonControlMode.Follower);
    	RobotMap.driveTrainrightDriveFollow.set(RobotMap.driveTrainrightDriveLead.getDeviceID());
     	RobotMap.driveTrainleftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
     	RobotMap.driveTrainleftDriveFollow.changeControlMode(TalonControlMode.Follower);
     	RobotMap.driveTrainleftDriveFollow.set(RobotMap.driveTrainleftDriveLead.getDeviceID());
    }

    protected void execute() {
    	double moveValue = Robot.oi.driverController.getLeftStickY();
    	double rotateValue = Robot.oi.driverController.getRightStickX();
    	
    	Robot.driveTrain.arcadeDrive(-1 * moveValue, rotateValue);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
