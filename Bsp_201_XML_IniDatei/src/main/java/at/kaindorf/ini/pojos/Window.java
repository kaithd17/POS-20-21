/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.ini.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author kainz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Window {
    private String name;
    private int xpos;
    private int ypos;
    private int width;
    private int height;
}
