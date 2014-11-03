/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.HashMap;

/**
 *
 * @author Orlando
 */

public class CurrentConfig {
    public HashMap avroraZCommands;
    
    public CurrentConfig(){
        avroraZCommands = new HashMap();
        avroraZCommands.put("action", new Triplet(false, "some grong", null));
        avroraZCommands.put("banner", new Triplet(false, true, null));
        avroraZCommands.put("colors", new Triplet(false, true, null));
        avroraZCommands.put("html", new Triplet(false, true, null));
        String[] inputs = {"atmel", "auto", "gas", "objdump", "odpp"};
        avroraZCommands.put("input", new Triplet(true, 4, inputs));
        avroraZCommands.put("license", new Triplet(false, true, null));
        avroraZCommands.put("status", new Triplet(false, true, null));
        avroraZCommands.put("verbose", new Triplet(false, "", null));
        avroraZCommands.put("configFile", new Triplet(true, "src/configurations/default.txt", null));
        avroraZCommands.put("extra", new Triplet(true, "> output.txt", null));
    }


    
}
