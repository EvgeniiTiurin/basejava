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

    private Resume findResumeByResume(Resume resume) {
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].getUuid() == resume.getUuid()) {
                return storage[i];
            }
        }
        return null;
    }

    private int findResumeByUuid(String uuid) {
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        System.out.print("ERROR: Резюме не найдено\n");
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, resumeCounter, null);
        resumeCounter = 0;
        System.out.println("Массив успешно очищен");
    }

    public void update(Resume resume) {
        if (findResumeByResume(resume) != null) {
            return;
        }
        System.out.println("ERROR: Резюме для обновления не найдено");
    }

    public void save(Resume resume) {
        if (resumeCounter < arraySize) {
            if (resume.getUuid() != null) {
                if (findResumeByResume(resume) != null) {
                    System.out.println("ERROR: Данное резюме уже внесено в базу");
                    return;
                }
                storage[resumeCounter] = resume;
                resumeCounter++;
                System.out.println("Резюме добавлено в базу");
            } else {
                System.out.println("ERROR: Вы ввели пустое значение");
            }
        } else {
            System.out.println("ERROR: Достигнут максимум массива резюме");
        }
    }

    public Resume get(String uuid) {
        int i = findResumeByUuid(uuid);
        if (i == -1) {
            return null;
        }
        return storage[i];
    }

    public void delete(String uuid) {
        int i = findResumeByUuid(uuid);
        if (i == -1) {
            return;
        }
        if (storage[i] != null) {
            System.out.println("Резюме " + storage[i].getUuid() + " успешно удалено из базы");
            System.arraycopy(storage, i + 1, storage, i, resumeCounter - 1 - i);
            resumeCounter--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[resumeCounter];
        System.arraycopy(storage, 0, allResume, 0, resumeCounter);
        return allResume;
    }

    public int size() {
        return resumeCounter;
    }
}
