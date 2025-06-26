package ch.axa.punchclock.models;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private long id;

    @NotBlank(message = "Category Name darf nicht leer sein")
    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Entry> entry = new HashSet<>();
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Entry> getEntry() {
        return entry;
    }

    public void setEntry(Set<Entry> entry) {
        this.entry = entry;
    }

    

}
