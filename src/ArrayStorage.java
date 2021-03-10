import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int index = 0;

    void clear() {
        Arrays.fill(storage, null);
        index = 0;
    }

    void save(Resume r) {
        if (r.uuid != null) {
            storage[index] = r;
            index++;
        }
    }

    Resume get(String uuid) {
        for (Resume r : storage) {
            if (r.uuid == uuid) {
                return r;
            }
        }
        System.out.println("Не найдено");
        return null;
    }

    void delete(String uuid) {
        for (Resume r : storage) {
            if (r.uuid == uuid) {
                System.arraycopy(storage, Arrays.asList(storage).indexOf(r) + 1, storage, Arrays.asList(storage).indexOf(r), storage.length - 1 - Arrays.asList(storage).indexOf(r));
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) ;
            count++;
        }

        Resume[] temp = new Resume[5]; //storage.length - count];
        int j = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                temp[j] = storage[i];
                j++;
            }
        }
        storage = temp;
        return storage;
        //return new Resume[0];
    }

    int size() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
            count++;
            }
        }
        return count;
    }
}
