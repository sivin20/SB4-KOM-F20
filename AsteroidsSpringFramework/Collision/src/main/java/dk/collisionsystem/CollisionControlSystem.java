package dk.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.springframework.stereotype.Component;

@Component
public class CollisionControlSystem implements IPostEntityProcessingService {

    public CollisionControlSystem() {

    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);

                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(collisionDetection.getID())) {
                    continue;

                    // remove entities with zero in expiration
                }

                // CollisionDetection
                if (this.checkCollision(entity, collisionDetection)) {
                    // if entity has been hit, and should have its life reduced
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    private boolean checkCollision(Entity entity, Entity collisionDetection) {

        float circle1r = entity.getRadius();
        PositionPart circle1pos = entity.getPart(PositionPart.class);
        float circle1x = circle1pos.getX();
        float circle1y = circle1pos.getY();


        float circle2r = collisionDetection.getRadius();
        PositionPart circle2pos = collisionDetection.getPart(PositionPart.class);
        float circle2x = circle2pos.getX();
        float circle2y = circle2pos.getY();


        float dx = circle1x - circle2x;
        float dy = circle1y - circle2y;
        float distance = (float) Math.sqrt(dx*dx + dy*dy);

        if(distance < circle1r + circle2r) {
            //System.out.println("Player R: " + circle1r + " x: " + circle1x + " y: " + circle1y);
            //System.out.println("Asteroid R: " + circle2r + " x: " + circle2x + " y: " + circle2y);
            return true;
        } else {
            return false;
        }
    }
}
