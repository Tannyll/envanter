package com.emirci.envanter.model;

import javax.persistence.*;

/**
 * Created by serdaremirci on 9/20/17.
 */
@Entity
@Table(name = "XAttTrademark")
public class Trademark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TrademarkId", unique = true)
    private Long trademarkId;

    @Column(name = "Trademark", length = 50)
    private String trademark;

    @OneToOne(mappedBy = "trademarks")
    @PrimaryKeyJoinColumn
    private Inventory inventory;

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
