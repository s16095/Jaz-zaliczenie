package pjatk.adrwoj.jazs16095nbp.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;
    @Column(name="table")
    public String table;
    @Column(name="currency")
    public String currency;
    @Column(name="mid")
    public String mid;
    @Column(name="code")
    public String code;
    @Column(name="rates")
    public Rate[] rates;
}