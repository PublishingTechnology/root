package com.projectx;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/domcis")
@Controller
@RooWebScaffold(path = "domcis", formBackingObject = DomCI.class)
public class DomCIController {
}
