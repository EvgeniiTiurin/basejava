package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected static final int ARRAY_SIZE = 10_000;
    protected Resume[] storage = new Resume[ARRAY_SIZE];
    protected int resumeCounter = 0;

    public int size() {
        return resumeCounter;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
