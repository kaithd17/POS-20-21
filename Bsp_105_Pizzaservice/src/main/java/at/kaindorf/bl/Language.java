/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

/**
 *
 * @author kainz
 */
public enum Language {
    DE("DE","Deutsch"), EN("EN", "English");
    
    private String languageCode;
    private String languageName;

    private Language(String languageCode, String languageName) {
        this.languageCode = languageCode;
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
    
    
}
