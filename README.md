# Voxel Core Library

A standalone core library plugin for Paper 1.21.1 that provides unified mechanics, registries, and a capability graph for custom plugins. Voxel serves as the foundation for all custom plugins authored by Voxelune, requiring no external plugin dependencies.

## Features

### Core Modules
- **voxel-core**: Bootstrap, configuration, service wiring, and event wrapper
- **voxel-registry**: Typed registries for Skills, Items, Mobs, Shops, Dungeons, Worldgen, Models, and Stats
- **voxel-mechanic-dsl**: YAML/JSON-based mechanic definitions that compile to executable node graphs
- **voxel-capabilities**: Capability graph system for plugin feature registration without external dependencies
- **voxel-model-router**: Model, animation, sound, and particle mapping with automatic fallbacks
- **voxel-utils**: General utilities including NBT, math, scheduler, i18n, and caching

### Unique Features
- **Capability Graph**: Runtime capability system where plugins register features and interact via typed contracts
- **Mechanic DSL**: Compact domain-specific language for declaring mechanics that compile to node graphs with hot-reload support
- **Model Router**: Maps Java entities/items to resource pack IDs with automatic fallbacks for unsupported clients

## Installation

1. Download the Voxel plugin JAR file
2. Place it in your server's `plugins` directory
3. Start your Paper 1.21.1 server
4. The plugin will create its configuration and mechanics directories automatically

## API Usage

Access the Voxel API through Bukkit's Services Manager:

```java
VoxelAPI api = Bukkit.getServicesManager().getRegistration(VoxelAPI.class).getProvider();

// Access sub-APIs
RegistryAPI registries = api.getRegistryAPI();
MechanicAPI mechanics = api.getMechanicAPI();
CapabilityAPI capabilities = api.getCapabilityAPI();
ModelAPI models = api.getModelAPI();
StatAPI stats = api.getStatAPI();
```

## Mechanic DSL

Create mechanics using YAML files in the `plugins/Voxel/mechanics/` directory:

```yaml
id: voxel:firebolt
version: 1.0.0
enabled: true
description: "Fires a bolt of flame that damages enemies in a cone"

graph:
  - node: condition.has_stat
    args: { stat: 'intelligence', gte: 10 }
  - node: target.cone
    args: { angle: 30, range: 12 }
  - node: effect.damage
    args: { amount: '{caster:intelligence*0.6+10}', element: 'fire' }
  - node: effect.particle
    args: { particle: 'voxel:firebolt_trail' }

metadata:
  category: 'combat'
  tags: ['fire', 'projectile', 'damage']
  cost_mana: 25
  cast_time: 1.5
```

## Commands

- `/voxel` - Display plugin information and status
- `/voxel reload` - Reload configuration and mechanics (requires `voxel.admin.reload`)
- `/voxel cap list` - List registered capabilities (requires `voxel.admin.inspect`)
- `/voxel reg list <registry>` - List registry entries (requires `voxel.admin.inspect`)
- `/voxel test run <mechanicId>` - Test a mechanic execution (requires `voxel.dev.test`)

## Configuration

The main configuration file (`config.yml`) allows you to:
- Set logging levels
- Enable/disable modules
- Configure hot-reload for mechanics
- Adjust registry settings
- Customize capability behavior
- Configure model router settings

## Dependencies

Voxel is completely self-contained and depends only on:
- **Paper API 1.21.1** (provided by your server)

No other plugins are required - Voxel is designed to be the root foundation for your plugin ecosystem.

## Development

For plugin developers building on Voxel:

1. Add Voxel as a dependency in your `plugin.yml`
2. Access the API through the Services Manager
3. Register your capabilities, mechanics, models, and stats
4. Use the unified registry system for consistent data management

## License

MIT License - see the `LICENSE` file for details.

## Support

Voxel is developed by Voxelune. For support and documentation, visit our development resources.