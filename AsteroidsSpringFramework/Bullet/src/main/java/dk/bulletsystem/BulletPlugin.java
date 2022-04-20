package dk.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

@Component
public class BulletPlugin implements IGamePluginService {

    public BulletPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity e: world.getEntities()) {
            if(e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
}
