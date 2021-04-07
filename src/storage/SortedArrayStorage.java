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
    public void save(Resume resume) {
        //super.save(resume);
        if (resumeCounter < ARRAY_SIZE) {
            if (resume.getUuid() != null) {
                if (getIndex(resume.getUuid()) >= 0) {
                    System.out.println("ERROR: Резюме " + resume + " уже внесено в базу");
                    return;
                }
                storage[resumeCounter] = resume;
                Arrays.sort(storage);
                resumeCounter++;
                System.out.println("Резюме " + resume + " добавлено в базу");
            } else {
                System.out.println("ERROR: Вы ввели пустое значение");
            }
        } else {
            System.out.println("ERROR: Достигнут максимум массива резюме");
        }
    }
}
