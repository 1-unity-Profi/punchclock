package ch.axa.punchclock.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "entry")
public class Entry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "entry_id")
    private Long id;

    @Column(name = "check_in", nullable = false)
    private LocalDateTime checkIn;


    @Column()
    private int duration;

    @Column(length = 5000)
    private String description;
    // getters and settersD

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
