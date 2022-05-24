package dk.sdu.mmmi.Player;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{

    BulletSPI bulletSPI;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        PlayerControlSystem pcs = new PlayerControlSystem();
        PlayerPlugin pp = new PlayerPlugin();
        bundleContext.registerService(IEntityProcessingService.class, pcs, null);
        bundleContext.registerService(IGamePluginService.class, pp, null);
        //TODO: bulletSPI = bundleContext.getService(????)
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
