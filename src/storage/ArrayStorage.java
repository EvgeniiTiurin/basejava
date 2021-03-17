package storage;

import model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private int arraySize = 10000;
    private Resume[] storage = new Resume[arraySize];
    private int resumeCounter = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeCounter, null);
        resumeCounter = 0;
        System.out.println("Массив успешно очищен");
    }

    public void update(Resume r) throws IOException {
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].getUuid() == r.getUuid()) {
                System.out.print("Введите новый uuid резюме: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String str = reader.readLine();
                String[] params = str.trim().toLowerCase().split(" ");
                if (str.length() == 0) {
                    System.out.println("ERROR: Неверная команда.");
                    return;

                } else {
                    System.out.println("Резюме успешно изменено");
                    storage[i].setUuid(params[0].intern());
                    return;
                }
            }
        }
        System.out.println("ERROR: Неверная команда");
    }

    public void save(Resume r) {
        if (resumeCounter < arraySize) {
            if (r.getUuid() != null) {
                for (int i = 0; i < resumeCounter; i++) {
                    if (storage[i].getUuid() == r.getUuid()) {
                        System.out.println("ERROR: Данное резюме уже внесено в базу");
                        return;
                    }
                }
                storage[resumeCounter] = r;
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
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].getUuid() == uuid) {
                return storage[i];
            }
        }
        System.out.print("ERROR: Резюме не найдено : ");
        return null;
    }

    public void delete(String uuid) {
        boolean found = false;
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].getUuid() == uuid) {
                System.out.println("Резюме " + storage[i].getUuid() + " успешно удалено из базы");
                System.arraycopy(storage, i + 1, storage, i, resumeCounter - 1 - i);
                resumeCounter--;
                found = true;
            }
        }
        if (!found) {
            System.out.println("Резюме для удаления не найдено или резюме не указано");
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
