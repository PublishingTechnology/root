package com.projectx;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class DomCI {

    /**
     */
    @NotNull
    @Value("CI_Name")
    @Size(min = 6)
    private String Name;

    /**
     */
    @NotNull
    @Column(unique = true)
    private String id;

    /**
     */
    @NotNull
    private String className;

    /**
     */
    private String requestedViews;

    /**
     */
    private String version;

    /**
     */
    
    
    
    @ElementCollection
    //@MapKeyColumn(name="keyName")
    //@CollectionTable(schema="jpa",name="view_name_map",joinColumns=@JoinColumn(name="viewName"))
    private Map<String,ViewCI> viewCIs = new HashMap<String,ViewCI>();
    //private Map<String,String> viewCIs = new HashMap<String,String>();
}
