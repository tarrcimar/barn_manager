package example.model;

import javax.persistence.*;

@Entity
public class Forage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Basic
    @Enumerated(EnumType.STRING)
    private ForageType name;

    @Basic
    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    @Basic
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ForageType getName() {
        return name;
    }

    public void setName(ForageType name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public User getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(User owner_id) {
        this.owner_id = owner_id;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }
}
