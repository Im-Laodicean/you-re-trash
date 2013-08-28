package engine;

import java.util.List;

import entity.Entity;

public class Physics {
	public enum TrajectoryType{
		NONE, LINEAR, BALLISTIC;
	}
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
		case NONE:
		break;
		
		//Linear (i.e. unaffected by gravity)
		case LINEAR:
			if(e.getXVelocity()!=0)
				e.setX(e.getX()+(int)(e.getXVelocity()));
		if(e.getYVelocity()!=0)
			e.setY(e.getY()+(int)(e.getYVelocity()));
		break;
		
		//Ballistic trajectories
		case BALLISTIC :
			if(!e.getTrajectoryType().equals(TrajectoryType.BALLISTIC))
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

	public static void interpolate(List<Entity> currentEntities, List<Entity> previousEntities, double alpha) {
		for(int i = 0;i<currentEntities.size();i++){
			System.out.println("INTERPOLATE");
			Entity curEntity = currentEntities.get(i);
			Entity pastEntity = previousEntities.get(i);
			curEntity.setRenderX((int)(curEntity.getX()*alpha + pastEntity.getX()*(1.0-alpha)));
			curEntity.setRenderY((int)(curEntity.getY()*alpha + pastEntity.getY()*(1.0-alpha)));
		}
	}
}
