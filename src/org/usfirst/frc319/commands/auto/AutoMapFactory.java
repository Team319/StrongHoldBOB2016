package org.usfirst.frc319.commands.auto;



import java.util.HashMap;
import java.util.Map;

import com.team319.auto.SelectedAuto;

import edu.wpi.first.wpilibj.command.Command;

public class AutoMapFactory {
	private Map<SelectedAuto, Command> map;

	private static AutoMapFactory instance;

	public static AutoMapFactory getInstance(){
		if(instance == null){
			instance = new AutoMapFactory();
		}
		return instance;
	}

	private AutoMapFactory(){
		map = new HashMap<SelectedAuto, Command>();
	}
	
	/**
	public Map<SelectedAuto, Command> buildMap(){

		map = new HashMap<SelectedAuto, Command>();


		//define position 1 autos
		//addAuto(AutoDictionary.POS_POSITION_1, AutoDictionary.DEF_LOW_BAR, AutoDictionary.MODE_DRIVE_AND_SHOOT_LOW, new LowBarLowGoalAuto());
		//if we're in position one going through the low bar and driving/shooting high run LowBarHighGoalAuto
		addAuto(AutoDictionary.POS_POSITION_1, AutoDictionary.DEF_LOW_BAR, AutoDictionary.MODE_DRIVE_AND_SHOOT_HIGH, new LowBarHighGoalAuto());


		for(String position: AutoDictionary.getPositions()){
			if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_1)){
				//skip position 1
				continue;
			}

			//any position other than one (2-5)
			for(String defense: AutoDictionary.getDefenses()){
				if(defense.equalsIgnoreCase(AutoDictionary.DEF_LOW_BAR)){
					//skip low bar
					continue;
				}else if(AutoDictionary.getStaticDefenses().indexOf(defense) == -1){
					//skip non static defenses
					continue;
				}
				
				addAuto(position, AutoDictionary.MODE_DRIVE_AND_SHOOT_HIGH, defense, new AutoDriveStraightThenSplineThenShoot(defense, position));
				//addAuto(position, AutoDictionary.MODE_DRIVE_STRAIGHT, defense, new AutoDriveStraightDistance(defense));

			}


		}

		return map;

	}
	**/
	
	public Command buildAuto(SelectedAuto auto){
		if(auto.getSelectedPosition().equalsIgnoreCase(AutoDictionary.POS_POSITION_1)){
			return new LowBarHighGoalAuto();
		}else{
			if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.MODE_DRIVE_AND_SHOOT_HIGH)){
				return new AutoDriveStraightThenSplineThenShoot(auto.getSelectedDefense(), auto.getSelectedPosition());
			}else if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.MODE_DRIVE_STRAIGHT)){
				return new AutoDriveStraightThenSplineThenShoot(auto.getSelectedDefense(), auto.getSelectedPosition());
			}
		}
		return null;
	}

	/**
	private void addAuto(String position, String defense, String mode, Command auto){
		this.map.put(new SelectedAuto(position, defense, mode), auto);
	}

	public Map<SelectedAuto, Command> getMap() {
		return map;
	}

	public boolean hasAuto(SelectedAuto auto){
		return map.containsKey(auto);
	}

	public Command getAuto(SelectedAuto auto){
		return map.get(auto);
	}
	**/
}
