package dev.voxelune.voxel.api.capability;

import dev.voxelune.voxel.api.internal.capability.InternalCapabilityProvider;
import dev.voxelune.voxel.api.internal.utils.InternalReflector;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.Optional;

@SuppressWarnings("unchecked")
public interface CapabilityProvider<T> extends Capability {

    @NotNull
    Optional<T> find(@NotNull Object instance);

    @NotNull
    T get(@NotNull Object instance);

    @NotNull
    T put(@NotNull Object instance, @NotNull T value);

    @NotNull
    T remove(@NotNull Object instance);

    static <T> @NotNull CapabilityProvider<T> newInstance(@NotNull Class<T> capability) {
        Constructor<InternalCapabilityProvider> constructor = InternalReflector.getCapabilityProviderConstructor();
        try {
            return constructor.newInstance(capability);
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred while creating a new instance of CapabilityProvider", e);
        }
    }
}
