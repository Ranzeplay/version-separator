package space.ranzeplay.versionseparator.events;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent;
import space.ranzeplay.versionseparator.Main;

public class ServerEvents {
    @Subscribe(order = PostOrder.EARLY)
    public void onServerPreConnect(PlayerChooseInitialServerEvent event) {
        final var protocol = event.getPlayer().getProtocolVersion().getProtocol();
        final var targetServerName = Main.INSTANCE.getConfigManager().getConfig().getServers().get(protocol);

        var targetServer = Main.INSTANCE.getServer()
                .getAllServers()
                .stream()
                .filter(s -> s.getServerInfo().getName().equals(targetServerName))
                .findFirst();

        targetServer.ifPresent(event::setInitialServer);
    }
}
