package com.projectx;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class ViewCI {

    /**
     */
    @NotNull
    @Size(min = 4)
    private String viewName;

}
