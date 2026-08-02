package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of media entries that the user has added to their library.
 */
public class Library {
    private List<MediaEntry> entries;

    public Library() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(MediaEntry entry) {
        entries.add(entry);
    }

    public void removeEntry(MediaEntry entry) {
        entries.remove(entry);
    }

    public List<MediaEntry> getAllEntries() {
        return entries;
    }
}
