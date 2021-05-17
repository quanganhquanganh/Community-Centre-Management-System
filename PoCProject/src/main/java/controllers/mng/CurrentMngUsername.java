/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

/**
 *
 * @author macbookpro
 */
public class CurrentMngUsername {
    String currentMngusername;

    public CurrentMngUsername(String currentMngusername) {
        this.currentMngusername = currentMngusername;
    }

    public CurrentMngUsername() {
        this.currentMngusername = "admin";
    }
    

    public String getCurrentMngusername() {
        return currentMngusername;
    }    

    public void setCurrentMngusername(String currentMngusername) {
        this.currentMngusername = currentMngusername;
    }
        
}
