package ch.axa.punchclock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
 
import ch.axa.punchclock.models.Category;
import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.models.Tag;
import ch.axa.punchclock.repositories.CategoryRepository;
import ch.axa.punchclock.repositories.EntryRepository;
import ch.axa.punchclock.repositories.TagRepository;
 
@Component
public class DataLoader implements ApplicationRunner {
 
  @Autowired
  private CategoryRepository categoryRepository;
 
  @Autowired
  private EntryRepository entryRepository;
 
  @Autowired
  private TagRepository tagRepository;
 
 
 
  @Override
  public void run(ApplicationArguments args) throws Exception {
    // Beispiel-Kategorien
    var category1 = new Category(); category1.setName("Entwicklung");
    var category2 = new Category(); category2.setName("Meeting");
    var category3 = new Category(); category3.setName("Support");
    var category4 = new Category(); category4.setName("Testing");
    var category5 = new Category(); category5.setName("Dokumentation");
    categoryRepository.save(category1);
    categoryRepository.save(category2);
    categoryRepository.save(category3);
    categoryRepository.save(category4);
    categoryRepository.save(category5);

    // Beispiel-Tags
    var tag1 = new Tag(); tag1.setName("Frontend");
    var tag2 = new Tag(); tag2.setName("Backend");
    var tag3 = new Tag(); tag3.setName("Bugfix");
    var tag4 = new Tag(); tag4.setName("Feature");
    var tag5 = new Tag(); tag5.setName("Refactoring");
    tagRepository.save(tag1);
    tagRepository.save(tag2);
    tagRepository.save(tag3);
    tagRepository.save(tag4);
    tagRepository.save(tag5);

    // Beispiel-Einträge für eine Arbeitswoche (Mo-Fr)
    java.time.LocalDateTime base = java.time.LocalDateTime.now().withHour(8).withMinute(0).withSecond(0).withNano(0);
    for (int i = 0; i < 5; i++) {
      var entry = new Entry();
      entry.setDescription("Arbeitstag " + (i + 1));
      entry.setCheckIn(base.plusDays(i));
      entry.setDuration(60 + i * 10);
      // Kategorien rotieren
      if (i == 0) entry.setCategory(category1);
      else if (i == 1) entry.setCategory(category2);
      else if (i == 2) entry.setCategory(category3);
      else if (i == 3) entry.setCategory(category4);
      else entry.setCategory(category5);
      // Tags zuweisen
      if (entry.getTags() != null) {
        entry.getTags().add(tag1);
        if (i % 2 == 0) entry.getTags().add(tag2);
        if (i % 3 == 0) entry.getTags().add(tag3);
      }
      entryRepository.save(entry);
    }
  }
 
}