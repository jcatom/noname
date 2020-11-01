package cc.jml1024.subdragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlPanelController {
    @GetMapping(value = {"/controlPanel"})
    public String controlPanel() {
        return "controlPanel";
    }
}
