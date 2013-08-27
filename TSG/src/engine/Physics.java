package engine;

import java.util.List;

import entity.Entity;

public class Physics {
	public static final int NONE = 0, LINEAR = 1, BALLISTIC = 2;
	public static double GRAVITY;

	public static void init(double accelerationOfGravity){
		GRAVITY = accelerationOfGravity;
	}

	public static void updatePhysics(List<Entity>entities, double delta){
		for(Entity e : entities){
			calculateNextPosition(e, delta);
		}
	}

	private static void calculateNextPosition(Entity e, double delta){
		switch(e.getTrajectoryType()){
		
		//stationary objects don't move
		case(NONE):
		break;
		
		//Linear (i.e. unaffected by gravity)
		case(LINEAR):
			if(e.getXVelocity()!=0)
				e.setX(e.getX()+(int)(e.getXVelocity()*delta));
		if(e.getYVelocity()!=0)
			e.setY(e.getY()+(int)(e.getYVelocity()*delta));
		break;
		
		//Ballistic trajectories
		case(BALLISTIC):
			if(e.getTrajectoryType()!=BALLISTIC)
				throw new IllegalArgumentException("Entity is not in ballistic motion");
		//horizontal motion unaffected
			e.setX(e.getX()+(int)(e.getXVelocity()*delta));

			e.setY(e.getY()-(int)(e.getYVelocity()*delta));
			//apply acceleration due to gravity
			e.setYVelocity(e.getYVelocity()-GRAVITY*delta);
		break;
		default:
			break;
		}
	}
}
