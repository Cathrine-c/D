package InterviewImportant.Arithmiy;

import java.util.*;


class Employee{
    int id;
    int importance;
    List<Integer> subordinates;

}


public class Expand {

    private final String x;


    public Expand(String x) {
        this.x =x;

    }

    public static int getImportance(List<Employee> employees, int id){
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return getImportancehelpr(map, id);

    }

    public static int getImportancehelpr(Map<Integer, Employee> map, int id) {
        Employee employee = map.get(id);
        for (int subId : employee.subordinates) {
            employee.importance += getImportancehelpr(map, subId);
        }
        return employee.importance;
    }

    public static void main(String[] args) {
        Employee employee1=new Employee();
        //员工1
        employee1.id=1;
        employee1.importance=5;
        employee1.subordinates.add(2);
        employee1.subordinates.add(3);

        //员工2
        Employee employee2=new Employee();
        employee2.id = 2;
        employee2.importance = 3;
        employee2.subordinates = null;

        //员工3
        Employee employee3=new Employee();
        employee3.id = 3;
        employee3.importance = 3;
        employee3.subordinates = null;

        //把三个员工相关信息添加到数据结构中
        List<Employee> employees = new LinkedList<>();

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        //调用方法
        getImportance(employees,1);


    }
}
