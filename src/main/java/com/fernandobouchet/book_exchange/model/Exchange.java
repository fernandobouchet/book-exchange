package com.fernandobouchet.book_exchange.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "exchanges")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offered_book_id", nullable = false)
    private Book offeredBook;

    @ManyToOne
    @JoinColumn(name = "received_book_id", nullable = false)
    private Book receivedBook;

    @ManyToOne
    @JoinColumn(name = "owner_id",nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "exchange_partner_id",nullable = false)
    private User exchangePartner;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExchangeStatus status = ExchangeStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange = (Exchange) o;
        return Objects.equals(id, exchange.id) && Objects.equals(offeredBook, exchange.offeredBook) && Objects.equals(receivedBook, exchange.receivedBook) && Objects.equals(owner, exchange.owner) && Objects.equals(exchangePartner, exchange.exchangePartner) && status == exchange.status && Objects.equals(date, exchange.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offeredBook, receivedBook, owner, exchangePartner, status, date);
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "id=" + id +
                ", offeredBook=" + offeredBook +
                ", receivedBook=" + receivedBook +
                ", owner=" + owner +
                ", exchangePartner=" + exchangePartner +
                ", status=" + status +
                ", date=" + date +
                '}';
    }

    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }
}
