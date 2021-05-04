/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class Menu implements AggregateRoot<Long>, Serializable {

    private final String title;
    private final List<MenuItem> itens = new ArrayList<>();
    private final Map<Integer, MenuItem> itemByOption = new HashMap<>();

    public Menu() {
        this.title = "";
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;

    public Menu(String title) {
        this.title = title;
    }

    public void add(MenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        this.itens.add(item);
        this.itemByOption.put(item.option(), item);
    }

    public String title() {
        return this.title;
    }

    public Iterable<MenuItem> itens() {
        return this.itens;
    }

    public MenuItem item(int option) {
        return this.itemByOption.get(option);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Menu)) {
            return false;
        }

        final Menu that = (Menu) other;
        if (this == that) {
            return true;
        }
        return id().equals(that.id());
    }

    @Override
    public Long id() {
        return id;
    }
}
