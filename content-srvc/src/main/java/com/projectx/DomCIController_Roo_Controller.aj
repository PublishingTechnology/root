// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.projectx;

import com.projectx.DomCI;
import com.projectx.DomCIController;
import com.projectx.ViewCI;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect DomCIController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String DomCIController.create(@Valid DomCI domCI, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, domCI);
            return "domcis/create";
        }
        uiModel.asMap().clear();
        domCI.persist();
        return "redirect:/domcis/" + encodeUrlPathSegment(domCI.getId_().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String DomCIController.createForm(Model uiModel) {
        populateEditForm(uiModel, new DomCI());
        return "domcis/create";
    }
    
    @RequestMapping(value = "/{id_}", produces = "text/html")
    public String DomCIController.show(@PathVariable("id_") Long id_, Model uiModel) {
        uiModel.addAttribute("domci", DomCI.findDomCI(id_));
        uiModel.addAttribute("itemId", id_);
        return "domcis/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String DomCIController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("domcis", DomCI.findDomCIEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) DomCI.countDomCIs() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("domcis", DomCI.findAllDomCIs(sortFieldName, sortOrder));
        }
        return "domcis/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String DomCIController.update(@Valid DomCI domCI, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, domCI);
            return "domcis/update";
        }
        uiModel.asMap().clear();
        domCI.merge();
        return "redirect:/domcis/" + encodeUrlPathSegment(domCI.getId_().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id_}", params = "form", produces = "text/html")
    public String DomCIController.updateForm(@PathVariable("id_") Long id_, Model uiModel) {
        populateEditForm(uiModel, DomCI.findDomCI(id_));
        return "domcis/update";
    }
    
    @RequestMapping(value = "/{id_}", method = RequestMethod.DELETE, produces = "text/html")
    public String DomCIController.delete(@PathVariable("id_") Long id_, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        DomCI domCI = DomCI.findDomCI(id_);
        domCI.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/domcis";
    }
    
    void DomCIController.populateEditForm(Model uiModel, DomCI domCI) {
        uiModel.addAttribute("domCI", domCI);
        uiModel.addAttribute("viewcis", ViewCI.findAllViewCIs());
    }
    
    String DomCIController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
