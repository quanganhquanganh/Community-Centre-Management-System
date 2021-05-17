/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

/**
 *
 * @author macbookpro
 */
public class CurrentUserUsername {

    private static String currentUserUsrename;

    public String getCurrentUserUsrename() {
        return currentUserUsrename;
    }

    public void setCurrentUserUsrename(String currentUserUsrename) {
        this.currentUserUsrename = currentUserUsrename;
    }

}
