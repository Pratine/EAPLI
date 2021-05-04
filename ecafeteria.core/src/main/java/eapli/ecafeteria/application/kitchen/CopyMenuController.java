/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.time.DateInterval;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Joao Paulo
 */
public class CopyMenuController implements  Controller{
    
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    
    public Iterable<Menu> findAllMenus() {
        return menuRepository.findAll();
    }
     
    public Menu copyMenu(Menu menu){
        return new Menu(menu);
    }
    
     public Menu saveMenu(Menu menu) throws DataConcurrencyException, DataIntegrityViolationException{
        return menuRepository.save(menu);
    }
     
    public Menu  setDate(Menu selected,Calendar sDate,Calendar eDate){
        selected.setDate(sDate, eDate);
        return selected;
    }
    public boolean isDateBefore(Calendar sDate,Calendar eDate){
         if(sDate.before(eDate)){
            return true;
        }
        return false;
    }
    
    private boolean dateMatch(DateInterval dt1,DateInterval dt2){
        Calendar s1 = dt1.start();
        Calendar s2 = dt2.start();
        Calendar e1 = dt1.end();
        Calendar e2 = dt2.end();
        
        
        System.out.println("s1:" +s1.getTime().toString() + ";s2:" +s2.getTime().toString() + ";e1:"+ e1.getTime().toString() +";e2:"+e2.getTime().toString()+ " \n");
        if((s1.after(s2))&&(s1.before(e2))){
            return true;
        }
        if((e1.before(e2))&&(e1.after(s2))){
            return true;
        }
        
        return false;
        
    }
    
    public boolean isDateFree(DateInterval dt){
        List<Menu> list = (List<Menu>) findAllMenus();
        
        for (Menu menu : list) {
            if(dateMatch(dt, menu.getDate())){
                return false;
            }
        }
        return true;
    }
  }
