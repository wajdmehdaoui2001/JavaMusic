/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentificaton;

/**
 *
 * @author LENOVO
 */
public final class Musicien extends User implements Authentification {
private String username;
private String motDePasse;

    public Musicien(String username, String motDePasse, int id, String nom, String email) {
        super(id, nom, email);
        this.username = username;
        this.motDePasse = motDePasse;
    }

    @Override
    public boolean authentifier(String nomUtilisateur, String motDePasse) {
        return this.username.equals(nomUtilisateur) && this.motDePasse.equals(motDePasse); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}
