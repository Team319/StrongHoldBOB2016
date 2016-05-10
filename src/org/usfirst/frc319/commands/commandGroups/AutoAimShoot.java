// Strong Hold BOB 2016 Commands (Collect and Load)

package org.usfirst.frc319.commands.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.commands.arm.ArmAutoAdjust;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromTape;
import org.usfirst.frc319.commands.camera.CameraGetTarget;
import org.usfirst.frc319.commands.camera.TuneCameraRotate;
import org.usfirst.frc319.commands.collector.CollectAndStop;
import org.usfirst.frc319.commands.collector.CollectorIn;
import org.usfirst.frc319.commands.collector.CollectorInUntilBallIsGone;
import org.usfirst.frc319.commands.collector.LoadBoulder;
import org.usfirst.frc319.commands.drivetrain.AutoAimRotate;
import org.usfirst.frc319.commands.drivetrain.TuneRotate;
import org.usfirst.frc319.commands.shooter.*;
import org.usfirst.frc319.subsystems.*;

/**
 *
 */
public class AutoAimShoot extends CommandGroup {

    public AutoAimShoot() {

    	addSequential(new LoadBoulder());
    	//addSequential(new ShooterSpeedUp());
    	addParallel(new ShooterSpeedUp());
    	addSequential(new CameraGetTarget());
    	addParallel(new ShooterSpeedUp());
    	//addParallel(new ArmAutoAdjust());
    	addSequential(new AutoAimRotate());
    	addSequential(new ArmGoToShootFromTape());
    	//addParallel(new ShooterSpeedUp());
    	addSequential(new CollectorInUntilBallIsGone());
    }

}
