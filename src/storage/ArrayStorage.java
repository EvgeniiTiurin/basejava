package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private int arraySize = 10_000;
    private Resume[] storage = new Resume[arraySize];
    private int resumeCounter = 0;

    private int getIndex(String uuid) {
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, resumeCounter, null);
        resumeCounter = 0;
        System.out.println("Массив успешно очищен");
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume + " успешно обновлено");
            return;
        }
        System.out.println("ERROR: Резюме " + resume.getUuid() + " для обновления не найдено");
    }

    public void save(Resume resume) {
        if (resumeCounter < arraySize) {
            if (resume.getUuid() != null) {
                if (getIndex(resume.getUuid()) >= 0) {
                    System.out.println("ERROR: Резюме " + resume + " уже внесено в базу");
                    return;
                }
                storage[resumeCounter] = resume;
                resumeCounter++;
                System.out.println("Резюме " + resume + " добавлено в базу");
            } else {
                System.out.println("ERROR: Вы ввели пустое значение");
            }
        } else {
            System.out.println("ERROR: Достигнут максимум массива резюме");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Резюме " + uuid + " не найдено");
            return;
        }
        System.out.println("Резюме " + storage[index].getUuid() + " успешно удалено из базы");
        System.arraycopy(storage, index + 1, storage, index, resumeCounter - 1 - index);
        resumeCounter--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, resumeCounter);
    }

    public int size() {
        return resumeCounter;
    }
}
