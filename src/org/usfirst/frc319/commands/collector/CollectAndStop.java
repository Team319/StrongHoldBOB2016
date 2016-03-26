// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.collector;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.BobController;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectAndStop extends Command {
	
	double collectorSpeed;
	double shooterSpeed;
	
	double collectHighSpeed;
	double collectLowSpeed;
	
	double shooterCollectHighSpeed;
	double shooterCollectLowSpeed;
	
	double collectThreshold;
	double speedThreshold;
	double centerThreshold;
	
	public CollectAndStop() {

		requires(Robot.collector);
		requires(Robot.shooter);

	}

	protected void initialize() {
		collectorSpeed = 0;
		shooterSpeed = 0;
		
		collectHighSpeed = Robot.constants.getConstant(BobConstants.COLLECTOR_HIGH_SPEED_KEY);
		collectLowSpeed = Robot.constants.getConstant(BobConstants.COLLECTOR_LOW_SPEED_KEY);
		
		shooterCollectHighSpeed = Robot.constants.getConstant(BobConstants.SHOOTER_COLLECT_HIGH_SPEED_KEY);
		shooterCollectLowSpeed = Robot.constants.getConstant(BobConstants.SHOOTER_COLLECT_LOW_SPEED_KEY);
		
		collectThreshold = Robot.constants.getConstant(BobConstants.COLLECTOR_COLLECT_LIMIT_KEY);
		speedThreshold = Robot.constants.getConstant(BobConstants.COLLECTOR_LOW_SPEED_THRESHOLD_KEY);
		centerThreshold = 2.0;
	}

	protected void execute() {		
		
		double rightIrValue = Robot.collector.getrightBoulderIrSensorAverageVoltage();
		double leftIrValue = Robot.collector.getleftBoulderIrSensorAverageVoltage();
		
		if (rightIrValue < speedThreshold && leftIrValue < speedThreshold) {
			collectorSpeed = collectHighSpeed;
			shooterSpeed = shooterCollectHighSpeed;
		} else {

			collectorSpeed = collectLowSpeed;
			shooterSpeed = shooterCollectLowSpeed;
		}

		if (rightIrValue < centerThreshold) {
			Robot.shooter.setLeftShooterSpeed(shooterSpeed);

		} else {
			Robot.shooter.setLeftShooterStop();
		}
		if (leftIrValue < centerThreshold) {
			Robot.shooter.setRightShooterSpeed(shooterSpeed);

		} else {
			Robot.shooter.setRightShooterStop();
		}
		Robot.collector.collectorGoIn(collectorSpeed);

	}

	protected boolean isFinished() {

		// highest distance the ball should go
		// return Robot.collector.loadIsFinished(2.5);
		// /return the isfinished from the IRcollector sensor pass it a smaller
		// value than in the
		return Robot.collector.bothIRSensorsCloseEnough(collectThreshold);
	}

	protected void end() {
		Robot.shooter.setRightShooterStop();
		Robot.shooter.setLeftShooterStop();
	}

	protected void interrupted() {
	}

}
