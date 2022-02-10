package asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    private Random rn = new Random();
    private float[] dicts = new float[8];

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    public void updateShape(Entity entity) {
        entity.setShapeX(new float[8]);
        entity.setShapeY(new float[8]);
        entity.setRadius(12);
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        float angle = 0;
        for(int i = 0; i < 8; i++) {
            shapex[i] = (float) (x + Math.cos(radians + angle) * 12);
            shapey[i] = (float) (y + Math.sin(radians + angle) * 12 );
            angle += 2 * 3.1415f / 8;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
