package storage;

import model.Resume;

import java.util.Arrays;

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
        if (index < 0) {
            System.out.println("ERROR: Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

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
        if (resumeCounter < ARRAY_SIZE) {
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

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, resumeCounter);
    }
    
    public void clear() {
        Arrays.fill(storage, 0, resumeCounter, null);
        resumeCounter = 0;
        System.out.println("Массив успешно очищен");
    }
}
