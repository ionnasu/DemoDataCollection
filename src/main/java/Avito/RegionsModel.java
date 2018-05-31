package Avito;

public class RegionsModel {
    public RegionsModel(int id, String name) {
        RegionsModel.setId(id);
        RegionsModel.setName(name);
    }

    public  int getId() {
        return id;
    }

    public static void setId(int id) {
        RegionsModel.id = id;
    }

    public  String getName() {
        return name;
    }

    public static void setName(String name) {
        RegionsModel.name = name;
    }

    private static int id;
    private static String name;
}
