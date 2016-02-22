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
import com.team319.web.trajectory.server.TrajectoryServletSocket;

import edu.wpi.first.wpilibj.command.Command;

public class BuildPosition4Spline extends Command implements ITrajectoryChangeListener{
	
	private static final double BACK_OFF = 3.5;
	private boolean waitingForTrajectory = true;
	
	public BuildPosition4Spline() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
		//this line say, that when we get the trajectory back run `onTrajectoryChange`
		TrajectoryManager.getInstance().registerListener(this);
		
		List<Waypoint> waypoints = new ArrayList<Waypoint>();
		/*waypoints.add(new Waypoint(0,0,0));
		waypoints.add(new Waypoint(10,0,0));
		waypoints.add(new Waypoint(21.5,-9,-60*Math.PI/180));
		*/
		
		//testing
		waypoints.add(new Waypoint(0,0,0));
		waypoints.add(new Waypoint (5,0,0));
		waypoints.add(new Waypoint(10,-2,-60*Math.PI/180));
		//waypoints.add(new Waypoint(21.5,-9,-60*Math.PI/180));
	/*
		double angle = -60;
		double radians = (angle * Math.PI)/180;
		
		waypoints.add(new Waypoint(21,-9,radians));
	*/
		
		try {
	    	// this is the trajectory server url
			TrajectoryClient.start("10.3.19.20");//"10.3.19.20");//"169.254.189.192");//"10.3.19.20");
			WaypointManager.getInstance().setWaypointList(new WaypointList(waypoints),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public void onTrajectoryChange(CombinedSrxMotionProfile combined, TrajectoryServletSocket source) {
		Robot.driveTrain.setCurrentProfile(combined);
		waitingForTrajectory = false;
	}

}
