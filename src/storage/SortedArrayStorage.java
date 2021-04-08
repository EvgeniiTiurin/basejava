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
    public void save(Resume resume) {
        if (resumeCounter < ARRAY_SIZE) {
            if (resume.getUuid() != null) {
                if (getIndex(resume.getUuid()) >= 0) {
                    System.out.println("ERROR: Резюме " + resume + " уже внесено в базу");
                    return;
                }
                storage[resumeCounter] = resume;
                resumeCounter++;
                sortInOrder(resume);
                System.out.println("Резюме " + resume + " добавлено в базу");
            } else {
                System.out.println("ERROR: Вы ввели пустое значение");
            }
        } else {
            System.out.println("ERROR: Достигнут максимум массива резюме");
        }
    }

    public void sortInOrder(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, resumeCounter - 1, resume, Comparator.nullsLast((Object o1, Object o2) -> ((Resume) o1).compareTo ((Resume) o2)));
        if (index < 0) {
            index = -index - 1;
        }
        System.arraycopy(storage, index, storage, index + 1, resumeCounter - 1 - index);
        storage[index] = resume;
    }
}
