package common.JsonDataProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class JsonDataProvider {

    @DataProvider(name="jsonReader")
    public static Iterator<Object> jsonReader(Method method) {
        List<Object> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getPathName(method)));
            Gson gson = new Gson();
            Type type = new TypeToken<Collection<UserLogInData>>(){}.getType();
            list = gson.fromJson(reader, type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.iterator();
    }

    private static String getPathName(Method method) {
        return String.format("src\\test\\resources\\dataProviders\\%s\\%s.json",
                method.getDeclaringClass().getSimpleName(), method.getName());
    }
}
