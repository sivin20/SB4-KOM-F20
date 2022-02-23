package collisionsystem;

import asteroidsystem.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollisionControlSystem implements IPostEntityProcessingService {

    List<Entity> asteroidList = new ArrayList<>();
    Map<String, Entity> entityMap = new HashMap<>();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            entityMap.put("player", player);
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            entityMap.put("asteroid", asteroid);
            if(checkCollision(entityMap)){
                System.out.println("Collision detected");
                //world.removeEntity("ASTEROID ID");
            }
        }


    }

    private boolean checkCollision(Map<String, Entity> entities) {
        Entity player = entities.get("player");
        Entity asteroid = entities.get("asteroid");

        float circle1r = player.getRadius();
        PositionPart circle1pos = player.getPart(PositionPart.class);
        float circle1x = circle1pos.getX();
        float circle1y = circle1pos.getY();


        float circle2r = asteroid.getRadius();
        PositionPart circle2pos = asteroid.getPart(PositionPart.class);
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
