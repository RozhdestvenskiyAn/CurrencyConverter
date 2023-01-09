package ru.rozhdestvenskiy.currencyConverter.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "history_convert")
public class HistoryConvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromCurrencyNumCode", column = @Column(name = "from_currency_num_code")),
            @AttributeOverride(name = "amountFrom", column = @Column(name = "amount_from")),
            @AttributeOverride(name = "toCurrencyNumCode", column = @Column(name = "to_currency_num_code")),
            @AttributeOverride(name = "amountTo", column = @Column(name = "amount_to")),
    })
    @AssociationOverrides({
            @AssociationOverride(name = "from_currency_num_code", joinColumns = @JoinColumn(name = "from_currency_num_code", referencedColumnName = "num_code")),
            @AssociationOverride(name = "to_currency_num_code", joinColumns = @JoinColumn(name = "to_currency_num_code", referencedColumnName = "num_code")),
    })
    private Convert convert;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Convert getConvert() {
        return convert;
    }

    public void setConvert(Convert convert) {
        this.convert = convert;
    }

    @Override
    public String toString() {
        return "HistoryConvert{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", convert=" + convert +
                '}';
    }
}
