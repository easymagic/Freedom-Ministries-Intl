package com.r2soft.freedoministriesinternational.packages.registry;

public interface RegistryPort {

    void setKey(String key,String value);
    String getKey(String key);
    boolean hasKey(String key);

}
