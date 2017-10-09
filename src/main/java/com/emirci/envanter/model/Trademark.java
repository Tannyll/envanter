package com.emirci.envanter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by serdaremirci on 9/20/17.
 */
@Entity
@Table(name = "XAttTrademark")
public class Trademark implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TrademarkId", unique = true)
    private Long trademarkId;

    @Column(name = "Trademark", length = 50)
    private String trademark;

    @OneToMany(mappedBy = "trademarks", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Inventory> inventoryes;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTrademarkId() {
        return trademarkId;
    }

    public void setTrademarkId(Long trademarkId) {
        this.trademarkId = trademarkId;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public Set<Inventory> getInventoryes() {
        return inventoryes;
    }

    public void setInventoryes(Set<Inventory> inventoryes) {
        this.inventoryes = inventoryes;
    }

    public Trademark() {

    }

    public Trademark(String trademark) {
        this.trademark = trademark;
    }

    @Override
    public String toString() {
        return "Trademark{" +
                "trademark='" + trademark + '\'' +
                '}';
    }
}
