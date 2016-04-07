package org.usfirst.frc319.commands.auto;



import java.util.Arrays;
import java.util.List;

public class AutoDictionary {
	
	
	public static final String AUTO_POSITION_1 = "auto Low Bar";
	public static final String AUTO_POSITION_2 = "auto position 2";
	public static final String AUTO_POSITION_3 = "auto position 3";
	public static final String AUTO_POSITION_4 = "auto position 4";
	public static final String AUTO_POSITION_5 = "auto position 5";
	public static final String AUTO_DO_NOTHING = "auto do nothing";
	
	@Deprecated
	public static final String POS_POSITION_1 = "Position 1";
	@Deprecated
	public static final String POS_POSITION_2 = "Position 2";
	@Deprecated
	public static final String POS_POSITION_3 = "Position 3";
	@Deprecated
	public static final String POS_POSITION_4 = "Position 4";
	@Deprecated
	public static final String POS_POSITION_5 = "Position 5";

	public static final String MODE_DRIVE_STRAIGHT = "Drive Straight";
	//public static final String MODE_DRIVE_TO_TOWER = "Drive to Tower";
	public static final String MODE_DRIVE_AND_SHOOT_HIGH = "Drive and Shoot High";
	//public static final String MODE_DRIVE_AND_SHOOT_LOW = "Drive and Shoot Low";
	//public static final String MODE_DRIVE_TURN_AND_SHOOT_HIGH = "Drive Turn and Shoot High";

	public static final String DEF_LOW_BAR = "Low Bar";
	public static final String DEF_PORTCULLIS = "Portcullis";
	public static final String DEF_CHEVAL = "Cheval de Frise";
	public static final String DEF_MOAT = "Moat";
	public static final String DEF_RAMPARTS = "Ramparts";
	public static final String DEF_DRAWBRIDGE = "Drawbridge";
	public static final String DEF_SALLYPORT = "Sallyport";
	public static final String DEF_TERRAIN = "Rough Terrain";
	public static final String DEF_WALL = "Rock Wall";

	public static final boolean SHOOT_HIGH = true;
	public static final boolean SHOOT_LOW = false;

	public static List<String> getPositions(){
		return Arrays.asList(POS_POSITION_1, POS_POSITION_2, POS_POSITION_3, POS_POSITION_4, POS_POSITION_5);
	}

	public static List<String> getModes(){
		return Arrays.asList(AUTO_POSITION_1,AUTO_POSITION_2,AUTO_POSITION_3,AUTO_POSITION_4,AUTO_POSITION_5, AUTO_DO_NOTHING);
	}

	public static List<String> getDefenses(){
		return Arrays.asList(DEF_LOW_BAR, DEF_PORTCULLIS, DEF_CHEVAL, DEF_MOAT, DEF_RAMPARTS, DEF_DRAWBRIDGE, DEF_SALLYPORT, DEF_TERRAIN, DEF_WALL);
	}

	public static List<String> getStaticDefenses(){
		return Arrays.asList(DEF_MOAT, DEF_RAMPARTS, DEF_TERRAIN, DEF_WALL);
	}
}
