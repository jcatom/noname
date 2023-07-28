package cc.jml1024.noname.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Evil
 */
@Controller
public class ControlPanelController {
    @GetMapping(value = {"/controlPanel"})
    public String controlPanel() {
        return "controlPanel";
    }
}
