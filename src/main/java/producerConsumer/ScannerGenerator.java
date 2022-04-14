package producerConsumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Scanner;
import java.util.function.Consumer;


public class ScannerGenerator extends Generator<String> {

    private final Scanner scanner;

    public ScannerGenerator(Scanner scanner) {
        super(1000);
        this.scanner = scanner;
    }

    public ScannerGenerator(File file) {
        this(file2Scanner(file));
    }

    public ScannerGenerator(InputStream inputStream) {
        this(new Scanner(inputStream));
    }

    public ScannerGenerator(Reader reader) {
        this(new Scanner(reader));
    }

    private static Scanner file2Scanner(File file) {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void produce(Consumer<String> consumer) {
        if (scanner == null) {
            return;
        }
        while (scanner.hasNextLine()) {
            consumer.accept(scanner.nextLine());
        }
        scanner.close();
    }
}