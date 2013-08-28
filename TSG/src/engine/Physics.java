package engine;

import java.util.List;

import entity.Entity;

public class Physics {
	public static final int NONE = 0, LINEAR = 1, BALLISTIC = 2;
	public static double GRAVITY;

	public static void init(double accelerationOfGravity){
		GRAVITY = accelerationOfGravity;
	}

	public static void updatePhysics(List<Entity>entities){
		for(Entity e : entities){
			calculateNextPosition(e);
		}
	}

	private static void calculateNextPosition(Entity e){
		switch(e.getTrajectoryType()){
		
		//stationary objects don't move
		case(NONE):
		break;
		
		//Linear (i.e. unaffected by gravity)
		case(LINEAR):
			if(e.getXVelocity()!=0)
				e.setX(e.getX()+(int)(e.getXVelocity()));
		if(e.getYVelocity()!=0)
			e.setY(e.getY()+(int)(e.getYVelocity()));
		break;
		
		//Ballistic trajectories
		case(BALLISTIC):
			if(e.getTrajectoryType()!=BALLISTIC)
				throw new IllegalArgumentException("Entity is not in ballistic motion");
		//horizontal motion unaffected
			e.setX(e.getX()+(int)(e.getXVelocity()));

			e.setY(e.getY()-(int)(e.getYVelocity()));
			//apply acceleration due to gravity
			e.setYVelocity(e.getYVelocity()-GRAVITY);
		break;
		default:
			break;
		}
	}
}
