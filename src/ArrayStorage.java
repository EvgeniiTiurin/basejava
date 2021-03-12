import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
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
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        System.out.print("Резюме не найдено : ");
        return null;
    }

    void delete(String uuid) {
        boolean found = false;
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].uuid == uuid) {
                System.arraycopy(storage, i + 1, storage, i, resumeCounter - 1 - i);
                resumeCounter--;
                found = true;
            }
        }
        if (!found) {
            System.out.println("Резюме для удаления не найдено");
        }
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
