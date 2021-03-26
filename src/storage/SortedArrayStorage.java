package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, resumeCounter, searchKey);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, resumeCounter, null);
        resumeCounter = 0;
        System.out.println("Массив успешно очищен");
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume + " успешно обновлено");
            return;
        }
        System.out.println("ERROR: Резюме " + resume.getUuid() + " для обновления не найдено");
    }

    @Override
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

    @Override
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

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, resumeCounter);
    }
}
