# Voxel

**Ultimate Plugin Developer Framework - Forge/Fabric for Paper Plugins**

Voxel is a comprehensive framework that brings Forge/Fabric-like development experience to Paper plugins. It provides registries, mechanics, packet manipulation, scripting, worldgen, and resourcepack pipelines, enabling developers to create any type of plugin without external dependencies.

## 🚀 Features

### Core Systems
- **Deferred Registries**: Forge-like registries for items, blocks, mobs, jobs, shops, dungeons, and more
- **Mechanic DSL v3**: Graph-based mechanics with scripting, branching, loops, and events
- **Built-in Packet API**: ProtocolLib-like functionality without external dependencies
- **Resource Pack Pipeline**: Automatic generation and merging of custom models
- **Scripting Engine**: Embedded Kotlin and JavaScript with sandboxing
- **Stats & Profiles**: Comprehensive player data management
- **Jobs & Teams**: Built-in progression and group systems
- **Shops & Economy**: Balanced trading systems
- **Dungeons & Worldgen**: Procedural generation tools

### Developer Experience
- **Hot Reload**: Live editing of mechanics and definitions
- **Editor Integration**: In-game editing and debugging tools
- **Performance Monitoring**: Built-in profiling and optimization
- **Comprehensive APIs**: Type-safe, well-documented interfaces
- **Module System**: Extensible architecture for custom functionality

## 📦 Installation

1. Download the Voxel Framework JAR
2. Place it in your server's `plugins` directory
3. Start your Paper 1.21.1+ server
4. The framework will initialize with developer mode enabled

## 🛠️ Quick Start

### Accessing the API

```java
// Get the Voxel API
VoxelAPI voxel = Bukkit.getServicesManager()
    .getRegistration(VoxelAPI.class)
    .getProvider();

// Access registries
VoxelRegistry<ItemDef> items = voxel.registry(ItemDef.class);
VoxelRegistry<MobDef> mobs = voxel.registry(MobDef.class);
```

### Registering Custom Items

```java
public class MyPlugin extends JavaPlugin {
    
    private static final VoxelRegistry<ItemDef> ITEMS = 
        VoxelAPI.registry(ItemDef.class);
    
    public static final RegistryObject<ItemDef> MAGIC_SWORD = 
        ITEMS.register("magic_sword", () -> 
            ItemDef.builder(new NamespacedKey("myplugin", "magic_sword"))
                .displayName("§6Magic Sword")
                .baseMaterial(Material.DIAMOND_SWORD)
                .customModelData(1001)
                .behavior(new MagicSwordBehavior())
                .build()
        );
}
```

### Creating Mechanics

Create `plugins/Voxel/mechanics/firebolt.yml`:

```yaml
id: myplugin:firebolt
version: 1.0.0
enabled: true
events: [onUse]

graph:
  - node: condition.has_stat
    args: { stat: intelligence, gte: 10 }
  - node: target.cone
    args: { angle: 30, range: 12 }
  - node: effect.damage
    args: { amount: '{caster:intelligence*0.6+10}', element: fire }
  - node: effect.particle
    args: { particle: 'flame', count: 20 }
```

### Using the Packet API

```java
// Send fake entities
PacketAPI packets = voxel.packets();
packets.sendFakeEntity(player, fakeEntity);

// Custom scoreboards
CustomScoreboard scoreboard = packets.createScoreboard("§6My Plugin");
scoreboard.setLine(1, "§7Level: §f" + level);
scoreboard.show(player);
```

## 📚 Documentation

- [Getting Started Guide](https://github.com/voxelune/voxel/wiki/Getting-Started)
- [API Reference](https://github.com/voxelune/voxel/wiki/API-Reference)
- [Mechanic DSL Guide](https://github.com/voxelune/voxel/wiki/Mechanics)
- [Packet API Guide](https://github.com/voxelune/voxel/wiki/Packets)
- [Registry System](https://github.com/voxelune/voxel/wiki/Registries)
- [Examples](https://github.com/voxelune/voxel/tree/main/examples)

## 🎯 Philosophy

- **Developer-First**: Built for plugin developers, not server admins
- **Dependency-Free**: Only requires Paper API - no external plugins
- **Forge-Like Experience**: Familiar patterns for modding veterans
- **Performance-Focused**: Async I/O, caching, and optimization built-in
- **Extensible**: Every system can be extended and customized

## 🔧 Commands

- `/voxel` - Framework information and status
- `/voxel reload` - Hot reload framework and definitions
- `/voxel devinfo` - Detailed developer information

## ⚙️ Configuration

The framework uses sane defaults with minimal configuration required:

```yaml
developer:
  mode: true              # Enable developer features
  hot_reload: true        # Hot reload mechanics
  debug_logging: true     # Verbose debug output
  module_loading: false   # External modules (security risk)

performance:
  async_io: true
  registry_cache_size: 10000
  mechanic_execution_timeout: 5000
```

## 🏗️ Architecture

Voxel is built with a modular architecture:

- **Registry System**: Deferred registration of all content types
- **Mechanic Engine**: Graph-based execution with hot reload
- **Packet Layer**: Low-level packet manipulation
- **Resource Pipeline**: Automatic asset generation
- **Scripting Runtime**: Sandboxed script execution
- **Data Layer**: Async persistence and caching

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

## 📄 License

MIT License - see [LICENSE](LICENSE) file for details.

## 🆘 Support

- [GitHub Issues](https://github.com/voxelune/voxel/issues)
- [Discord Server](https://discord.gg/voxelune)
- [Documentation Wiki](https://github.com/voxelune/voxel/wiki)

---

**Voxel Framework** - Bringing the power of Forge/Fabric to Paper plugins.
