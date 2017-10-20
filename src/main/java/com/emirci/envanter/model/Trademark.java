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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrademarkId", unique = true, nullable = false)
    private Long trademarkId;

    @Column(name = "Trademark", unique = true, nullable = false)
    private String trademark;

    @OneToMany(mappedBy = "trade")
    @PrimaryKeyJoinColumn
    private Set<Inventory> inventoryes;


    public Trademark() {

    }

    public Trademark(String trademark) {
        this.trademark = trademark;
    }

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

    @Override
    public String toString() {
        return "Trademark{" +
                "trademark='" + trademark + '\'' +
                '}';
    }
}
