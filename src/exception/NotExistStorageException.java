package exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("ERROR: Резюме " + uuid + " не найдено", uuid);
    }
}
