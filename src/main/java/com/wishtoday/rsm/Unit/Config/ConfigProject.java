package com.wishtoday.rsm.Unit.Config;

import com.wishtoday.rsm.Config.ResConfig;

public class ConfigProject<E> {
    private E Project;
    public ConfigProject(E Project) {
        this.Project = Project;
    }
    public E get(){
      return this.Project;
    }
    public void set(E Project){
        this.Project = Project;
        ResConfig.saveConfigs();
    }
}
