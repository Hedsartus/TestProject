import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


class JsonParsTest {

    @org.junit.jupiter.api.Test
    void listToJsonTest() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"John", "Smith", "USA",25));
        employeeList.add(new Employee(2,"Ivan", "Petrov", "RU",23));


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String strExpected =  gson.toJson(employeeList, new TypeToken<List<Employee>>() {}.getType());

        List<Employee> employeeList1 = new ArrayList<>();

        Assertions.assertEquals(strExpected, JsonPars.listToJson(employeeList));
        Assertions.assertNull(JsonPars.listToJson(employeeList1));
        Assertions.assertNull(JsonPars.listToJson(null));
    }

    @org.junit.jupiter.api.Test
    void readStringTest() {
        Assertions.assertNull(JsonPars.readString("fakefile.txt"));
    }

    @org.junit.jupiter.api.Test
    void jsonToListTest() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"John", "Smith", "USA",25));
        employeeList.add(new Employee(2,"Ivan", "Petrov", "RU",23));

        String jsonText =
                "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},"+
                        "{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        List<Employee> employeeListResult = JsonPars.jsonToList(jsonText, Employee.class);
        Assertions.assertEquals(employeeList.toString(), employeeListResult.toString());
        Assertions.assertNull(JsonPars.jsonToList("", Employee.class));
        Assertions.assertNull(JsonPars.jsonToList(null, Employee.class));

    }
}