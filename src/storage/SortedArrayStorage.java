package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
//        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, resumeCounter, searchKey);
    }

    @Override
    protected void saveToArray(Resume resume, int index) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, resumeCounter - index);
        storage[index] = resume;
    }
}
