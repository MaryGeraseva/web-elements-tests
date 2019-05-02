package common.dataProviders;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class CsvDataProvider {

    @DataProvider(name="csvReader")
    public static Iterator<Object[]> csvReader(Method method) {
        List<Object[]> list = new ArrayList<>();
        String pathname = getPathName(method);
        try (CSVReader reader = new CSVReader(new FileReader(new File(pathname)))) {
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<>();
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    list.add(new Object[]{testData});
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file " + pathname + " wasn't found.\n" + e.getStackTrace().toString());

        } catch (IOException e) {
            throw new RuntimeException("couldn't read " + pathname + " file.\n" + e.getStackTrace().toString());
        }
        return list.iterator();
    }

    private static String getPathName(Method method) {
        return String.format("src\\test\\resources\\dataProviders\\%s\\%s.csv",
                method.getDeclaringClass().getSimpleName(), method.getName());
    }
}
