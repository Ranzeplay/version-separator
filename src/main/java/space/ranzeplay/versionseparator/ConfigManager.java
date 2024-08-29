package space.ranzeplay.versionseparator;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class ConfigManager {
    private final Path configDirectoryPath;
    private VersionServerListConfig config;
    private final Logger logger;

    public ConfigManager(Path configDirectoryPath, Logger logger) {
        this.configDirectoryPath = configDirectoryPath;
        this.config = new VersionServerListConfig(new HashMap<>());
        this.logger = logger;
    }

    private Path getConfigFilePath() {
        return configDirectoryPath.resolve("config.toml");
    }

    public Path getConfigDirectoryPath() {
        return configDirectoryPath;
    }

    public VersionServerListConfig getConfig() {
        return config;
    }

    public void initConfigIfNotExists() throws IOException {
        if (!Files.exists(configDirectoryPath)) {
            logger.info("Creating config directory since it is not exist");
            configDirectoryPath.toFile().mkdirs();
        }

        if (!Files.exists(getConfigFilePath())) {
            logger.info("Creating config file since it is not exist");
            Files.createFile(getConfigFilePath());
            var tomlWriter = new TomlWriter();
            config.servers.put(0, "default");
            tomlWriter.write(config, getConfigFilePath().toFile());
        }
    }

    public void loadConfig() {
        logger.info("Loading config");
        var toml = new Toml().read(getConfigFilePath().toFile());
        this.config = toml.to(VersionServerListConfig.class);
    }
}
