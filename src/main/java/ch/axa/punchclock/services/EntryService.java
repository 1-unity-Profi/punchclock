/*package ch.axa.punchclock.services;

import ch.axa.punchclock.models.Entry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EntryService { 

	@PersistenceContext
	private EntityManager em;

    @Transactional
    public Entry create(Entry entry) {
        this.em.persist(entry);
        return entry;

    }

    public Entry read(Long id) {
        return em.find(Entry.class, id);
    }

    public List<Entry> index() {
        return this.em.createQuery("SELECT e FROM Entry e", Entry.class).getResultList();
    }

    public Entry update(Entry entry) { 
        this.em.merge(entry);
        return entry;
    }

    public void delete(Entry entry) {
        this.em.remove(entry);
    }
}
*/