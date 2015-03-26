package com.projectx;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.json.RooJson;

@RequestMapping("/viewcis")
@Controller
@RooWebScaffold(path = "viewcis", formBackingObject = ViewCI.class)
@RooWebJson(jsonObject=ViewCI.class)
public class ViewCIController {
}
