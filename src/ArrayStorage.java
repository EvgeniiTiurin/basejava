import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int resumeCounter = 0;

    void clear() {
        Arrays.fill(storage, 0, resumeCounter, null);
        resumeCounter = 0;
    }

    void save(Resume r) {
        if (r.uuid != null) {
            storage[resumeCounter] = r;
            resumeCounter++;
        }
    }

    Resume get(String uuid) {
        Resume resume = new Resume();
        resume.uuid = null;
        try {
            for (Resume r : storage) {
                if (r.uuid == uuid) {
                    return r;
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("Резюме не найдено");
        }
        return resume;
    }

    void delete(String uuid) {
        try {
            for (Resume r : storage) {
                if (r.uuid == uuid) {
                    int numberOfElement = Arrays.asList(storage).indexOf(r);
                    System.arraycopy(storage, Arrays.asList(storage).indexOf(r) + 1, storage, numberOfElement, storage.length - 1 - numberOfElement);
                    resumeCounter--;
                } else {
                    System.out.println("Резюме для удаления не найдено");
                }
            }
        } catch (Exception ex) { }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[resumeCounter];
        System.arraycopy(storage, 0, allResume, 0, resumeCounter);
        return allResume;
    }

    int size() {
        return resumeCounter;
    }
}
