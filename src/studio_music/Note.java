/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package studio_music;

/**
 *
 * @author LENOVO
 */
import javafx.scene.input.KeyCode;

/**
 *
 * @author LENOVO
 */
public record Note(String name, KeyCode key, int number) {

    public String getName() {
        return name;
    }

    public KeyCode getKey() {
        return key;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Note{" + "name=" + name + ", key=" + key + ", number=" + number + '}';
    }
}
