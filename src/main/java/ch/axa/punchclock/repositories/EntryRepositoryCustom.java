package ch.axa.punchclock.repositories;

import java.util.List;

import ch.axa.punchclock.models.Entry;

public interface EntryRepositoryCustom {
        List<Entry> findByDescriptionWithCriteria(String description);
}