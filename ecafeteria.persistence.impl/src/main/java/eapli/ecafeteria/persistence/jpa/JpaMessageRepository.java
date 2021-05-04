/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.Message;
import eapli.ecafeteria.persistence.MessageRepository;

/**
 *
 * @author Utilizador
 */
public class JpaMessageRepository extends CafeteriaJpaRepositoryBase<Message, Long> implements MessageRepository {
    
}
