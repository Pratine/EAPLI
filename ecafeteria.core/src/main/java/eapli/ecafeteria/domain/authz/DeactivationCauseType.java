/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

/**
 *
 * @author Henrique Moura Costa
 */
public enum DeactivationCauseType {
        EXPIRED{
            public String toString(){
                return "Account expired.";
            }
        },
        BAD_CONDUCT{
            public String toString(){
                return "Bad conduct.";
            }
        }
}
