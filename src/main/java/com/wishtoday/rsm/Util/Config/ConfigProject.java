package com.wishtoday.rsm.Util.Config;

import com.wishtoday.rsm.Config.ResConfig;

public class ConfigProject<E> {
    private E Project;
    private ConfigProject(E Project) {
        this.Project = Project;
    }
    public E get(){
      return this.Project;
    }
    public void set(E Project){
        this.Project = Project;
        ResConfig.saveConfigs();
    }
    public static <E> ConfigProject<E> of(E Project) {
       return new ConfigProject<>(Project);
    }
}
