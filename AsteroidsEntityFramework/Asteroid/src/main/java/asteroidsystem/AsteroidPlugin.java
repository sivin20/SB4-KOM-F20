package asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private Random rn = new Random();
    private Entity asteroid;

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createEnemyShip(gameData);
        world.addEntity(asteroid);
    }

    public Entity createEnemyShip(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 100;
        float maxSpeed = 100;
        float rotationSpeed = 10;
        float x = (gameData.getDisplayWidth() + 10);
        float y = rn.nextFloat() * (gameData.getDisplayWidth()) + 10;
        float radians = rn.nextFloat() * (3.1415f - 3.1415f*4);

        Entity asteroid = new Asteroid();
        asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }
}
