package com.r2soft.freedoministriesinternational.packages.registry;

public class RegistryService implements RegistryPort {

    RegistryPort registryPort = null;

    public RegistryService(RegistryPort registryPort){
        this.registryPort = registryPort;
    }

    @Override
    public void setKey(String key, String value) {
        registryPort.setKey(key,value);
    }

    @Override
    public String getKey(String key) {
        return registryPort.getKey(key);
    }

    @Override
    public boolean hasKey(String key) {
        return registryPort.hasKey(key);
    }

}
