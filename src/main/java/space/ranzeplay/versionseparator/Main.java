package space.ranzeplay.versionseparator;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;
import space.ranzeplay.versionseparator.events.ServerEvents;

import java.io.IOException;
import java.nio.file.Path;

import static space.ranzeplay.versionseparator.BuildConstants.VERSION;

@Plugin(id = "version-separator", name = "VersionSeparator", authors = "Play_D", version = VERSION)
public class Main {
    public static Main INSTANCE;
    private final ProxyServer server;
    private final Logger logger;
    private final ConfigManager configManager;

    @Inject
    public Main(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.configManager = new ConfigManager(dataDirectory, logger);
        INSTANCE = this;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) throws IOException {
        logger.info("Registering events");
        server.getEventManager().register(this, new ServerEvents());

        configManager.initConfigIfNotExists();
        configManager.loadConfig();
    }

    public Logger getLogger() {
        return logger;
    }

    public ProxyServer getServer() {
        return server;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
