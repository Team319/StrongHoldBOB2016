package org.usfirst.frc319.commands.drivetrain;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.subsystems.TowerCamera;

import com.team319.trajectory.CombinedSrxMotionProfile;
import com.team319.waypoint.WaypointList;
import com.team319.waypoint.WaypointManager;
import com.team319.trajectory.ITrajectoryChangeListener;
import com.team319.trajectory.TrajectoryManager;
import com.team319.waypoint.Waypoint;
import com.team319.web.trajectory.client.TrajectoryClient;

import edu.wpi.first.wpilibj.command.Command;

public class BuildPosition4Spline extends Command implements ITrajectoryChangeListener{
	
	private static final double BACK_OFF = 3.5;
	private boolean waitingForTrajectory = true;
	
	public BuildPosition4Spline() {
		requires(Robot.driveTrain);
	}
	
	public void buildSpline(){
		List<Waypoint> waypoints = new ArrayList<Waypoint>();
		/**
		//testing
		waypoints.add(new Waypoint(0,0,0));
		waypoints.add(new Waypoint (9,-3.3,0));
		**/
		//waypoints.add(new Waypoint(17,-16,0));
		//waypoints.add(new Waypoint(22, -14, 0));
		waypoints.add(new Waypoint(15,-15,0));
		waypoints.add(new Waypoint (19.5,-13.5,0));
		try {
			WaypointList waypointList = new WaypointList(waypoints);
			waypointList.setCachable(true);
			WaypointManager.getInstance().setWaypointList(waypointList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initialize() {
		//this line say, that when we get the trajectory back run `onTrajectoryChange`
		TrajectoryManager.getInstance().registerListener(this);
		
		buildSpline();
		
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
