package org.usfirst.frc319.commands.camera;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.subsystems.TowerCamera;

import com.team319.trajectory.CombinedSrxMotionProfile;
import com.team319.waypoint.WaypointManager;
import com.team319.trajectory.ITrajectoryChangeListener;
import com.team319.trajectory.TrajectoryManager;
import com.team319.waypoint.Waypoint;
import com.team319.web.trajectory.server.TrajectoryServletSocket;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraCalculateOffset extends Command implements ITargetListener{

	private static final double BACK_OFF = 3.5;
	private boolean waitingForTarget = true;

	public CameraCalculateOffset() {
		requires(Robot.towerCamera);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		TargetManager.getInstance().registerListener(this);

		//gets the distance and sends it to the Trajectory Server
		Robot.towerCamera.processFrame();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

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
		waitingForTarget = true;
	}

	@Override
	public void onTargetChange(Target target) {
		waitingForTarget = false;
	}

}
