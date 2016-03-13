// Strong Hold BOB 2016 Commands (Collect and Load)

package org.usfirst.frc319.commands.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.commands.collector.CenterBoulder;
import org.usfirst.frc319.commands.collector.CollectAndStop;
import org.usfirst.frc319.commands.collector.CollectorTimedPause;
import org.usfirst.frc319.commands.collector.LoadBoulder;
import org.usfirst.frc319.commands.collector.*;
import org.usfirst.frc319.subsystems.*;

/**
 *
 */
public class CenterBoulderRoutine extends CommandGroup {

    public CenterBoulderRoutine() {
    	
    	
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulder());
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulder());
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulder());
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulder());
    	addSequential(new LoadBoulder());
    	addSequential(new CollectAndStop());
    } 
    
}
