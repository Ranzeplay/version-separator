package space.ranzeplay.versionseparator;

import java.util.HashMap;

public final class VersionServerListConfig {
    HashMap<Integer, String> servers;

    public VersionServerListConfig(HashMap<Integer, String> servers) {
        this.servers = servers;
    }

    public VersionServerListConfig() {
    }

    public HashMap<Integer, String> getServers() {
        return servers;
    }
}
