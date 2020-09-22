package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> timeList = new HashMap<Long, TimeEntry>();
    private long id = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeList.put(id, timeEntry);
        timeEntry.setId(id);
        id++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeList.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> result = new ArrayList<>();

        for(Map.Entry<Long, TimeEntry> entry : timeList.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry oldTimeEntry = timeList.replace(id, timeEntry);
        if (oldTimeEntry == null) return null;
        else{
            timeEntry.setId(id);
            return timeEntry;
        }
    }

    @Override
    public void delete(long id) {
        timeList.remove(id);
    }
}
