// Strong Hold BOB 2016 Commands (Collector In)

package org.usfirst.frc319.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CollectorInUntilBallIsGone extends Command {

	long startTime = 0;

	public CollectorInUntilBallIsGone() {

		requires(Robot.collector);

	}

	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	protected void execute() {
		double speed = 1;

		if (Robot.shooter.getRightShooterSpeed() < 100) {
			Robot.collector.collectorGoIn(0);
		}else{
			Robot.collector.collectorGoIn(speed);
		}
	}

	protected boolean isFinished() {
		if (System.currentTimeMillis() - startTime > 1000) {
			// we've been shooting for over two seconds
			return true;
		} else {
			return false;
			// implement bellow when bouldersensor is working
			// Robot.collector.ballIsGone(20);
		}
	}

	protected void end() {
		Robot.collector.collectorGoIn(0);
	}

	protected void interrupted() {
	}

}
