package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.camera.CameraDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarAuto extends CommandGroup {
	public LowBarAuto(){
		//similar code to final desired but simple
		addParallel(new ArmGoToCollect());
		addSequential(new FollowBothMotionProfiles());
		//addSequential(new FollowBothMotionProfilesTurnToTower());
		addSequential(new SpeedUpShooter());
		addParallel(new SpeedUpShooter());
		addParallel(new Shoot());
		
		
		//Arm to Collect
		//Drive Low Bar Spline (end at 60 degrees)
		//Gyro to 60
		//Measure Distance (via camera)
		//Drive to Known Distance
		//Measure Y (via camera's X)
		//Calculate Waypoints & Send Waypoints to server & Receive Trajectory
		//Drive Trajectory
		//Speed Up Shooter
		//Shoot
		
		
		
		
		
		
		/*Final Desired
		addSequential(new ArmGoToCollect());
		//addSequential(new DriveOverLowGoal());		//PID drive straight a distance
		//addSequential(new Turn to zero()); based on gyro
		//addSequential(new TurnToLeftTarget()); 		//PID based on gyro
		addSequential(new ArmGoToAutoSearchPosition());	//what angle is this?
		addSequential(new CameraDrive());				// Motion Profile - S turn
		addSequential(new ArmGoToAutoShootPosition());	// maybe parallel
		//addSequential(new SpeedUpShooter());
		//addSequential(new SpeedUpShooter());
		//addParallel(new Shoot());
		 * 
		 */
	}
}
