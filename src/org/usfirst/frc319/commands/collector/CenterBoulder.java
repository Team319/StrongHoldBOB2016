// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.collector;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.BobController;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CenterBoulder extends Command {
	double rightIrValue = 0;
	double leftIrValue = 0;
	int loopCount = 0;
	boolean goingIn = false;
	boolean goingOut = false;

	public CenterBoulder() {

		requires(Robot.collector);
		requires(Robot.shooter);

	}

	protected void initialize() {
		loopCount = 0;

	}

	protected void execute() {

		rightIrValue = Robot.collector.getrightBoulderIrSensorVoltage();
		leftIrValue = Robot.collector.getleftBoulderIrSensorVoltage();

		if (rightIrValue < 2.2 || leftIrValue < 2.2) {
			Robot.collector.collectorGoIn(.3);
		}
		/*
		 * if ((rightIrValue < 1.9 || leftIrValue < 1.9) && loopCount < 5) {
		 * goingIn = true; goingOut = false; Robot.collector.collectorGoIn(.3);
		 * }else if(goingIn==true){ loopCount++; } if ((rightIrValue >= 1.75 ||
		 * leftIrValue >= 1.75) && loopCount < 5) { goingIn = false; goingOut =
		 * true; Robot.collector.collectorGoOut(.3);
		 * 
		 * } else if(goingOut==true){ loopCount++; }
		 */
	}

	protected boolean isFinished() {

		// highest distance the ball should go
		// return Robot.collector.loadIsFinished(2.5);
		// /return the isfinished from the IRcollector sensor pass it a smaller
		// value than in the
		return (rightIrValue >= 2.2 || leftIrValue > 2.2);
	}

	protected void end() {
		Robot.collector.collectorGoOut(0);
		// default command is CollectorStop
	}

	protected void interrupted() {
		Robot.collector.collectorGoOut(0);
	}

}
