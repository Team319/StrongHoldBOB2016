// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.collector;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.BobController;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CenterBoulderAfterCollected extends Command {
	double rightIrValue = 0;
	double leftIrValue = 0;
	int loopCount = 0;
	boolean goingIn = false;
	boolean goingOut = false;

	public double turnAmount = 5;
	double leftShooterInitialPosition = 0;
	double rightShooterInitialPosition = 0;
	double leftDelta;
	double rightDelta;
	

	public CenterBoulderAfterCollected() {

		requires(Robot.collector);
		requires(Robot.shooter);

	}

	protected void initialize() {
		loopCount = 0;
		leftShooterInitialPosition= Robot.shooter.getLeftShooterPosition();
		rightShooterInitialPosition = Robot.shooter.getRightShooterPosition();
		
	}

	protected void execute() {
		//System.out.println("leftShooterInitialPosition" + leftShooterInitialPosition);
		//System.out.println("turnAMount = " +turnAmount);
		leftDelta = Robot.shooter.getLeftShooterPosition()-leftShooterInitialPosition;
		System.out.println("left Delta = " +leftDelta);
		rightDelta = Robot.shooter.getRightShooterPosition()-rightShooterInitialPosition;
		System.out.println("right Delta = " +rightDelta);
		
		
		
		if (-1*leftDelta < turnAmount && rightDelta< turnAmount){
			Robot.collector.collectorGoIn(.2);
			System.out.println("executing centering and not touching both wheels yet");
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
		return (-1*leftDelta > turnAmount && Robot.shooter.getRightShooterPosition()-rightShooterInitialPosition > turnAmount);
	}

	protected void end() {
		Robot.collector.collectorGoOut(0);
		// default command is CollectorStop
	}

	protected void interrupted() {
		Robot.collector.collectorGoOut(0);
	}

}
