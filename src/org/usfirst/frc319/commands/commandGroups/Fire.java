// Strong Hold BOB 2016 Commands (Collect and Load)

package org.usfirst.frc319.commands.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.commands.collector.CenterBoulder;
import org.usfirst.frc319.commands.collector.CollectAndStop;
import org.usfirst.frc319.commands.collector.CollectorIn;
import org.usfirst.frc319.commands.collector.CollectorInUntilBallIsGone;
import org.usfirst.frc319.commands.collector.LoadBoulder;
import org.usfirst.frc319.commands.shooter.*;
import org.usfirst.frc319.subsystems.*;

/**
 *
 */
public class Fire extends CommandGroup {

    public Fire() {
    	
    	addSequential(new WaitForShooterAtSpeed());
    	addSequential(new CollectorInUntilBallIsGone());
 
    } 
    
}
