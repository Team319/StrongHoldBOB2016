package org.usfirst.frc319.commands.camera;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.subsystems.TowerCamera;

import com.team319.trajectory.CombinedSrxMotionProfile;
import com.team319.vision.ITargetListener;
import com.team319.vision.Target;
import com.team319.vision.TargetManager;
import com.team319.waypoint.WaypointManager;
import com.team319.trajectory.ITrajectoryChangeListener;
import com.team319.trajectory.TrajectoryManager;
import com.team319.waypoint.Waypoint;
import com.team319.waypoint.WaypointList;
import com.team319.waypoint.WaypointManager;

import edu.wpi.first.wpilibj.command.Command;

public class CameraGetTarget extends Command implements ITargetListener{
	
	private boolean waitingForTarget = true;
	private long startTime = 0;
	
	public CameraGetTarget() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		TargetManager.getInstance().registerListener(this);
		startTime = System.currentTimeMillis();
	    
	}
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		System.out.println("Waiting for Target");
	}

	@Override 
	protected boolean isFinished() {
		return !waitingForTarget;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		TargetManager.getInstance().unregisterListener(this);
		waitingForTarget = true;
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		TargetManager.getInstance().unregisterListener(this);
		
	}
	
	@Override
	public void onTargetChange(Target target) {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis() - startTime < 500){
			return;
		}
		System.out.println("Adjust " + target.getHorizontalOffset());
		Robot.driveTrain.setTargetHorizontalOffset(target.getHorizontalOffset());
		//Robot.arm.setTargetVerticalOffset(target.getVerticalOffset());
		waitingForTarget = false;
	}

}
