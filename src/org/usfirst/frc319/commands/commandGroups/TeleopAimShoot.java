// Strong Hold BOB 2016 Commands (Collect and Load)

package org.usfirst.frc319.commands.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.commands.arm.ArmAutoAdjust;
import org.usfirst.frc319.commands.camera.CameraGetTarget;
import org.usfirst.frc319.commands.camera.TuneCameraRotate;
import org.usfirst.frc319.commands.collector.CollectAndStop;
import org.usfirst.frc319.commands.collector.CollectorIn;
import org.usfirst.frc319.commands.collector.CollectorInUntilBallIsGone;
import org.usfirst.frc319.commands.collector.LoadBoulder;
import org.usfirst.frc319.commands.drivetrain.AutoAimRotate;
import org.usfirst.frc319.commands.drivetrain.TeleopAimRotate;
import org.usfirst.frc319.commands.drivetrain.TuneRotate;
import org.usfirst.frc319.commands.shooter.*;
import org.usfirst.frc319.subsystems.*;

/**
 *
 */
public class TeleopAimShoot extends CommandGroup {

    public TeleopAimShoot() {

    	addSequential(new LoadBoulder());
    	//addSequential(new ShooterSpeedUp());
    	addParallel(new ShooterSpeedUp());
    	addSequential(new CameraGetTarget());
    	addParallel(new ShooterSpeedUp());
    	addSequential(new AimAtTarget());
    	//addParallel(new ShooterSpeedUp());
    	addSequential(new CollectorInUntilBallIsGone());
    }

}
