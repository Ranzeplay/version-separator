# Version separator

A Velocity plugin that separates players to different servers based on game version when connecting to the server.

## Configuration

The plugin uses TOML as the language of config file.

The key should be the protocol version, and the value should be the name of a registered server in `velocity.toml`.

### Example

```toml
[servers]
763 = "modded"
767 = "latest-surv"
```

This connects newly-joined player to `modded` server if using 1.20 or 1.21,
and connects to `latest-surv` server if using 1.21 or 1.21.1.
