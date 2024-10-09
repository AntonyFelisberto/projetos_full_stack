/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package METODOS;

/**
 *
 * @author Antony
 */
public enum Drive {
    VIDEODRIVE("<iframe src='%s' allow='autoplay' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen frameborder='0' scrolling='no' seamless=''></iframe>");

    String url;
    Drive(String url){
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
