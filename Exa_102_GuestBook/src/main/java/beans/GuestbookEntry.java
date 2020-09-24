/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author kainz
 */
public class GuestbookEntry implements Serializable{
    private String nickname;
    private String email;
    private String comment;

    public GuestbookEntry() {
    }

    public GuestbookEntry(String nickname, String email, String comment) {
        this.nickname = nickname;
        this.email = email;
        this.comment = comment;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "GuestbookEntry{" + "nickname=" + nickname + ", email=" + email + ", comment=" + comment + '}';
    }
    
    
}
