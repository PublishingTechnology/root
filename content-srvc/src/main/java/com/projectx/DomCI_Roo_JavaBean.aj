// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.projectx;

import com.projectx.DomCI;
import com.projectx.ViewCI;
import java.util.Map;

privileged aspect DomCI_Roo_JavaBean {
    
    public String DomCI.getName() {
        return this.Name;
    }
    
    public void DomCI.setName(String Name) {
        this.Name = Name;
    }
    
    public String DomCI.getId() {
        return this.id;
    }
    
    public void DomCI.setId(String id) {
        this.id = id;
    }
    
    public String DomCI.getClassName() {
        return this.className;
    }
    
    public void DomCI.setClassName(String className) {
        this.className = className;
    }
    
    public String DomCI.getRequestedViews() {
        return this.requestedViews;
    }
    
    public void DomCI.setRequestedViews(String requestedViews) {
        this.requestedViews = requestedViews;
    }
    
    public String DomCI.getVersion() {
        return this.version;
    }
    
    public void DomCI.setVersion(String version) {
        this.version = version;
    }
    
    public Map<String, ViewCI> DomCI.getViewCIs() {
        return this.viewCIs;
    }
    
    public void DomCI.setViewCIs(Map<String, ViewCI> viewCIs) {
        this.viewCIs = viewCIs;
    }
    
}
