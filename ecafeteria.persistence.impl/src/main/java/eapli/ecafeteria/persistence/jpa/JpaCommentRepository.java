/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.rateMeal.Comment;
import eapli.ecafeteria.persistence.CommentRepository;
import java.io.Serializable;

/**
 *
 * @author Utilizador
 */
public class JpaCommentRepository extends CafeteriaJpaRepositoryBase<Comment, Long> implements CommentRepository {
    
}
