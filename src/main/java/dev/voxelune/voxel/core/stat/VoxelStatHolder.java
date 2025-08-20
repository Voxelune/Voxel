package dev.voxelune.voxel.core.stat;

import dev.voxelune.voxel.api.stat.*;
import org.bukkit.NamespacedKey;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation of StatHolder.
 */
public class VoxelStatHolder implements StatHolder {
    
    private final StatManager statManager;
    private final Map<NamespacedKey, Double> baseValues;
    private final Map<NamespacedKey, Map<NamespacedKey, StatModifier>> modifiers;

    public VoxelStatHolder(StatManager statManager) {
        this.statManager = statManager;
        this.baseValues = new ConcurrentHashMap<>();
        this.modifiers = new ConcurrentHashMap<>();
    }

    @Override
    public double getBaseValue(NamespacedKey statKey) {
        return baseValues.getOrDefault(statKey, getDefaultValue(statKey));
    }

    @Override
    public void setBaseValue(NamespacedKey statKey, double value) {
        baseValues.put(statKey, value);
    }

    @Override
    public double getFinalValue(NamespacedKey statKey) {
        double base = getBaseValue(statKey);
        Map<NamespacedKey, StatModifier> statModifiers = modifiers.get(statKey);
        
        if (statModifiers == null || statModifiers.isEmpty()) {
            return clampValue(statKey, base);
        }
        
        // Sort modifiers by priority
        List<StatModifier> sorted = statModifiers.values().stream()
            .sorted(Comparator.comparingInt(StatModifier::priority))
            .toList();
        
        double result = base;
        
        // Apply additive modifiers first
        for (StatModifier modifier : sorted) {
            if (modifier.type() == ModifierType.ADDITIVE) {
                result += modifier.value();
            }
        }
        
        // Then percentage modifiers (of base value)
        for (StatModifier modifier : sorted) {
            if (modifier.type() == ModifierType.PERCENTAGE) {
                result += base * (modifier.value() / 100.0);
            }
        }
        
        // Finally multiplicative modifiers
        for (StatModifier modifier : sorted) {
            if (modifier.type() == ModifierType.MULTIPLICATIVE) {
                result *= modifier.value();
            }
        }
        
        return clampValue(statKey, result);
    }

    @Override
    public void addModifier(NamespacedKey statKey, StatModifier modifier) {
        modifiers.computeIfAbsent(statKey, k -> new ConcurrentHashMap<>())
                .put(modifier.key(), modifier);
    }

    @Override
    public boolean removeModifier(NamespacedKey statKey, NamespacedKey modifierKey) {
        Map<NamespacedKey, StatModifier> statModifiers = modifiers.get(statKey);
        if (statModifiers != null) {
            return statModifiers.remove(modifierKey) != null;
        }
        return false;
    }

    @Override
    public Map<NamespacedKey, StatModifier> getModifiers(NamespacedKey statKey) {
        return Map.copyOf(modifiers.getOrDefault(statKey, Map.of()));
    }

    @Override
    public Map<NamespacedKey, Double> getAllStats() {
        Map<NamespacedKey, Double> result = new HashMap<>();
        
        // Include all stats with base values
        for (Map.Entry<NamespacedKey, Double> entry : baseValues.entrySet()) {
            result.put(entry.getKey(), getFinalValue(entry.getKey()));
        }
        
        // Include stats that only have modifiers
        for (NamespacedKey statKey : modifiers.keySet()) {
            if (!result.containsKey(statKey)) {
                result.put(statKey, getFinalValue(statKey));
            }
        }
        
        return result;
    }

    @Override
    public boolean hasStat(NamespacedKey statKey) {
        return baseValues.containsKey(statKey) || modifiers.containsKey(statKey);
    }

    @Override
    public Optional<StatModifier> getModifier(NamespacedKey statKey, NamespacedKey modifierKey) {
        Map<NamespacedKey, StatModifier> statModifiers = modifiers.get(statKey);
        return statModifiers != null ? 
            Optional.ofNullable(statModifiers.get(modifierKey)) : 
            Optional.empty();
    }

    private double getDefaultValue(NamespacedKey statKey) {
        return statManager.getStat(statKey).map(Stat::defaultValue).orElse(0.0);
    }

    private double clampValue(NamespacedKey statKey, double value) {
        Optional<Stat> stat = statManager.getStat(statKey);
        if (stat.isPresent()) {
            return Math.max(stat.get().minValue(), Math.min(stat.get().maxValue(), value));
        }
        return value;
    }
}