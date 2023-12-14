/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package authentificaton;

/**
 *
 * @author LENOVO
 */
@FunctionalInterface
public interface Authentification {
    boolean authentifier(String nomUtilisateur, String motDePasse);
    
}
