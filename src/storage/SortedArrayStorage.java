package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, resumeCounter, searchKey);
    }

    @Override
    protected void adoptedSave(Resume resume) {
        if (resumeCounter > 0) {
            int index = Arrays.binarySearch(storage, 0, resumeCounter, resume, Comparator.nullsLast((Object o1, Object o2) -> ((Resume) o1).compareTo((Resume) o2)));
            if (index < 0) {
                index = -index - 1;
            }
            System.arraycopy(storage, index, storage, index + 1, resumeCounter - index);
            storage[index] = resume;
        } else {
            storage[resumeCounter] = resume;
        }
    }
}
