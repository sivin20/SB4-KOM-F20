import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.osgienemy.EnemyPlugin;
import dk.sdu.mmmi.cbse.osgienemy.EnemyProcessor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnemyTest {

    World mockWorld = mock(World.class);
    GameData mockGameData = mock(GameData.class);

    @Test
    public void testEnemyMovement(){
        Entity enemy = new Entity();
        mockWorld.addEntity(enemy);
        List<Entity> enemies = new ArrayList<>();
        enemies.add(enemy);

        when(mockWorld.getEntities(Enemy.class)).thenReturn(enemies);
        assertNotNull(mockWorld.getEntities(Enemy.class));

        EnemyProcessor processor = new EnemyProcessor();
        MovingPart movingPart = new MovingPart(1, 10, 100,10);
        PositionPart positionPart = new PositionPart(10, 10, 1);
        enemy.add(movingPart);
        enemy.add(positionPart);
        for(int i = 0; i < 10; i++) {
            processor.process(mockGameData, mockWorld);
        }
        assertNotEquals(10,positionPart.getX());
    }

    @Test
    public void testEnemyLaser(){
        //Entity enemy = mockEnemyPlugin.start();
    }
}
