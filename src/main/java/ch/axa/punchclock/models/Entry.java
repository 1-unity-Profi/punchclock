package ch.axa.punchclock.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@NamedQuery(query = "SELECT u FROM Entry u WHERE u.category.id = :categoryId", name = "Entry.findByCategory")
@Table(name = "entry")
public class Entry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "entry_id")

@JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "check_in", nullable = false)
    @NotNull(message = "Datum darf Nicht leer")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkIn;

    private int duration;

    @Column(length = 5000)
    private String description;
    // getters and setters

    @ManyToOne
    @JsonIgnoreProperties(value = "entry")
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany
    @JsonIgnoreProperties(value = "entries")
    @JoinTable(
        name = "tag_entry",
        joinColumns = @JoinColumn(name = "entry_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
    

    @AssertTrue(message = "Duration darf nicht unter 5Min sein")
    public boolean isDurationOver5Min(){

        return duration >= 5;
    }

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

    public Entry(@NotNull(message = "Datum darf Nicht leer") LocalDateTime checkIn, int duration,
            @NotBlank(message = "Description darf Nicht Leer sein") String description, Category category,
            Set<Tag> tags) {
        this.checkIn = checkIn;
        this.duration = duration;
        this.description = description;
        this.category = category;
        this.tags = tags;
    }

    public Entry() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTag(Set<Tag> tags) {
        this.tags = tags;
    }

    

    

}
