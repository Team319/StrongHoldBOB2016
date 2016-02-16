package org.usfirst.frc319.commands.camera;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.subsystems.TowerCamera;

import com.team319.trajectory.CombinedSrxMotionProfile;
import com.team319.trajectory.PathManager;
import com.team319.trajectory.TrajectoryChangeListener;
import com.team319.trajectory.TrajectoryManager;
import com.team319.trajectory.Waypoint;

import edu.wpi.first.wpilibj.command.Command;

public class CalculateSpline extends Command implements TrajectoryChangeListener{
	
	private static final double BACK_OFF = 3.5;
	private boolean waitingForTrajectory = true;
	
	public CalculateSpline() {
		requires(Robot.towerCamera);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		TrajectoryManager.getInstance().registerListener(this);
		
		//gets the distance and sends it to the Trajectory Server
		Robot.towerCamera.processFrame();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		return !waitingForTrajectory;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		TrajectoryManager.getInstance().unregisterListener(this);
		waitingForTrajectory = true;
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onTrajectoryChange(CombinedSrxMotionProfile combined) {
		Robot.driveTrain.setCurrentProfile(combined);
		waitingForTrajectory = false;
	}

}
