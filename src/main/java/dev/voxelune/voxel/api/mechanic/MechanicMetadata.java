package dev.voxelune.voxel.api.mechanic;

import java.util.List;
import java.util.Map;

/**
 * Metadata associated with a mechanic.
 */
public record MechanicMetadata(
    String category,
    List<String> tags,
    int costMana,
    double castTime,
    Map<String, Object> additionalData
) {
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String category = "general";
        private List<String> tags = List.of();
        private int costMana = 0;
        private double castTime = 0.0;
        private Map<String, Object> additionalData = Map.of();
        
        public Builder category(String category) { this.category = category; return this; }
        public Builder tags(List<String> tags) { this.tags = List.copyOf(tags); return this; }
        public Builder costMana(int costMana) { this.costMana = costMana; return this; }
        public Builder castTime(double castTime) { this.castTime = castTime; return this; }
        public Builder additionalData(Map<String, Object> data) { this.additionalData = Map.copyOf(data); return this; }
        
        public MechanicMetadata build() {
            return new MechanicMetadata(category, tags, costMana, castTime, additionalData);
        }
    }
}