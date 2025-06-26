package ch.axa.punchclock.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.axa.punchclock.models.Entry;

public interface EntryRepository extends CrudRepository<Entry, Long>, EntryRepositoryCustom {
    Iterable<Entry> findByCategory(@Param("categoryId") Long categoryId);
    Iterable<Entry> findByTags_Id(@Param("tagId")Long tagId);
    
    @Query(value = "SELECT * FROM entry e WHERE e.description LIKE %:searchDescription%", nativeQuery = true)
    Iterable<Entry> findByDescription(@Param("searchDescription") String searchDescription);
}