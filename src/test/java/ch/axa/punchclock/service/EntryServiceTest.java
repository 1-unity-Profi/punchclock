/*package ch.axa.punchclock.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.springframework.boot.test.context.SpringBootTest;

import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.repositories.EntryRepository;
import jakarta.inject.Inject;

@SpringBootTest
public class EntryServiceTest {

  @Inject
  private EntryRepository entryService;

  @Test
  public void testIfEntryCanBeSaved1() {
      Entry entry = new Entry();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryService.create(entry);
    
      assertEquals(entry.getDescription(), entryService.read(entry.getId()).getDescription());
  }

  @Test
  public void testIfEntryCanBeSaved2() {
      Entry entry = new Entry();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryService.create(entry);
    
      assertEquals(entry.getDuration(), entryService.read(entry.getId()).getDuration());
  }

  @Test
  public void testIfEntryCanBeSaved3() {
      Entry entry = new Entry();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryService.create(entry);
    
      assertSame(entry.getCheckIn(), entryService.read(entry.getId()).getCheckIn());
  }

  @Test
  public void testIfEntryCanBeSaved4() {
      Entry entry = new Entry();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryService.create(entry);
    
      assertEquals(entry.getId(), entryService.read(entry.getId()).getId());
  }

  @Test
  public void testIfEntryCanBeSaved5() {
      Entry entry = new Entry();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryService.create(entry);

    assertNotNull(entryService.read(entry.getId()));
      
  }
}*/